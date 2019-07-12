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
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MUser;
import com.fast.service.ICouponMaintService;
import com.fast.service.ICouponService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 优惠券
 * @author J
 *
 */
@RequestMapping(value = "/coupon", produces = "application/json; charset=utf-8")
@Controller
public class CouponController {
	
	@Autowired
	ICouponService iCouponService;
	
	@Autowired
	ICouponMaintService iCouponMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("vip/coupon", map);
	}
	
	/**
	 * 查询所有优惠券
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/coupon")
	@ResponseBody
	public String coupon(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iCouponService.coupon();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改优惠券
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/change")
	@ResponseBody
	public String change(HttpServletRequest request, HttpServletResponse response, MCoupon coupon) {
		String r = "";
		
		try {
			Result result = new Result();
			
			MUser user = Common.currentUser(request);
			if (user != null) {
				result = iCouponMaintService.changeCoupon(coupon, user);
			} else {
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
	 * 删除优惠券
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
			Result result = iCouponMaintService.deleteCoupon(Integer.valueOf(id));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
