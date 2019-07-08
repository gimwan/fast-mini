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
import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MUser;
import com.fast.base.page.PagingView;
import com.fast.service.IPatternMaintService;
import com.fast.service.IPatternService;
import com.fast.system.RedisCache;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 版型
 * @author J
 *
 */
@RequestMapping(value = "/pattern", produces = "application/json; charset=utf-8")
@Controller
public class PatternController {
	
	@Autowired
	IPatternService iPatternService;
	
	@Autowired
	IPatternMaintService iPatternMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/pattern", map);
	}
	
	/**
	 * 查询所有版型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pattern")
	@ResponseBody
	public String pattern(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iPatternService.pattern();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改版型
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MPattern pattern) {
		String r = "";
		
		try {
			String sessionid = request.getSession().getId();
			MUser mUser = (MUser) RedisCache.retake(sessionid);
			
			Result result = iPatternMaintService.changePattern(pattern, mUser);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 删除版型
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
			Result result = iPatternMaintService.deletePattern(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
