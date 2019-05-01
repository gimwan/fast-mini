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
import com.fast.service.IConfigService;

import net.sf.json.JSONObject;

/**
 * 系统参数
 * @author J
 *
 */
@RequestMapping(value = "/config", produces = "application/json; charset=utf-8")
@Controller
public class ConfigController {
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	@Autowired
	IConfigService iConfigService;
	
	@RequestMapping("")
	public ModelAndView configView(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("system/config", map);
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public String config(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iConfigService.miniProgramConfig();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
