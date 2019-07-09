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
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MUser;
import com.fast.service.IConfigMaintService;
import com.fast.service.IConfigService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 系统参数
 * @author J
 *
 */
@RequestMapping(value = "/config", produces = "application/json; charset=utf-8")
@Controller
public class ConfigController {
	
	@Autowired
	IConfigService iConfigService;
	
	@Autowired
	IConfigMaintService iConfigMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("system/config", map);
	}
	
	/**
	 * 查询所有参数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/config")
	@ResponseBody
	public String config(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iConfigService.config();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改参数
	 * @param request
	 * @param response
	 * @param config
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MConfig config) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iConfigMaintService.changeConfig(config, user);
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
