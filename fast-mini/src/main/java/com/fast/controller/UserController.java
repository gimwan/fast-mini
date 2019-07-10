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
import com.fast.base.data.entity.MUser;
import com.fast.service.IUserMaintService;
import com.fast.service.IUserService;
import com.fast.system.RedisCache;
import com.fast.util.Common;

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
	
	@Autowired
	IUserMaintService iUserMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
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
	
	/**
	 * 修改用户
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MUser user) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser currentUser = Common.currentUser(request);
			if (currentUser != null) {
				result = iUserMaintService.changeUser(user, currentUser);
			} else {
				result.setMessage("当前登入者已失效");
			}
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
			
			if (result.getErrcode().intValue() == 0) {
				if (result.getId().intValue() == currentUser.getId().intValue()) {
					request.getSession().setAttribute("user",result.getData());
					String sessionid = request.getSession().getId();
					RedisCache.set(sessionid, result.getData());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 删除用户
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String id = request.getParameter("id");
			String sessionid = request.getSession().getId();
			MUser mUser = (MUser) RedisCache.retake(sessionid);
			Result result = iUserMaintService.deleteUser(Integer.valueOf(id), mUser);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
