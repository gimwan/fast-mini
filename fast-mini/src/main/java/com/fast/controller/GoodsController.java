package com.fast.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fast.base.Result;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsdtl;
import com.fast.base.data.entity.MUser;
import com.fast.service.IGoodsMaintService;
import com.fast.service.IGoodsService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 商品
 * @author J
 *
 */
@RequestMapping(value = "/goods", produces = "application/json; charset=utf-8")
@Controller
public class GoodsController {
	
	@Autowired
	IGoodsService iGoodsService;
	
	@Autowired
	IGoodsMaintService iGoodsMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/goods", map);
	}

	/**
	 * 查询所有商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goods")
	@ResponseBody
	public String goods(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iGoodsService.goods();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改商品
	 * @param request
	 * @param response
	 * @param employee
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MGoods goods) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iGoodsMaintService.changeGoods(goods, user);
			} else {
				result.setMessage("当前登入者已失效");
			}
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 删除商品
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String id = request.getParameter("id");
			Result result = iGoodsMaintService.deleteGoods(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 上下架
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/onsale")
	@ResponseBody
	public String onsale(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = new Result();
			MUser user = Common.currentUser(request);
			if (user != null) {
				String id = request.getParameter("id");
				String onsale = request.getParameter("onsale");
				result = iGoodsMaintService.onsaleGoods(user, Integer.valueOf(id.trim()), Integer.valueOf(onsale.trim()));
			} else {
				result.setMessage("当前登入者已失效");
			}
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 商品主图/明细图
	 * @param request
	 * @param response
	 * @param goodsdtls
	 * @return
	 */
	@RequestMapping("/images")
	@ResponseBody
	public String images(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String goodsid = request.getParameter("goodsid");
			Result result = iGoodsService.goodsImages(Integer.valueOf(goodsid.trim()));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/images/save")
	@ResponseBody
	public String saveimages(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = new Result();
			MUser user = Common.currentUser(request);
			if (user != null) {
				String images = request.getParameter("images");
				result = iGoodsMaintService.goodsImages(user, images);
			} else {
				result.setMessage("当前登入者已失效");
			}
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
}
