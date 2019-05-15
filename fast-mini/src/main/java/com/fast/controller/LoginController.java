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
import com.fast.system.RedisCache;

import net.sf.json.JSONObject;

/**
 * 登录
 * @author J
 *
 */
@RequestMapping(value = "", produces = "application/json; charset=utf-8")
@Controller
public class LoginController {
	
	@Autowired
	IUserService IUserService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
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
				String sessionid = request.getSession().getId();
				RedisCache.set(sessionid, result.getData());
				//request.getSession().setAttribute("user",result.getData());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
