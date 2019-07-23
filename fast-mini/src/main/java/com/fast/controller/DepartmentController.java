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
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDepartmentMaintService;
import com.fast.service.IDepartmentService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 门店管理
 * @author J
 *
 */
@RequestMapping(value = "/department", produces = "application/json; charset=utf-8")
@Controller
public class DepartmentController {
	
	@Autowired
	IDepartmentService iDepartmentService;
	
	@Autowired
	IDepartmentMaintService iDepartmentMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("data/department", map);
	}
	
	/**
	 * 查询所有门店
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/department")
	@ResponseBody
	public String department(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iDepartmentService.department();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改门店
	 * @param request
	 * @param response
	 * @param department
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MDepartment department) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iDepartmentMaintService.changeDepartment(department, user);
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
	 * 删除门店
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
			Result result = iDepartmentMaintService.deleteDepartment(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
