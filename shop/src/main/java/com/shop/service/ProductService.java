package com.shop.service;

import java.sql.SQLException;
import java.util.List;

import com.shop.entity.Order;
import com.shop.entity.Product;
import com.shop.entity.SkuProd;
import com.shop.entity.vo.PageSet;
import com.shop.entity.vo.ReturnProduct;


public  interface ProductService {

	/**
	 * 测试
	 * @param test
	 */
	public void test(String test);

	/*************************************  PC  ********************************************/

	/**
	 * 发布商品
	 * @param returnproduct
	 */
	public void addproduct(ReturnProduct returnproduct);
	

	/**
	 *  待发货的商品
	 * @param pageSize
	 * @param pageIndex
	 * @param shopUuid
	 * @return
	 */
	public PageSet<Order> getwaitdeliver(int pageSize, String pageIndex,
			String shopUuid);


	/**
	 * 获取审核中 的 商品
	 * @param pageSize
	 * @param pageIndex
	 * @param shopUuid
	 * @return
	 */
	public PageSet<Product> getcheckingProduct(int pageSize, String pageIndex,
			String shopUuid);


	/**
	 * 获取仓库中 的 商品
	 * @param pageSize
	 * @param pageIndex
	 * @param shopUuid
	 * @return
	 */
	public PageSet<Product> getStorageProduct(int pageSize, String pageIndex,
			String shopUuid);


	/**
	 * 获取出售中 的 商品
	 * @param pageSize
	 * @param pageIndex
	 * @param shopUuid
	 * @return
	 */
	public PageSet<Product> getSellingProduct(int pageSize, String pageIndex,
			String shopUuid);


	/**
	 * 跟新商品详细信息
	 * @param product
	 * @return
	 */
	public boolean updateproductdetail(Product product);


	/**
	 * 跟新 skuprod
	 * @param product
	 * @param skuprod
	 * @return
	 */
	public boolean updateskuprod(Product product, SkuProd skuprod);


	/**
	 * 删除商品
	 * @param product
	 * @return
	 */
	public boolean delproduct(Product product);


	/**
	 * 上下架商品
	 * @param product
	 * @return
	 */
	public boolean updateproductstate(Product product);



	/*************************************  UI  ********************************************/
	
	/**
	 * 
	 * @param prodUuid
	 */
	public Product findProductDetail(String prodUuid);

	/**
	 * 根据  店铺   查询  所有商品
	 * @param shopUuid
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public PageSet<Product> findByshop(String shopUuid ,String pageIndex, int pageSize)throws SQLException;

	/**
	 * 1、获取热门商品 （多个商品）
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public PageSet<Product> getPopularProduct(int pageSize, String pageIndex);


	/**
	 * 2、获取新品	（多个商品）
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public PageSet<Product> getNewProduct(int pageSize, String pageIndex);


	/**
	 * 3、获取促销商品 （多个商品）
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public List<Product> getPromotionProduct(int pageSize, String pageIndex);
	
	/** 查看商品关注状态
	 * 
	 * @param shop_id
	 * @param cust_id
	 * @return
	 * @throws Exception
	 */
	public int getFollowed(int shop_id, int cust_id) throws Exception;
	
	/**
	 * 模糊查找商品
	 * @param keywords
	 * @return
	 */
	public List<Product> fuzzyFindProducts(String keywords);
	
	
	/** 查看商品促销状态
	 * 
	 */
	public boolean isPromotion(Product product) throws Exception;
}
