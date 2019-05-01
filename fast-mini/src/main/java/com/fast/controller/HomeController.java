package com.fast.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页
 * @author J
 *
 */
@RequestMapping(value = "/home", produces = "application/json; charset=utf-8")
@Controller
public class HomeController {
	
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	@RequestMapping("")
	public ModelAndView home(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("home", map);
	}

}
