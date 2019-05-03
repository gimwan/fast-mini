package com.fast.controller;

import java.util.HashMap;
import java.util.Map;

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
 * 登录
 * @author J
 *
 */
@RequestMapping(value = "", produces = "application/json; charset=utf-8")
@Controller
public class LoginController {
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	@Autowired
	IUserService IUserService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("login", map);
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String code = request.getParameter("code");
			String password = request.getParameter("password");
			Result result = IUserService.checkLogin(code, password);
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
			
			if (result.getErrcode().intValue() == 0) {
				request.getSession().setAttribute("user",result.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
