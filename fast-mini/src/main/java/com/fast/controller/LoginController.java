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
import com.fast.service.IUserService;
import com.fast.system.RedisCache;
import com.fast.util.Common;

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
	
	@Autowired
	IConfigService iConfigService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("login", map);
	}
	
	/**
	 * 登入
	 * @param request
	 * @param response
	 * @return
	 */
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
				String sessionid = request.getSession().getId();
				RedisCache.set(sessionid, result.getData());
				
				Result re = iConfigService.queryConfigValueByCode("7001");
				if (Common.isActive(re)) {
					String value = (String) re.getData();
					Integer extsystem = 0;
					if (value != null && "1".equals(value.trim())) {
						extsystem = 1;
					}
					request.getSession().setAttribute("extsystem", extsystem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		Result result = new Result();
		
		try {
			request.getSession().removeAttribute("user");
			String sessionid = request.getSession().getId();
			RedisCache.remove(sessionid);
			
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		r = jsonObject.toString();
		
		return r;
	}

}
