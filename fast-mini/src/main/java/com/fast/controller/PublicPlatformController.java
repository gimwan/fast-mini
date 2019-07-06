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
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MUser;
import com.fast.service.IPublicPlatformMaintService;
import com.fast.service.IPublicPlatformService;
import com.fast.system.RedisCache;

import net.sf.json.JSONObject;

/**
 * 公众号
 * @author J
 *
 */
@RequestMapping(value = "/publicplatform", produces = "application/json; charset=utf-8")
@Controller
public class PublicPlatformController {
	
	@Autowired
	IPublicPlatformService iPublicPlatformService;
	
	@Autowired
	IPublicPlatformMaintService iPublicPlatformMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("application/publicplatform", map);
	}
	
	/**
	 * 查询所有公众号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/publicplatform")
	@ResponseBody
	public String publicplatform(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iPublicPlatformService.publicplatform();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改公众号
	 * @param request
	 * @param response
	 * @param publicplatform
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MPublicplatform publicplatform) {
		String r = "";
		
		try {
			String sessionid = request.getSession().getId();
			MUser user = (MUser) RedisCache.retake(sessionid);
			
			Result result = iPublicPlatformMaintService.changePublicplatform(publicplatform, user);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
