package com.shop.controller.user;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pingplusplus.model.Charge;
import com.shop.controller.base.RequestContext;
import com.shop.entity.Order;
import com.shop.service.OrderService;
import com.shop.service.ShippingService;
@Component
@RequestMapping(value = "/user/order")
public class OrderController extends RequestContext{
	@Autowired
	private OrderService orderservice;
	@Autowired
	private ShippingService shippingservice;
	/**
	 * 分页列出所有的订单
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "listOrderByPage", method = RequestMethod.POST,consumes = "application/json")
	public JSONArray ListOrderByPage(@RequestBody JSONObject obj,HttpServletResponse response) throws IOException{
		int pageIndex = obj.getInt("pageIndex");//页面index
		int pageSize = obj.getInt("pageSize");//页面size
		int custId = obj.getInt("custId");//页面size
		JSONArray result = orderservice.ListOrderByPage(pageIndex,pageSize,custId);
		if(result.size()==0)
		{
			response.setStatus(204);
		}
		return JSONArray.fromObject(result);
	}
	/**
	 * 直接下单,不经过购物车
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "createOrderDirectly", method = RequestMethod.POST,consumes = "application/json")
	public JSONObject CreateOrderDirectly(@RequestBody JSONObject obj,
			HttpServletResponse response) throws IOException{
		int prodAmount = obj.getInt("prodAmount");//商品购买数量
		int addressId = obj.getInt("addressId");//采用的地址模板
		int customerId = obj.getInt("custId");//用户ID
		int skuId = obj.getInt("skuId");
		JSONObject result = orderservice.CreateDirectly(prodAmount,addressId,customerId,skuId);
		if(result == null)
		{
			response.setStatus(202);
		}
		if(result.containsKey("message"))
		{
			response.setStatus(301);
		}
		return result;
	}
	/**
	 * 根据订单填写订单信息，确认订单
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "confirmOrder", method = RequestMethod.POST,consumes = "application/json")
	public void ConfirmOrder(@RequestBody JSONObject obj) throws IOException{
		BigDecimal orderShipping = shippingservice.getOrderShipping(obj.getInt("orderId"));
		orderservice.ConfirmOrder(obj,orderShipping);
	}
	/**
	 * 对订单进行付款
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "payOrder", method = RequestMethod.POST,consumes = "application/json")
	public Charge PayOrder(@RequestBody JSONObject obj) throws IOException{
		return orderservice.RequestPayOrder(obj);
	}
	/**
	 * 通过购物车下单
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "createByCartitem", method = RequestMethod.POST,consumes = "application/json")
	public JSONObject createByCartitem(@RequestBody JSONObject obj,
			HttpServletResponse response) throws IOException{
		int custId = obj.getInt("custId");//用户ID
		int addressId = obj.getInt("addressId");//采用的地址模板
		JSONObject result = orderservice.CreateByCartitem(custId,addressId);//根据用户UUID查找它的购物车下单
		if(result == null)
		{
			response.setStatus(202);
		}
		if(result.containsKey("message"))
		{
			response.setStatus(301);
		}
		return result;
	}
	/**
	 * 删除订单(伪删除操作)
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "RemoveOrder", method = RequestMethod.POST,consumes = "application/json")
	public void RemoveOrder(@RequestBody JSONObject obj,HttpServletResponse response) throws IOException{
		response.setStatus(orderservice.FalseRemoveOrder(obj.getInt("orderId")));	
	}
	/**
	 * 取消订单
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "cancelOrder", method = RequestMethod.POST,consumes = "application/json")
	public void CancelOrder(@RequestBody JSONObject obj,HttpServletResponse response) throws IOException{
		int orderId = obj.getInt("orderId");
		int result = orderservice.CancelOrder(orderId);
		response.setStatus(result);
	}
	
}
