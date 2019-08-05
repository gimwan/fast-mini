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
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MUser;
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
@RequestMapping(value = "/order", produces = "application/json; charset=utf-8")
@Controller
public class OrderController {
	
	@Autowired
	IOrderService iOrderService;
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("order/order", map);
	}
	
	/**
	 * 分页查询订单数据
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
			String pageNo = request.getParameter("pageno");
			String pageSize = request.getParameter("pagesize");
			if (Common.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (Common.isEmpty(pageSize)) {
				pageSize = "15";
			}
			PagingView page = new PagingView(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize).intValue());
			
			Result result = iOrderService.list(page);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 修改订单状态
	 * @param request
	 * @param response
	 * @param micropage
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
				String id = request.getParameter("id");
				String logisticsid = request.getParameter("logisticsid");
				String logisticsno = request.getParameter("logisticsno");
				result = iOrderMaintService.changeOrderStatus(user, Integer.valueOf(id.trim()), Integer.valueOf(logisticsid.trim()), logisticsno.trim());
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
