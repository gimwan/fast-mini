package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IGroupbuyService;

import net.sf.json.JSONObject;

/**
 * 拼团
 * @author J
 *
 */
@Controller
public class Groupbuy extends MiniMaster {
	
	@Autowired
	IGroupbuyService iGroupbuyService;
	
	/**
	 * 拼团中
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/active")
	@ResponseBody
	public String active(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			r = iGroupbuyService.queryActiveGroupBuy(appid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 即将开始
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/soon")
	@ResponseBody
	public String soon(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			r = iGroupbuyService.querySoonGroupBuy(appid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 即将开始
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/detail")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String groupbuyid = request.getParameter("groupbuyid");
			String goodsid = request.getParameter("goodsid");
			r = iGroupbuyService.queryGroupbuyDetail(Integer.valueOf(groupbuyid), Integer.valueOf(goodsid));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
