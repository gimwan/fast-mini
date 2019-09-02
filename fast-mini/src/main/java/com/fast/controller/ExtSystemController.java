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
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MUser;
import com.fast.service.IExtSystemMaintService;
import com.fast.service.IExtSystemService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 应用接口
 * @author J
 *
 */
@RequestMapping(value = "/extsystem", produces = "application/json; charset=utf-8")
@Controller
public class ExtSystemController {
	
	@Autowired
	IExtSystemService iExtSystemService;
	
	@Autowired
	IExtSystemMaintService iExtSystemMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("system/extsystem", map);
	}
	
	/**
	 * 修改应用接口
	 * @param request
	 * @param response
	 * @param role
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MExtsystem extsystem) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iExtSystemMaintService.changeExtSystem(extsystem, user);
			} else {
				result.setErrcode(Integer.valueOf(88));
				result.setMessage("当前登入者已失效");
			}
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 删除应用接口
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
			Result result = iExtSystemMaintService.deleteExtSystem(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
