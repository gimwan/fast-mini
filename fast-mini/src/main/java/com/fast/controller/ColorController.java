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
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MUser;
import com.fast.base.page.PagingView;
import com.fast.service.IColorMaintService;
import com.fast.service.IColorService;
import com.fast.system.RedisCache;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 颜色
 * @author J
 *
 */
@RequestMapping(value = "/color", produces = "application/json; charset=utf-8")
@Controller
public class ColorController {
	
	@Autowired
	IColorService iColorService;
	
	@Autowired
	IColorMaintService iColorMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/color", map);
	}
	
	/**
	 * 查询所有颜色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/color")
	@ResponseBody
	public String color(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iColorService.color();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改颜色
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MColor color) {
		String r = "";
		
		try {
			String sessionid = request.getSession().getId();
			MUser mUser = (MUser) RedisCache.retake(sessionid);
			
			Result result = iColorMaintService.changeColor(color, mUser);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 删除颜色
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
			Result result = iColorMaintService.deleteColor(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
