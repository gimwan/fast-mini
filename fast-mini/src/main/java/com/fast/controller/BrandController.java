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
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MUser;
import com.fast.service.IBrandMaintService;
import com.fast.service.IBrandService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 品牌
 * @author J
 *
 */
@RequestMapping(value = "/brand", produces = "application/json; charset=utf-8")
@Controller
public class BrandController {
	
	@Autowired
	IBrandService iBrandService;
	
	@Autowired
	IBrandMaintService iBrandMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/brand", map);
	}
	
	/**
	 * 查询所有品牌
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/brand")
	@ResponseBody
	public String brand(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iBrandService.brand();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改品牌
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MBrand brand) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iBrandMaintService.changeBrand(brand, user);
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
	 * 删除品牌
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
			Result result = iBrandMaintService.deleteBrand(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
