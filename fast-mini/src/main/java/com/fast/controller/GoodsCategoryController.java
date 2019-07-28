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
import com.fast.base.data.entity.MMicropage;
import com.fast.base.page.PagingView;
import com.fast.service.IGoodsCategoryService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 分类
 * @author J
 *
 */
@RequestMapping(value = "/goodscategory", produces = "application/json; charset=utf-8")
@Controller
public class GoodsCategoryController {
	
	@Autowired
	IGoodsCategoryService iGoodsCategoryService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/goodscategory", map);
	}
	
	/**
	 * 按等级查询分类
	 * @param request
	 * @param response
	 * @param micropage
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response, MMicropage micropage) {
		String r = "";
		
		try {
			String grade = request.getParameter("grade");
			String parentid = request.getParameter("parentid");
			String pageNo = request.getParameter("pageno");
			String pageSize = request.getParameter("pagesize");
			if (Common.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (Common.isEmpty(pageSize)) {
				pageSize = "15";
			}
			if (Common.isEmpty(grade)) {
				grade = "0";
			}
			if (Common.isEmpty(parentid)) {
				parentid = "0";
			}
			PagingView page = new PagingView(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize).intValue());
			
			Result result = iGoodsCategoryService.list(page, Integer.valueOf(grade.trim()), Integer.valueOf(parentid.trim()));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	@RequestMapping("/category")
	@ResponseBody
	public String category(HttpServletRequest request, HttpServletResponse response, MMicropage micropage) {
		String r = "";
		
		try {			
			Result result = iGoodsCategoryService.category();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
