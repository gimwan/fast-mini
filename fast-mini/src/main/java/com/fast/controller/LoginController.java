package com.fast.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录
 * @author gimwan
 *
 */
@RequestMapping(value = "", produces = "application/json; charset=utf-8")
@Controller
public class LoginController {
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	@RequestMapping("")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("login", map);
	}

}
