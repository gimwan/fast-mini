package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IGoodsService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 商品
 * @author J
 *
 */
@Controller
public class Goods extends MiniMaster {
	
	@Autowired
	IGoodsService iGoodsService;
	
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goods/detail")
	@ResponseBody
	public String defaultlogin(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String openid = request.getParameter("openid");
			String appid = request.getParameter("appid");
			String id = request.getParameter("id");
			if (Common.isEmpty(id)) {
				id = "0";
			}
			if (Common.isEmpty(openid)) {
				openid = "";
			}
			if (Common.isEmpty(appid)) {
				appid = "";
			}
			
			r = iGoodsService.goodsDetail(Integer.valueOf(id), openid, appid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 商品库存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goods/stock")
	@ResponseBody
	public String sku(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String openid = request.getParameter("openid");
			String appid = request.getParameter("appid");
			String id = request.getParameter("goodsid");
			if (Common.isEmpty(id)) {
				id = "0";
			}
			if (Common.isEmpty(openid)) {
				openid = "";
			}
			if (Common.isEmpty(appid)) {
				appid = "";
			}
			
			r = iGoodsService.queryGoodsStock(Integer.valueOf(id), appid, openid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
