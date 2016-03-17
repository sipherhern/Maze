package com.shop.service;

import java.util.List;

import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.entity.SkuProd;
import com.shop.entity.vo.ReturnProduct;



public  interface CategoryService {

	/**
	 * 测试
	 * @param test
	 */
	public void test(String test);
	
	/*************************************  PC  ********************************************/

	/**
	 *  添加商品分类
	 * @param cates
	 * @param shopUuid
	 * @param lists
	 * @param skuprods 
	 * @param product 
	 */
	public ReturnProduct addcate(List<Category> cates, String shopUuid,
			List<List<String>> lists, List<SkuProd> skuprods, Product product);
	
	/**
	 * 全局  查找  子分类
	 * @return
	 */
	public List<Category> findsku1Bycate();

	/**
	 * 全局  查找  子分类
	 * @param kindId
	 * @return
	 */
	public List<Category> findcateChildren(String kindId);

	/**
	 * 全局  根据  cate3  查找  sku1 分类   
	 * @param kindId
	 * @return
	 */
	public List<Category> findsku1Bycate(String kindId);
	
	
	
	/*************************************  UI  ********************************************/

	/**
	 * 查找当前店铺的所有分类
	 * @param shopUuid
	 * @return
	 */
	public List<Category> findCateByShop(String shopUuid);



	/**
	 * 根据商品sku  获取  价格 数量
	 * @param prodUuid
	 * @param kindId
	 * @return
	 */
	public List<SkuProd> findSkuProdBySku(String prodUuid, int kindId);

	/**
	 * 根据  商品找  所有 的 sku
	 * @param prodUuid
	 * @return
	 */
	public List<SkuProd> getSkuByProd(String prodUuid);

}
