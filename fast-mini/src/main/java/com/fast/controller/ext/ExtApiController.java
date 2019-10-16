package com.fast.controller.ext;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IVipCouponMaintService;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * 外部调用接口
 * @author J
 *
 */
@RequestMapping(value = "/extapi", produces = "application/json; charset=utf-8")
@Controller
public class ExtApiController {
	
	@Autowired
	IVipCouponMaintService iVipCouponMaintService;
	
	/**
	 * 核销优惠券
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/coupon/use")
	@ResponseBody
	public Result couponuse(HttpServletRequest request, HttpServletResponse response) {
		Result result = new Result();
		
		try {
			Map<String, Object> map = CommonUtil.getParameterStringMap(request);
			
			String no = request.getParameter("no");
			if (Common.isEmpty(no)) {
				no = map.get("no") == null ? "" : map.get("no").toString();
			}
			if (Common.isEmpty(no)) {
				result.setMessage("缺少券号");
				return result;
			}
			String vipcode = request.getParameter("vipcode");
			if (Common.isEmpty(vipcode)) {
				vipcode = map.get("vipcode") == null ? "" : map.get("vipcode").toString();
			}
			if (Common.isEmpty(vipcode)) {
				result.setMessage("缺少会员号");
				return result;
			}
			result = iVipCouponMaintService.useVipCoupon(no.trim(), vipcode.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
