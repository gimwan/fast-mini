package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IWechatPayService;

import net.sf.json.JSONObject;

/**
 * 微信支付
 * @author J
 *
 */
@Controller
public class WechatPay extends MiniMaster {
	
	@Autowired
	IWechatPayService iWechatPayService;
	
	/**
	 * 订单支付
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/wechatpay/order")
	@ResponseBody
	public String orderpay(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String domain = request.getParameter("domain");
			String id = request.getParameter("id");
			
			// 终端IP
			String spbill_create_ip = request.getRemoteAddr();
			
			r = iWechatPayService.orderpay(appid, openid, domain, Integer.valueOf(id), spbill_create_ip);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
