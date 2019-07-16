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
import com.fast.service.IMicropageService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 微页面
 * @author J
 *
 */
@RequestMapping(value = "micropage", produces = "application/json; charset=utf-8")
@Controller
public class MicropageController {
	
	@Autowired
	IMicropageService iMicropageService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("application/micropage", map);
	}
	
	/**
	 * 微页面配置数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/data")
	@ResponseBody
	public String data(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String pageid = request.getParameter("pageid");
			if (Common.isEmpty(pageid)) {
				Result result = new Result();
				result.setMessage("pageid无效");
				JSONObject jsonObject = JSONObject.fromObject(result);
				return jsonObject.toString();
			}
			Result result = iMicropageService.queryPageData(Integer.valueOf(pageid.trim()));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
