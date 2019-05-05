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
import com.fast.service.IUserService;

import net.sf.json.JSONObject;

/**
 * 用户管理
 * @author J
 *
 */
@RequestMapping(value = "/user", produces = "application/json; charset=utf-8")
@Controller
public class UserController {
	
	@Autowired
	IUserService iUserService;
	
	@RequestMapping("")
	public ModelAndView userView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("system/user", map);
	}
	
	/**
	 * 查询所有用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/user")
	@ResponseBody
	public String user(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iUserService.user();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
