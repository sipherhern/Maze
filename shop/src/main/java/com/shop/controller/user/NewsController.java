package com.shop.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.controller.base.RequestContext;
import com.shop.entity.News;
import com.shop.entity.PictureRelate;
import com.shop.entity.Pictures;
import com.shop.entity.Product;
import com.shop.service.NewsService;
import com.shop.service.ProductService;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("/user/news")
public class NewsController extends RequestContext {

	@Autowired
	private NewsService sysNewsService;

	@Autowired
	private ProductService productService;

	@ResponseBody
	@RequestMapping(value = "findNewsByPage", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray findNewsByPage(HttpServletResponse res,
			@RequestBody JSONObject json) throws Exception {
		int shop_id = json.getInt("shopId");
		int pageIndex = json.getInt("pageIndex");
		int pageSize = json.getInt("pageSize");
		json.remove("shopId");
		json.remove("pageIndex");
		json.remove("pageSize");
		Pictures pic = new Pictures();
		News news = new News();
		JSONArray jsonArray = new JSONArray();
		// get the result list
		try {
			List list = sysNewsService
					.getNewsPage(shop_id, pageIndex, pageSize);
			if (list.isEmpty()) {
				res.setStatus(204);
				return null;
			} else {
				for (Object object : list) {
					news = (News) object;
					json.put("newContent", news.getNewContent());
					json.put("newDate", news.getNewDate());
					jsonArray.add(json);
				}
				Product product = productService
						.findProductDetail(sysNewsService
								.getProductUuid(shop_id));
				for (PictureRelate pr : product.getPictureRelates()) {
					pic = pr.getPictures();
				}
				json.put("prodId", product.getProdId());
				json.put("prodUuid", product.getProdUuid());
				json.put("prodName", product.getProdName());
				json.put("prodPrice", product.getProdPrice());
				json.put("picUrl", pic.getPicUrl());

				jsonArray.add(json);
				res.setStatus(200);
				return jsonArray;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "getAdvertiseToHomePage", method = RequestMethod.POST, consumes = "application/json")
	public JSONArray getAdvertiseToHomePage(HttpServletResponse res)
			throws Exception {
		JSONArray jsonArray = new JSONArray();
		JSONObject json = new JSONObject();
		String picUrl = new String();
		int prodId = 0;
		String prodUuid = new String();
		List list = sysNewsService.getAdvertiseToHomePage();
		if (list.isEmpty()) {
			res.setStatus(204);
			return null;
		} else {
			for (Object object : list) {
				Object[] obj = (Object[]) object;
				picUrl = (String) obj[0];
				prodId = (int) obj[1];
				prodUuid = (String) obj[2];
				json.put("prodId",prodId);
				json.put("prodUuid",prodUuid);
				json.put("picUrl",picUrl);
				jsonArray.add(json);
			}
			
			res.setStatus(200);
			return jsonArray;
		}
	}
}
