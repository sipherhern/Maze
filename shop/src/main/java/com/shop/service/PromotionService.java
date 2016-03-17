package com.shop.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.entity.Promotion;

@SuppressWarnings("rawtypes")
@Service
public interface PromotionService {

	/**
	 * 添加促销
	 * 
	 * @param promotion
	 * @param prod_id
	 * @return
	 * @throws Exception
	 */
	public int addPromotion(Promotion promotion, int prod_id) throws Exception;

	/**
	 * 修改促销
	 * 
	 * @param promotion
	 * @param prod_id
	 * @return
	 * @throws Exception
	 */

	public int updatePromotion(Promotion promotion, int prod_id)
			throws Exception;

	/**
	 * 删除促销
	 * 
	 * @param prom_id
	 * @return
	 * @throws Exception
	 */

	public int deletePromotion(int prom_id) throws Exception;

	/**
	 * 获取某店铺促销活动列表分页
	 * 
	 * @param shop_id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */

	public List getPromotionByPage(int shop_id, int pageIndex, int pageSize)
			throws Exception;

	/**
	 * 获取所有促销活动分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getAllPromByPage(int pageIndex, int pageSize) throws Exception;

	/**
	 * 获取首页的促销活动
	 * 
	 * @return
	 * @throws Exception
	 */
	public List getPromToHomepage() throws Exception;

	/**
	 * 判断促销是否过期
	 * 
	 * @param start
	 * @return
	 * @throws Exception
	 */

	public boolean judgeValidity(Date start) throws Exception;

	/**
	 * 更新促销是否过期
	 * 
	 * @param promotion
	 * @return
	 * @throws Exception
	 */

	public int updateValidity(Promotion promotion) throws Exception;
	
	
}
