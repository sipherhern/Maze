package com.shop.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.pingplusplus.model.Charge;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderRelate;
import com.shop.entity.Promotion;
import com.shop.entity.SkuProd;
import com.shop.service.OrderService;
import com.shop.service.pingpp.imp.ChargeServiceImpl;
import com.shop.util.UUIdutil;

@Component
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Override
	public JSONObject CreateByCartitem(int custId, int addressId) {
		// TODO Auto-generated method stub

		// 根据skuprodId和数量的list，写入事务中
		Session session = this.openSession();
		Transaction tx = session.beginTransaction();
		Order order = new Order();
		// 寻找特定用户
		Customer customer = (Customer) session.get(Customer.class, custId);
		try {
			// 首先先提交一个订单，该订单付款总量为0
			String Uuid = UUIdutil.uuid();
			order.setOrderUuid(Uuid);
			order.setAddressId(addressId);
			order.setOrderPayment(new BigDecimal(0));
			session.save(order);
			BigDecimal TotalPrice = new BigDecimal(0);
			// 寻找购物车的商品种类和数量
			// 给要购买的数量上悲观锁，保证此次购买行为只有该线程才能修改
			String HQL = "Select c.skuprodId as skuprod, c.cartitemQuantity From Cartitem c.custId = :custId";
			Query query = session.createQuery(HQL).setParameter("custId",
					customer.getCustId());
			query.setLockMode("skuprod", LockMode.PESSIMISTIC_WRITE);
			List result = query.list();
			// 在事务结束之前，保证amount被锁住
			// 写入订单-商品关系表
			for (int i = 0; i < result.size(); i++) {
				Object[] obj = (Object[]) result.get(0);
				SkuProd skuprod = (SkuProd) obj[0];
				int prodAmount = (int) obj[1];
				// 判断购买数量是否小于等于剩余数量
				int resultAmount = skuprod.getProdStore();
				if (prodAmount > resultAmount) {
					// 如果不到达剩余数量，该事务回滚
					if (tx != null)
						tx.rollback();
					JSONObject noAmount = new JSONObject();
					noAmount.put("message", "unavaliable goods");
					return noAmount;
				} else {
					// 减少skuprod数量并update
					skuprod.setProdStore(skuprod.getProdStore() - prodAmount);
					// 减少skuprod对应的product并update
					skuprod.getProduct().setProdStore(
							skuprod.getProduct().getProdStore() - prodAmount);
					skuprod.getProduct().setProdSell(
							skuprod.getProduct().getProdSell() + prodAmount);
					session.update(skuprod);
				}
				OrderRelate re = new OrderRelate();
				re.setOrder(order);
				re.setSkuProd(skuprod);
				re.setProdAmount(prodAmount);
				// 计算单个商品后经过促销后的实际价格
				BigDecimal unitPrice = calculatePromotedPrice(skuprod);
				// 最终生成OrderRelate(单个信息)
				BigDecimal TempPrice = unitPrice.multiply(new BigDecimal(
						prodAmount));
				TotalPrice.add(TempPrice);
				session.save(re);
			}
			// 更新订单的总额
			order.setOrderPayment(TotalPrice);
			session.update(order);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			session.clear();
			session.close();
		}
		// 输出订单ID，订单编号
		JSONObject obj = new JSONObject();
		obj.put("orderId", order.getId());
		obj.put("orderNo", order.getOrderUuid());
		return obj;
	}

	@Override
	public JSONObject CreateDirectly(int prodAmount, int addressId,
			int customerId, int skuId) {
		// 根据skuprodId和数量的list，写入事务中
		Session session = this.openSession();
		Transaction tx = session.beginTransaction();
		// 创建新订单对象
		Order order = new Order();
		Customer customer = (Customer) session.get(Customer.class, customerId);
		try {
			String HQL = "From SkuProd sp where sp.skuprodId = :skuId";
			Query query = session.createQuery(HQL).setParameter("skuId", skuId);
			query.setLockMode("sp", LockMode.PESSIMISTIC_WRITE);
			SkuProd skuprod = (SkuProd) query.list().get(0);
			List result = query.list();
			if (skuprod.getProdStore() < prodAmount) {
				// 如果不到达剩余数量，该事务回滚
				if (tx != null)
					tx.rollback();
				JSONObject noAmount = new JSONObject();
				noAmount.put("message", "unavaliable goods");
				return noAmount;
			}
			// 让skuprod增加销售量，减少库存量，并更新skuprod
			else {
				skuprod.setProdStore(skuprod.getProdStore() - prodAmount);
				skuprod.getProduct().setProdStore(
						skuprod.getProduct().getProdStore() - prodAmount);
				skuprod.getProduct().setProdSell(
						skuprod.getProduct().getProdSell() + prodAmount);
				session.update(skuprod);
			}
			// 计算单个商品后经过促销后的实际价格
			BigDecimal unitPrice = calculatePromotedPrice(skuprod);
			OrderRelate re = new OrderRelate();
			String Uuid = UUIdutil.uuid();
			// 创建订单的订单编号(uuid),地址ID，和付费总金额(不算运费)。
			order.setOrderUuid(Uuid);
			order.setCustomer(customer);
			order.setAddressId(addressId);
			order.setOrderState("unpaid");
			order.setOrderEnabled(true);
			order.setOrderPayment(unitPrice
					.multiply(new BigDecimal(prodAmount)));
			session.save(order);
			// 开始写入order关系表，把order和skuid，amount写入
			re.setOrder(order);
			re.setSkuProd(skuprod);
			re.setProdAmount(prodAmount);
			// 最终生成OrderRelate(单个信息)
			session.save(re);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			return null;
		} finally {
			session.clear();
			session.close();
		}
		// 输出订单ID，订单编号
		JSONObject obj = new JSONObject();
		obj.put("orderId", order.getId());
		obj.put("orderNo", order.getOrderUuid());
		return obj;
	}

	@Override
	public void ConfirmOrder(JSONObject obj, BigDecimal orderShipping) {
		// TODO Auto-generated method stub
		Session session = this.openSession();
		Transaction tx = session.beginTransaction();
		try {
			int orderId = obj.getInt("orderId");// 订单Id
			// 首先根据订单ID获取订单对象
			Order order = (Order) session.get(Order.class, orderId);
			String orderNote = obj.getString("orderNote");
			String orderPayType = obj.getString("orderPayType");// 支付方式
			String orderLogistictype = obj.getString("orderLogistictype");// 配送方式
			order.setOrderLogisticType(orderLogistictype);
			order.setOrderPayType(orderPayType);
			order.setOrderNote(orderNote);
			order.setOrderShipping(orderShipping);
			session.saveOrUpdate(order);
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			session.clear();
			session.close();
		}
	}

	@Override
	public JSONArray ListOrderByPage(int pageIndex, int pageSize, int custId) {
		// TODO Auto-generated method stub
		// 过滤掉用户，订单关系实体
		Map map = new HashMap();
		map.put("custId", custId);
		List<Map<String, Object>> result = this
				.find("SQM:"
						+ "Select od.order_state as orderState, od.id as orderId,sku.skuprod_id as skudid,od.order_uuid as orderNo, "
						+ "re.prod_amount as orderAmount, od.order_shipping as orderShipping, od.order_payment as orderPayment, "
						+ "ct.kind_name as name,ct2.kind_name as title,ct3.kind_name as subject,pt.prod_uuid as prodUuid, "
						+ "pt.prod_name as productName "
						+ "from order_relate as re inner join sku_prod as sku on re.skuprod_id = sku.skuprod_id "
						+ "inner join product as pt on pt.prod_id = sku.prod_id "
						+ "inner join category as ct on sku.sku2_id1 = ct.kind_id "
						+ "inner join category as ct2 on ct.parent_id = ct2.kind_id "
						+ "left join category as ct3 on ct2.parent_id = ct3.kind_id "
						+ "inner join "
						+ "`order` as od on re.order_id = od.id inner join customer as cu on od.customer_id "
						+ "= cu.cust_id "
						+ "where od.customer_id = :custId and od.order_enabled = 1 "
						+ "union all "
						+ "Select od.order_state as orderState, od.id as orderId,sku.skuprod_id as skudid,od.order_uuid as orderNo, "
						+ "re.prod_amount as orderAmount, od.order_shipping as orderShipping, od.order_payment as orderPayment, "
						+ "ct.kind_name as name2,ct2.kind_name as title2,ct3.kind_name as subject2,pt.prod_uuid as prodUuid, "
						+ "pt.prod_name as productName "
						+ "from order_relate as re inner join sku_prod as sku on re.skuprod_id = sku.skuprod_id "
						+ "inner join product as pt on pt.prod_id = sku.prod_id "
						+ "inner join category as ct on sku.sku2_id2 = ct.kind_id "
						+ "inner join category as ct2 on ct.parent_id = ct2.kind_id "
						+ "left join category as ct3 on ct2.parent_id = ct3.kind_id "
						+ "inner join "
						+ "`order` as od on re.order_id = od.id inner join customer as cu on od.customer_id "
						+ "= cu.cust_id "
						+ "where od.customer_id = :custId and od.order_enabled = 1 "
						+ "order by skudid ", map);
		System.out.println(result);
		JsonConfig config = new JsonConfig();
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object object, String fieldName,
					Object fieldValue) {
				return null == fieldValue;
			}
		};
		config.setJsonPropertyFilter(filter);
		config.setExcludes(new String[] { "product", "categories",
				"orderRelates", "skuProdsForSku2Id2", "skuProdsForSku2Id1" });
		JSONArray ja = JSONArray.fromObject(result, config);
		ja = JSONMerger(ja);
		return ja;
	}

	@Override
	public Charge RequestPayOrder(JSONObject obj) {
		// TODO Auto-generated method stub
		Order order = (Order) this.get(Order.class, obj.getString("order_no"));
		obj.put("amount", order.getOrderPayment().add(order.getOrderShipping()));
		ChargeServiceImpl chargeServiceImpl = new ChargeServiceImpl();
		Charge result = chargeServiceImpl.createCharge(obj);
		if (result.getPaid() == true) {
			order.setOrderState("success");
			this.update(order);
		}
		return result;
	}

	public JSONArray JSONMerger(JSONArray jsonarray) {
		int Lastskudid = -1;
		for (int i = 0; i < jsonarray.size(); i++) {
			JSONObject obj = (JSONObject) jsonarray.get(i);
			int skudid = obj.getInt("skudid");
			if (i == 0) {
				Lastskudid = obj.getInt("skudid");
			} else {

				if (skudid == Lastskudid) {
					JSONObject rawobj = (JSONObject) jsonarray.get(i - 1);
					if (rawobj.containsKey("subject")) {
						obj.put("subject2", rawobj.get("subject"));
					} else {
						obj.put("subject2", rawobj.get("title"));
					}
					if (obj.containsKey("subject")) {
						obj.remove("title");
					} else {
						obj.put("subject", obj.get("title"));
						obj.remove("title");
					}
					obj.put("name2", rawobj.get("name"));
					jsonarray.remove(i - 1);
					i--;
				}
				Lastskudid = skudid;
				continue;
			}
			Lastskudid = skudid;
		}
		return jsonarray;

	}

	@Override
	public int FalseRemoveOrder(int orderId) {
		// TODO Auto-generated method stub
		Order order = (Order) this.get(Order.class, orderId);
		if (order == null)
			return 204;
		else {
			order.setOrderEnabled(false);
			return 200;
		}

	}

	public BigDecimal calculatePromotedPrice(SkuProd skuprod) {
		BigDecimal unitPrice = skuprod.getProdPrice();
		// 查找有无促销?
		Promotion promotion = skuprod.getProduct().getPromotion();
		if (promotion != null) {
			String type = promotion.getPromType();
			if (promotion.isPromEnabled() == true) {
				switch (type) {
				case "special":
					unitPrice = promotion.getPromPrice();
				case "discount":
					unitPrice = unitPrice.multiply(promotion.getPromDiscount());
				}
			}
		}
		return unitPrice;
	}

	@Override
	public int CancelOrder(int orderId) {
		// TODO Auto-generated method stub
		Order order = (Order) this.get(Order.class, orderId);
		String type = order.getOrderState();
		if (type.equals("unpaid")) {
			Session session = this.openSession();
			Transaction tx = session.beginTransaction();
			try {
				// String HQL =
				// "Select od.orderRelates.skuProd.skuprodId as re From Order od where od.id = :orderId";
				String HQL = "Select re.skuProd as sp,re.prodAmount,re,re.order From OrderRelate re where re.order.id = :orderId";
				Query query = session.createQuery(HQL).setParameter("orderId",
						orderId);
				query.setLockMode("sp", LockMode.PESSIMISTIC_WRITE);
				List resultSet = query.list();
				for (int index = 0; index < resultSet.size(); index++) {
					Object[] resultObj = (Object[]) resultSet.get(index);
					SkuProd skuprod = (SkuProd) resultObj[0];
					int prodAmount = (int) resultObj[1];
					skuprod.setProdStore(skuprod.getProdStore() + prodAmount);
					skuprod.getProduct().setProdStore(
							skuprod.getProduct().getProdStore() + prodAmount);
					skuprod.getProduct().setProdSell(
							skuprod.getProduct().getProdSell() - prodAmount);
					session.update(skuprod);
					// 逐个删除关系表
					OrderRelate re = (OrderRelate) resultObj[2];
					session.delete(re);
					// 逐个删除order表
					Order od = (Order) resultObj[3];
					if (index == resultSet.size() - 1)
						session.delete(od);
				}
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (tx != null)
					tx.rollback();
				return 202;
			} finally {
				session.clear();
				session.close();
			}
			return 200;
		} else {
			return 204;
		}

	}

}
