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
import com.fast.service.IRoleService;

import net.sf.json.JSONObject;

/**
 * 角色管理
 * @author J
 *
 */
@RequestMapping(value = "/role", produces = "application/json; charset=utf-8")
@Controller
public class RoleController {
	
	@Autowired
	IRoleService iRoleService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("system/role", map);
	}
	
	/**
	 * 查询所有角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/role")
	@ResponseBody
	public String role(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iRoleService.role();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
