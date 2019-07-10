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
import com.fast.service.IVipService;

import net.sf.json.JSONObject;

/**
 * 会员
 * @author J
 *
 */
@RequestMapping(value = "/vip", produces = "application/json; charset=utf-8")
@Controller
public class VipController {
	
	@Autowired
	IVipService iVipService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("vip/vip", map);
	}
	
	/**
	 * 查询所有尺码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip")
	@ResponseBody
	public String vip(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iVipService.vip();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
