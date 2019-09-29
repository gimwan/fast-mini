package com.fast.controller;

import java.math.BigDecimal;
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
import com.fast.base.data.entity.MUser;
import com.fast.base.page.PagingView;
import com.fast.service.IMicropageMaintService;
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
	
	@Autowired
	IMicropageMaintService iMicropageMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("application/micro", map);
	}
	
	@RequestMapping("/micropage")
	public ModelAndView micropage(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String publicplatformid = request.getParameter("publicplatformid");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pageid", id);
		map.put("publicplatformid", publicplatformid);
		return new ModelAndView("application/micropage", map);
	}
	
	/**
	 * 微页面列表
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
			String publicplatformid = request.getParameter("publicplatformid");
			String pageNo = request.getParameter("pageno");
			String pageSize = request.getParameter("pagesize");
			if (Common.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (Common.isEmpty(pageSize)) {
				pageSize = "15";
			}
			if (Common.isEmpty(publicplatformid)) {
				publicplatformid = "0";
			}
			PagingView page = new PagingView(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize).intValue());
			
			Result result = iMicropageService.list(page, Integer.valueOf(publicplatformid));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改微页面
	 * @param request
	 * @param response
	 * @param role
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MMicropage micropage) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iMicropageMaintService.changeMicropage(micropage, user);
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
	 * 删除微页面
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
			Result result = iMicropageMaintService.deleteMicropage(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
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
			Result result = iMicropageService.queryPageData(Integer.valueOf(pageid.trim()), BigDecimal.ONE, true);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 保存微页面配置数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String dasaveta(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = new Result();
			MUser user = Common.currentUser(request);
			if (user != null) {
				String pageid = request.getParameter("pageid");
				if (Common.isEmpty(pageid)) {
					result.setMessage("pageid无效");
					JSONObject jsonObject = JSONObject.fromObject(result);
					return jsonObject.toString();
				}
				String setdata = request.getParameter("setdata");
				result = iMicropageMaintService.saveMicropageSetData(Integer.valueOf(pageid), setdata, user);
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
	 * 发布
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/release")
	@ResponseBody
	public String release(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = new Result();
			MUser user = Common.currentUser(request);
			if (user != null) {
				String pageid = request.getParameter("pageid");
				if (Common.isEmpty(pageid)) {
					result.setMessage("pageid无效");
					JSONObject jsonObject = JSONObject.fromObject(result);
					return jsonObject.toString();
				}
				result = iMicropageMaintService.releaseMicroPage(Integer.valueOf(pageid), user);
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

}
