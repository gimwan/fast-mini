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
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MUser;
import com.fast.base.page.PagingView;
import com.fast.service.ISizeMaintService;
import com.fast.service.ISizeService;
import com.fast.system.RedisCache;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 尺码
 * @author J
 *
 */
@RequestMapping(value = "/size", produces = "application/json; charset=utf-8")
@Controller
public class SizeController {
	
	@Autowired
	ISizeService iSizeService;
	
	@Autowired
	ISizeMaintService iSizeMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("goods/size", map);
	}
	
	/**
	 * 查询所有尺码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/size")
	@ResponseBody
	public String size(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iSizeService.size();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改尺码
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MSize size) {
		String r = "";
		
		try {
			String sessionid = request.getSession().getId();
			MUser mUser = (MUser) RedisCache.retake(sessionid);
			
			Result result = iSizeMaintService.changeSize(size, mUser);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 删除尺码
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
			Result result = iSizeMaintService.deleteSize(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
