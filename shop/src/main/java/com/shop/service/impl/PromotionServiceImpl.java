package com.shop.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.shop.entity.Product;
import com.shop.entity.Promotion;
import com.shop.service.PromotionService;
import com.shop.util.UUIdutil;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class PromotionServiceImpl extends BaseServiceImpl implements
		PromotionService {

	@Override
	public int addPromotion(Promotion promotion, int prod_id) throws Exception {
		Product product = new Product();
		Map<String, Object> prod = new HashMap<String, Object>();
		prod.put("prodId", prod_id);
		String hql = "From Product p where p.prodId = :prodId";
		List list = this.find(hql, prod);
		try {
			product = (Product) list.get(0);
			promotion.setPromUuid(UUIdutil.uuid());
			product.setPromotion(promotion);
			this.update(product);
			this.save(promotion);
			return 200;
		} catch (Exception e) {
			e.printStackTrace();
			return 404;
		}
	}

	@Override
	public int updatePromotion(Promotion promotion, int prod_id)
			throws Exception {
		// 先将原来的product对应的促销设空
		Product product = new Product();
		Map<String, Object> prod = new HashMap<String, Object>();
		prod.put("promId", promotion.getPromId());
		String hql = "From Product p inner join p.promotion prom where prom.promId = :promId";
		List list = this.find(hql, prod);
		try {
			if (list.isEmpty()) {
				return 202;
			} else {
				Object object = list.get(0);
				Object[] obj = (Object[]) object;
				product = (Product) obj[0];
				product.setPromotion(null);
				this.update(product);
			}
			// 更新
			prod.remove("promId");
			prod.put("prodId", prod_id);
			hql = "From Product p where p.prodId = :prodId";
			list = this.find(hql, prod);
			product = (Product) list.get(0);
			product.setPromotion(promotion);
			this.update(product);
			this.merge(promotion);
			return 200;
		} catch (Exception e) {
			e.printStackTrace();
			return 404;
		}
	}

	@Override
	public int deletePromotion(int prom_id) throws Exception {
		Promotion promotion = new Promotion();
		Product product = new Product();
		Map<String, Object> p = new HashMap<String, Object>();
		try {
			p.put("promId", prom_id);
			List list = this
					.find("From Promotion p inner join p.products prod where p.promId = :promId",
							p);
			if (list.isEmpty()) {
				return 202;
			} else {
				for (Object object : list) {
					Object[] obj = (Object[]) object;
					promotion = (Promotion) obj[0];
					product = (Product) obj[1];
					product.setPromotion(null);
					this.update(product);
					this.delete(promotion);
				}
			}
			return 200;
		} catch (Exception e) {
			e.printStackTrace();
			return 404;
		}
	}

	/**
	 * 如果不加@Transactional(readOnly=false),那么
	 * this.updateA(list)这个函数就会在getPromotionByPage的事务中,不会另外开一个事务,因为<tx:method
	 * name="*" propagation="REQUIRED" />,
	 * 这是REQUIRE的含义,因此methodA(getPromotionByPage)为只读,所以不能修改
	 */
	@Transactional(readOnly = false)
	@Override
	public List<Map<String, Object>> getPromotionByPage(int shop_id,
			int pageIndex, int pageSize) throws Exception {
		Promotion promotion = new Promotion();
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("shopId", shop_id);
		String hql = "from Product p inner join p.promotion prom where prom.promEnabled = 1 and p.shop.shopId = :shopId";
		// 检查促销是否过期,并更新
		List list = this.find(hql, p);
		for (Object object : list) {
			Object[] obj = (Object[]) object;
			promotion = (Promotion) obj[1];
			this.updateValidity(promotion);
		}

		hql = "HQM:select p.prodId as prodId,p.prodUuid as prodUuid ,p.prodName as prodName ,p.prodSell as prodSell ,prom.promPrice as promPrice ,prom.promDiscount as promDiscount ,prom.promId as promId, ps.picUrl as picUrl from Product p inner join p.promotion prom inner join p.pictureRelates pr inner join pr.pictures ps inner join p.shop s where prom.promEnabled = 1 and s.shopId = :shopId";
		List<Map<String, Object>> result = this.find(hql, pageIndex, pageSize,
				p);
		return result;
	}

	@Transactional(readOnly = false)
	@Override
	public List<Map<String, Object>> getAllPromByPage(int pageIndex,
			int pageSize) throws Exception {
		Promotion promotion = new Promotion();
		String hql = "from Product p inner join p.promotion prom where prom.promEnabled = 1 ";
		// 检查促销是否过期,并更新
		List list = this.find(hql, null);
		for (Object object : list) {
			Object[] obj = (Object[]) object;
			promotion = (Promotion) obj[1];
			this.updateValidity(promotion);
		}
		
		hql = "HQM: select p.prodId as prodId,p.prodUuid as prodUuid, p.prodName as prodName ,p.prodSell as prodSell ,prom.promPrice as promPrice ,prom.promDiscount as promDiscount,prom.promId as promId, ps.picUrl as picUrl from Product p inner join p.promotion prom inner join p.pictureRelates pr inner join pr.pictures ps where prom.promEnabled = 1";
		List<Map<String, Object>> result = this.find(hql, pageIndex, pageSize,
				null);
		return result;
	}

	@Transactional(readOnly = false)
	@Override
	public List getPromToHomepage() throws Exception {
		String hql = "from Product p inner join p.pictureRelates pr inner join pr.pictures inner join p.shop s where p.prodType = 'isrecommend' group by p.prodId order by p.prodDate";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setMaxResults(10);
		return query.list();
		
	}
	
	/*@Transactional(readOnly = false)
	@Override
	public List getPromToHomepage() throws Exception {
		Promotion promotion = new Promotion();
		String hql = "from Product p inner join p.promotion prom inner join p.pictureRelates pr inner join pr.pictures inner join p.shop s where prod.prodType = 'isrecommend' order by prod.prodDate";
		Query query = this.getCurrentSession().createQuery(hql);
		query.setMaxResults(5);
		for(Object object: query.list()){
			Object[] obj = (Object[]) object;
			promotion = (Promotion) obj[1];
			this.updateValidity(promotion);
		}
		query = this.getCurrentSession().createQuery(hql);
		query.setMaxResults(5);
		return query.list();
		
	}*/

	@Override
	public boolean judgeValidity(Date end) {
		Date date = new Date();
		long dif = date.getTime() - end.getTime();
		if (dif < 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int updateValidity(Promotion promotion) {
		try {
			// 用户获取时,更新促销的状态
			promotion.setPromEnabled(this.judgeValidity(promotion
					.getPromEndDate()));
			this.update(promotion);
			return 200;
		} catch (Exception e) {
			e.printStackTrace();
			return 404;
		}
	}
}
