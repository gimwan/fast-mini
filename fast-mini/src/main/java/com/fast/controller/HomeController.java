package com.fast.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fast.base.data.entity.MUser;
import com.fast.system.RedisCache;

/**
 * 主页
 * @author J
 *
 */
@RequestMapping(value = "/home", produces = "application/json; charset=utf-8")
@Controller
public class HomeController {
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		String sessionid = request.getSession().getId();
		MUser user = (MUser) RedisCache.retake(sessionid);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		return new ModelAndView("home", map);
	}

}
