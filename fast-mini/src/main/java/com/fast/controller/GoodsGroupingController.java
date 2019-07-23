package com.fast.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fast.base.Result;
import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MUser;
import com.fast.service.IGoodsGroupingMaintService;
import com.fast.service.IGoodsGroupingService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 分组
 * @author J
 *
 */
@RequestMapping(value = "/goodsgrouping", produces = "application/json; charset=utf-8")
@Controller
public class GoodsGroupingController {
	
	@Autowired
	IGoodsGroupingService iGoodsGroupingService;
	
	@Autowired
	IGoodsGroupingMaintService iGoodsGroupingMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/goodsgrouping", map);
	}
	
	/**
	 * 查询所有分组
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goodsgrouping")
	@ResponseBody
	public String goodsgrouping(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iGoodsGroupingService.goodsGrouping();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改分组
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MGoodsgrouping goodsgrouping) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iGoodsGroupingMaintService.changeGoodsGrouping(goodsgrouping, user);
			} else {
				result.setErrcode(Integer.valueOf(88));
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
	 * 删除分组
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
			Result result = iGoodsGroupingMaintService.deleteGoodsGrouping(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
