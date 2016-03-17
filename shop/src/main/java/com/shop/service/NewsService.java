package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shop.entity.News;
import com.shop.entity.Pictures;

@SuppressWarnings("rawtypes")
@Service
public interface NewsService {

	/**
	 * 添加资讯
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */

	public int addNews(News news) throws Exception;

	/**
	 * 删除资讯
	 * 
	 * @param new_id
	 * @return
	 * @throws Exception
	 */

	public int deleteNews(int new_id) throws Exception;

	/**
	 * 修改资讯
	 * 
	 * @param news
	 * @return
	 * @throws Exception
	 */

	public int updateNews(News news) throws Exception;

	/** 获取资讯分页列表
	 * 
	 * @param shop_id
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List getNewsPage(int shop_id, int pageIndex, int pageSize) throws Exception;

	/**
	 * 添加广告
	 * 
	 * @param news
	 * @param picture
	 * @param shop
	 * @return
	 * @throws Exception
	 */

	public int addAdvertise(News news, ArrayList<Pictures> picture)
			throws Exception;

	/**
	 * 更新广告
	 * 
	 * @param news
	 * @param picture
	 * @param shop
	 * @return
	 * @throws Exception
	 */

	public int updateAdvertise(News news, ArrayList<Pictures> picture)
			throws Exception;

	/**
	 * 删除广告
	 * 
	 * @param new_id
	 * @return
	 * @throws Exception
	 */

	public int delAdvertise(int new_id) throws Exception;

	/**
	 * 获取广告分页
	 * 
	 * @param shop
	 * @return
	 * @throws Exception
	 */

	public List getAdvertisePage(int shop_id) throws Exception;

	/** 获得首页的productUuid
	 * 
	 * @param shop_id
	 * @return
	 * @throws Exception
	 */
	public String getProductUuid(int shop_id) throws Exception;

	/**
	 * 商城首页显示的图片
	 * 
	 * @return
	 */
	public List getAdvertiseToHomePage() throws Exception;

}
