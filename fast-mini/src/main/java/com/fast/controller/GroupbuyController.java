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
import com.fast.base.data.entity.MGroupbuy;
import com.fast.base.data.entity.MUser;
import com.fast.service.IGroupbuyMaintService;
import com.fast.service.IGroupbuyService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 拼团活动
 * @author J
 *
 */
@RequestMapping(value = "/groupbuy", produces = "application/json; charset=utf-8")
@Controller
public class GroupbuyController {
	
	@Autowired
	IGroupbuyService iGroupbuyService;
	
	@Autowired
	IGroupbuyMaintService iGroupbuyMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("marketing/groupbuy", map);
	}
	
	/**
	 * 查询所有拼团活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy")
	@ResponseBody
	public String groupbuy(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iGroupbuyService.groupbuy();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改拼团活动
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MGroupbuy groupbuy) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iGroupbuyMaintService.changeGroupbuy(groupbuy, user);
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
	 * 删除拼团活动
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
			Result result = iGroupbuyMaintService.deleteGroupbuy(Integer.valueOf(id.trim()));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 拼团活动明细
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String groupbuyid = request.getParameter("groupbuyid");
			Result result = iGroupbuyService.groupbuyDetail(Integer.valueOf(groupbuyid.trim()));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	/**
	 * 保存拼团活动明细信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/detail/save")
	@ResponseBody
	public String savegroupbuydtl(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = new Result();
			MUser user = Common.currentUser(request);
			if (user != null) {
				String groupbuyid = request.getParameter("groupbuyid");
				String goodsdatas = request.getParameter("goodsdatas");
				result = iGroupbuyMaintService.saveGroupbuydtl(user, Integer.valueOf(groupbuyid.trim()), goodsdatas);
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
