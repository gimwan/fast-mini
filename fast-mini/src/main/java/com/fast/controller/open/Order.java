package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.base.page.PagingView;
import com.fast.service.IOrderMaintService;
import com.fast.service.IOrderService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 订单
 * @author J
 *
 */
@Controller
public class Order extends MiniMaster {
	
	@Autowired
	IOrderService iOrderService;
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
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
			
			r = iOrderService.orderConfirmData(appid, openid, cartid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 结算计算
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/order/calculation")
	@ResponseBody
	public String calculation(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String vipid = request.getParameter("vipid");
			String cartid = request.getParameter("cartid");
			String couponid = request.getParameter("couponid");
			String usedeposit = request.getParameter("usedeposit");
			String usepoint = request.getParameter("usepoint");
			if (Common.isEmpty(couponid)) {
				couponid = "0";
			}
			if (Common.isEmpty(usedeposit)) {
				usedeposit = "0";
			}
			if (Common.isEmpty(usepoint)) {
				usepoint = "0";
			}
			
			r = iOrderService.calculation(Integer.valueOf(vipid.trim()), cartid.trim(),
					Integer.valueOf(couponid.trim()), Integer.valueOf(usepoint.trim()),
					Integer.valueOf(usedeposit.trim()));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 生成订单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/order/create")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String vipid = request.getParameter("vipid");
			String cartid = request.getParameter("cartid");
			String addressid = request.getParameter("addressid");
			String couponid = request.getParameter("couponid");
			String usedeposit = request.getParameter("usedeposit");
			String usepoint = request.getParameter("usepoint");
			if (Common.isEmpty(couponid)) {
				couponid = "0";
			}
			if (Common.isEmpty(usedeposit)) {
				usedeposit = "0";
			}
			if (Common.isEmpty(usepoint)) {
				usepoint = "0";
			}
			
			r = iOrderMaintService.createOrder(appid, Integer.valueOf(vipid.trim()), cartid,
					Integer.valueOf(addressid.trim()), Integer.valueOf(couponid.trim()), Integer.valueOf(usepoint),
					Integer.valueOf(usedeposit));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 不同状态订单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/order/status")
	@ResponseBody
	public String status(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String status = request.getParameter("status");
			String pageNo = request.getParameter("pageno");
			String pageSize = request.getParameter("pagesize");
			if (Common.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (Common.isEmpty(pageSize)) {
				pageSize = "15";
			}
			if (Common.isEmpty(status)) {
				status = "0";
			}
			PagingView page = new PagingView(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize).intValue());
			
			r = iOrderService.queryOrderByStatus(page, appid, openid, Integer.valueOf(status));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 订单明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/order/detail")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String id = request.getParameter("id");
			
			r = iOrderService.queryOrderDetail(Integer.valueOf(id.trim()));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
