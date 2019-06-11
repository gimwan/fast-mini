package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IVipService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 会员
 * @author J
 *
 */
@Controller
public class Vip extends MiniMaster {
	
	@Autowired
	IVipService iVipService;
	
	/**
	 * 默认登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/defaultlogin")
	@ResponseBody
	public String defaultlogin(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String openid = request.getParameter("openid");
			String appid = request.getParameter("appid");
			String unionid = request.getParameter("unionid");
			if (Common.isEmpty(unionid)) {
				unionid = "";
			}
			String guideid = request.getParameter("rcguideid");
			if (Common.isEmpty(guideid)) {
				guideid = "0";
			}
			String departmentid = request.getParameter("rcdepartmentid");
			if (Common.isEmpty(departmentid)) {
				departmentid = "0";
			}
			
			r = iVipService.defaultLogin(appid, unionid, openid, Integer.valueOf(guideid.trim()), Integer.valueOf(departmentid.trim()));			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
