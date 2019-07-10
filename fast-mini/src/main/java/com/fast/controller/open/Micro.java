package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IMicropageService;
import com.fast.util.IpKit;
import com.jfinal.kit.StrKit;

import net.sf.json.JSONObject;

/**
 * 微页面
 * @author J
 *
 */
@Controller
public class Micro extends MiniMaster {
	
	@Autowired
	IMicropageService iMicropageService;
	
	/**
	 * 微页面数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/page")
	@ResponseBody
	public String page(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String id = request.getParameter("id");
			
			Integer pageid = null;
			if (id != null && !"".equals(id.trim())) {
				pageid = Integer.valueOf(id.trim());
			}
			
			if (openid == null || "".equals(openid.trim())) {
				openid = "";
			}
			
			String ip = IpKit.getRealIp(request);
            if (StrKit.isBlank(ip)) {
                ip = "127.0.0.1";
            }
			
			r = iMicropageService.micropage(appid, pageid, openid, ip);
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
