package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IGroupbuyService;
import com.fast.service.IOrderMaintService;
import com.fast.service.IOrderService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 拼团
 * @author J
 *
 */
@Controller
public class Groupbuy extends MiniMaster {
	
	@Autowired
	IGroupbuyService iGroupbuyService;
	
	@Autowired
	IOrderService iOrderService;
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
	/**
	 * 查询拼团活动
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy")
	@ResponseBody
	public String groupbuy(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			r = iGroupbuyService.queryGroupBuy(appid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 拼团中
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/active")
	@ResponseBody
	public String active(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			r = iGroupbuyService.queryActiveGroupBuy(appid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 即将开始
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/soon")
	@ResponseBody
	public String soon(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			r = iGroupbuyService.querySoonGroupBuy(appid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 拼团商品明细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/detail")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String groupbuyid = request.getParameter("groupbuyid");
			String goodsid = request.getParameter("goodsid");
			r = iGroupbuyService.queryGroupbuyDetail(Integer.valueOf(groupbuyid), Integer.valueOf(goodsid));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 拼团商品库存
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/stock")
	@ResponseBody
	public String sku(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String openid = request.getParameter("openid");
			String appid = request.getParameter("appid");
			String goodsid = request.getParameter("goodsid");
			String groupbuyid = request.getParameter("groupbuyid");
			if (Common.isEmpty(openid)) {
				openid = "";
			}
			if (Common.isEmpty(appid)) {
				appid = "";
			}
			
			r = iGroupbuyService.queryGoodsStock(Integer.valueOf(groupbuyid), Integer.valueOf(goodsid), appid, openid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 结算页默认值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/order")
	@ResponseBody
	public String order(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String skuid = request.getParameter("skuid");
			String quantity = request.getParameter("quantity");
			String groupbuyid = request.getParameter("groupbuyid");
			
			r = iOrderService.groupbuyOrderConfirmData(appid, openid, Integer.valueOf(groupbuyid), Integer.valueOf(skuid), Integer.valueOf(quantity));
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
	@RequestMapping("/groupbuy/calculation")
	@ResponseBody
	public String calculation(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String skuid = request.getParameter("skuid");
			String quantity = request.getParameter("quantity");
			String groupbuyid = request.getParameter("groupbuyid");
			
			r = iOrderService.groupbuyCalculation(Integer.valueOf(groupbuyid), Integer.valueOf(skuid), Integer.valueOf(quantity));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 生成拼团订单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/groupbuy/create")
	@ResponseBody
	public String create(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String vipid = request.getParameter("vipid");
			String skuid = request.getParameter("skuid");
			String quantity = request.getParameter("quantity");
			String groupbuyid = request.getParameter("groupbuyid");
			String addressid = request.getParameter("addressid");
			
			r = iOrderMaintService.createGroupbuyOrder(appid, Integer.valueOf(vipid.trim()),
					Integer.valueOf(skuid.trim()), Integer.valueOf(quantity.trim()), Integer.valueOf(groupbuyid.trim()),
					Integer.valueOf(addressid.trim()));
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
