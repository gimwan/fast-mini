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
import com.fast.base.data.entity.MEmployee;
import com.fast.base.data.entity.MUser;
import com.fast.service.IEmployeeMaintService;
import com.fast.service.IEmployeeService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 员工管理
 * @author J
 *
 */
@RequestMapping(value = "/employee", produces = "application/json; charset=utf-8")
@Controller
public class EmployeeController {
	
	@Autowired
	IEmployeeService iEmployeeService;
	
	@Autowired
	IEmployeeMaintService iEmployeeMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("data/employee", map);
	}
	
	/**
	 * 查询所有员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/employee")
	@ResponseBody
	public String employee(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iEmployeeService.employee();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改员工
	 * @param request
	 * @param response
	 * @param employee
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MEmployee employee) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iEmployeeMaintService.changeEmployee(employee, user);
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
	 * 删除员工
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
			Result result = iEmployeeMaintService.deleteEmployee(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
