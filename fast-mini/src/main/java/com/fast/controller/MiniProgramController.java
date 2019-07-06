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
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MUser;
import com.fast.service.IMiniProgramMaintService;
import com.fast.service.IMiniProgramService;
import com.fast.system.RedisCache;

import net.sf.json.JSONObject;

/**
 * 小程序
 * @author J
 *
 */
@RequestMapping(value = "/miniprogram", produces = "application/json; charset=utf-8")
@Controller
public class MiniProgramController {

	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IMiniProgramMaintService iMiniProgramMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("application/miniprogram", map);
	}
	
	/**
	 * 查询所有小程序
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/miniprogram")
	@ResponseBody
	public String miniprogram(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iMiniProgramService.miniprogram();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改小程序
	 * @param request
	 * @param response
	 * @param miniprogram
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MMiniprogram miniprogram) {
		String r = "";
		
		try {
			String sessionid = request.getSession().getId();
			MUser user = (MUser) RedisCache.retake(sessionid);
			
			Result result = iMiniProgramMaintService.changeMiniProgram(miniprogram, user);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
}
