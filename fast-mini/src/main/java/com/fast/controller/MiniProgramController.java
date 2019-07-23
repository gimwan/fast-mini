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
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MUser;
import com.fast.service.IMiniProgramMaintService;
import com.fast.service.IMiniProgramService;
import com.fast.util.Common;

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
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iMiniProgramMaintService.changeMiniProgram(miniprogram, user);
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
	 * 删除小程序
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
			Result result = iMiniProgramMaintService.deleteMiniProgram(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
}
