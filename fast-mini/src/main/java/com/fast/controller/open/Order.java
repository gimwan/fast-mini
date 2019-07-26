package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;

import net.sf.json.JSONObject;

/**
 * 订单
 * @author J
 *
 */
@Controller
public class Order extends MiniMaster {
	
	/**
	 * 订单结算页计算
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/order")
	@ResponseBody
	public String order(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String cartid = request.getParameter("cartid");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
