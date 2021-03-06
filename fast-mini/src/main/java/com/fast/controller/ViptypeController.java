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
import com.fast.base.data.entity.MViptype;
import com.fast.service.IViptypeMaintService;
import com.fast.service.IViptypeService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 会员等级
 * @author J
 *
 */
@RequestMapping(value = "/viptype", produces = "application/json; charset=utf-8")
@Controller
public class ViptypeController {
	
	@Autowired
	IViptypeService iViptypeService;
	
	@Autowired
	IViptypeMaintService iViptypeMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("vip/viptype", map);
	}
	
	/**
	 * 查询所有会员等级
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/viptype")
	@ResponseBody
	public String viptype(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iViptypeService.viptype();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改会员等级
	 * @param request
	 * @param response
	 * @param viptype
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MViptype viptype) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iViptypeMaintService.changeVipType(viptype, user);
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
	 * 删除会员等级
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
			Result result = iViptypeMaintService.deleteVipType(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
}
