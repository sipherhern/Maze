package com.shop.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.controller.base.RequestContext;
import com.shop.entity.PictureRelate;
import com.shop.entity.Pictures;
import com.shop.entity.Product;
import com.shop.entity.vo.PageSet;
import com.shop.service.ProductService;
import com.shop.util.JSONConfig;

@Component
@RequestMapping(value = "/user/product")
public class ProductController extends RequestContext {

	@Autowired
	private ProductService productservice;

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public void test() {

		String test = "product : test";

		productservice.test(test);

		System.out.println("ddd");
	}

	/**
	 * 查询 某一 店铺下 的 所有商品
	 */
	@ResponseBody
	@RequestMapping(value = "/findByshop", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray findByshop(@RequestBody JSONObject json,
			HttpServletResponse res) {

		String shopUuid = null;
		String pageIndex = null;
		Integer pageSize = null;
		try {
			shopUuid = json.getString("shopUuid");

			pageIndex = json.getString("pageIndex");

			pageSize = json.optInt("pageSize");
		} catch (Exception e) {

			res.setStatus(403);
			e.printStackTrace();

			return null;
		}

		PageSet<Product> productpageset = null;
		try {
			productpageset = productservice.findByshop(shopUuid, pageIndex,
					pageSize);
		} catch (Exception e) {
			res.setStatus(505);
			e.printStackTrace();
			return null;
		}

		/*
		 * 封装 Pageset
		 */

		List<Product> productlist = productpageset.getList();
		JSONArray jasonarray = new JSONArray();

		for (int i = 0; i < productlist.size(); i++) {

			Product product = productlist.get(i);
			/**
			 * 在将每个对象转为json对象的时候用setExcludes函数将级联的属性去除掉
			 */
			JSONObject returnobject = JSONObject.fromObject(product,
					JSONConfig.prodConfig());

			/**
			 * 商品图片
			 */

			JSONArray picarray = new JSONArray();
			if (product.getPictureRelates() != null) {
				for (PictureRelate pr : product.getPictureRelates()) {
					Pictures pic = new Pictures(pr.getPictures());

					JSONObject picobject = JSONObject.fromObject(pic,
							JSONConfig.pictureConfig());
					picarray.add(picobject);
				}
			}
			returnobject.put("picarray", picarray);

			jasonarray.add(returnobject);
		}

		/**
		 * 在将每个对象转为json对象的时候用setExcludes函数将级联的属性去除掉
		 */
		JSONObject jsonobject = JSONObject.fromObject(productpageset,
				JSONConfig.pageSetConfig());
		/**
		 * 封装 两个 json
		 */
		JSONObject returnjson = new JSONObject();
		returnjson.put("prodlist", jasonarray);
		returnjson.put("pageSet", jsonobject);

		return jasonarray;
	}

	/**
	 * 查询 商品详情
	 */
	@ResponseBody
	@RequestMapping(value = "/findProductDetail", method = RequestMethod.POST, consumes = "application/json")
	public JSONObject findProductDetail(@RequestBody JSONObject json,
			HttpServletResponse res) {

		String prodUuid = json.getString("prodUuid");
		int custId = json.getInt("custId");
		Product product = productservice.findProductDetail(prodUuid);

		JSONObject returnobject = JSONObject.fromObject(product,
				JSONConfig.prodConfig());

		/**
		 * 商品图片
		 */

		JSONArray picarray = new JSONArray();
		if (product.getPictureRelates() != null) {
			for (PictureRelate pr : product.getPictureRelates()) {
				Pictures pic = new Pictures(pr.getPictures());
				/**
				 * 获取 logo 照片
				 */
				if (pic.getPicType().equals("product")) {
					JSONObject picobject = JSONObject.fromObject(pic,
							JSONConfig.pictureConfig());
					picarray.add(picobject);
				}
			}
		}
		/**
		 * 添加返回 所在 商铺
		 */

		returnobject.put("shopId", product.getShop().getShopId());
		returnobject.put("shopUuId", product.getShop().getShopUuid());
		returnobject.put("picarray", picarray);


		try {
			// 商品是否收藏
			returnobject.put("IsFollowed",
					productservice.getFollowed(product.getProdId(), custId));
			
			// 商品促销信息
			returnobject.put("IsPromotion", productservice.isPromotion(product));
			returnobject.put("promPrice", (product.getPromotion()==null)? 0 :product.getPromotion().getPromPrice());
			

		} catch (Exception e) {
			res.setStatus(403);
			return null;
		}
		return returnobject;
	}

	/**
	 * 获取 图文详情
	 */
	@ResponseBody
	@RequestMapping(value = "/getProdPicDetail", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray getProdPicDetail(@RequestBody JSONObject json,
			HttpServletResponse res) {

		String prodUuid = json.getString("prodUuid");

		Product product = productservice.findProductDetail(prodUuid);
		/**
		 * 商品图片
		 */

		JSONArray picarray = new JSONArray();
		if (product.getPictureRelates() != null) {
			for (PictureRelate pr : product.getPictureRelates()) {
				Pictures pic = new Pictures(pr.getPictures());
				/**
				 * 图文详情
				 */
				if (pic.getPicType().equals("productdetail")) {
					JSONObject picobject = JSONObject.fromObject(pic,
							JSONConfig.pictureConfig());
					picarray.add(picobject);
				}
			}
		}

		return picarray;
	}



	/**
	 * 获取热门商品
	 * 
	 * @param json
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPopularProduct", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray getPopularProduct(@RequestBody JSONObject json,
			HttpServletResponse res) {

		int pageSize = json.getInt("pageSize");

		String pageIndex = json.getString("pageIndex");

		PageSet<Product> prodPageSet = productservice.getPopularProduct(
				pageSize, pageIndex);

		List<Product> productlist = prodPageSet.getList();
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < productlist.size(); i++) {
			Product product = productlist.get(i);

			JSONObject jsonproduct = JSONObject.fromObject(product,
					JSONConfig.prodConfig());
			/**
			 * 商品图片
			 */

			JSONArray picarray = new JSONArray();
			if (product.getPictureRelates() != null) {
				for (PictureRelate pr : product.getPictureRelates()) {
					Pictures pic = new Pictures(pr.getPictures());

					JSONObject picobject = JSONObject.fromObject(pic,
							JSONConfig.pictureConfig());
					picarray.add(picobject);
				}
			}
			jsonproduct.put("picarray", picarray);

			/**
			 * 添加返回 所在 商铺
			 */

			jsonproduct.put("shopId", product.getShop().getShopId());

			jsonarray.add(jsonproduct);
		}

		JSONObject pagejson = JSONObject.fromObject(prodPageSet,
				JSONConfig.pageSetConfig());

		JSONObject returnobject = new JSONObject();

		returnobject.put("pageSet", pagejson);
		returnobject.put("productlist", jsonarray);

		return jsonarray;
	}

	/**
	 * 获取新品
	 * 
	 * @param json
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getNewProduct", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray getNewProduct(@RequestBody JSONObject json,
			HttpServletResponse res) {

		int pageSize = json.getInt("pageSize");

		String pageIndex = json.getString("pageIndex");

		PageSet<Product> prodPageSet = productservice.getNewProduct(pageSize,
				pageIndex);

		List<Product> productlist = prodPageSet.getList();
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < productlist.size(); i++) {

			Product product = productlist.get(i);
			JSONObject jsonproduct = JSONObject.fromObject(product,
					JSONConfig.prodConfig());

			/**
			 * 商品图片
			 */

			JSONArray picarray = new JSONArray();
			if (product.getPictureRelates() != null) {
				for (PictureRelate pr : product.getPictureRelates()) {
					Pictures pic = new Pictures(pr.getPictures());

					JSONObject picobject = JSONObject.fromObject(pic,
							JSONConfig.pictureConfig());
					picarray.add(picobject);
				}
			}
			jsonproduct.put("picarray", picarray);

			/**
			 * 添加返回 所在 商铺
			 */

			jsonproduct.put("shopId", product.getShop().getShopId());
			jsonarray.add(jsonproduct);
		}

		JSONObject pagejson = JSONObject.fromObject(prodPageSet,
				JSONConfig.pageSetConfig());

		JSONObject returnobject = new JSONObject();

		returnobject.put("pageSet", pagejson);
		returnobject.put("productlist", jsonarray);

		return jsonarray;
	}

	/**
	 * 获取促销商品
	 * 
	 * @param json
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPromotionProduct", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray getPromotionProduct(@RequestBody JSONObject json,
			HttpServletResponse res) {

		int pageSize = json.getInt("pageSize");

		String pageIndex = json.getString("pageIndex");

		List<Product> productlist = productservice.getPromotionProduct(
				pageSize, pageIndex);
		JSONArray jsonarray = new JSONArray();
		for (int i = 0; i < productlist.size(); i++) {

			Product product = productlist.get(i);
			JSONObject jsonproduct = JSONObject.fromObject(product,
					JSONConfig.prodConfig());

			/**
			 * 商品图片
			 */

			JSONArray picarray = new JSONArray();
			if (product.getPictureRelates() != null) {
				for (PictureRelate pr : product.getPictureRelates()) {
					Pictures pic = new Pictures(pr.getPictures());

					JSONObject picobject = JSONObject.fromObject(pic,
							JSONConfig.pictureConfig());
					picarray.add(picobject);
				}
			}
			jsonproduct.put("picarray", picarray);

			/**
			 * 添加返回 所在 商铺
			 */

			jsonproduct.put("shopId", product.getShop().getShopId());
			jsonarray.add(jsonproduct);
		}
		return jsonarray;
	}

	/**
	 * 模糊查找商品
	 * 
	 * @param json
	 * @param res
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fuzzyFindProduct", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray fuzzyFindProducts(@RequestBody JSONObject json,
			HttpServletResponse res) {
		String keywords = json.getString("keywords");
		List<Product> productlist = productservice.fuzzyFindProducts(keywords);

		JSONArray prodarray = new JSONArray();
		for (int i = 0; i < productlist.size(); i++) {
			JSONObject object = new JSONObject();
			object.put("prodId", productlist.get(i).getProdId());
			object.put("prodUuid",productlist.get(i).getProdUuid());
			object.put("prodName", productlist.get(i).getProdName());
			prodarray.add(object);
		}

		return prodarray;
	}

	

}

