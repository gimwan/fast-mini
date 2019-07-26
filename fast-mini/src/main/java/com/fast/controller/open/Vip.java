package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.base.data.entity.MVip;
import com.fast.service.IVipMaintService;
import com.fast.service.IVipService;
import com.fast.service.IVipcartMaintService;
import com.fast.service.IVipcartService;
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
	
	@Autowired
	IVipMaintService iVipMaintService;
	
	@Autowired
	IVipcartService iVipcartService;
	
	@Autowired
	IVipcartMaintService iVipcartMaintService;
	
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
	
	/**
	 * 绑定会员手机号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/bind")
	@ResponseBody
	public String bind(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String unionid = request.getParameter("unionid");
			String openid = request.getParameter("openid");
			String mobile = request.getParameter("mobile");
			String nickname = request.getParameter("nickname");
			String headimgurl = request.getParameter("headimgurl");
			String sex = request.getParameter("sex");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String rcvipid = request.getParameter("rcvipid");
			String rcdepartmentid = request.getParameter("rcdepartmentid");
			MVip vip = new MVip();
			
			if (Common.isEmpty(mobile)) {
				r.setMessage("手机号无效");
				JSONObject jsonObject = JSONObject.fromObject(r);
				result = jsonObject.toString();
				return result;
			}
			vip.setMobilephone(mobile);
			if (Common.isEmpty(unionid) && Common.isEmpty(openid)) {
				r.setMessage("unionid和openid均无效");
				JSONObject jsonObject = JSONObject.fromObject(r);
				result = jsonObject.toString();
				return result;
			}
			if (Common.isEmpty(unionid)) {
				unionid = null;
			}	
			vip.setUnionid(unionid);
			if (Common.isEmpty(openid)) {
				openid = null;
			}
			if (Common.isEmpty(nickname)) {
				nickname = mobile;
			}
			vip.setNickname(nickname);
			vip.setName(vip.getNickname());
			if (Common.isEmpty(headimgurl)) {
				headimgurl = null;
			}
			vip.setPhotourl(headimgurl);
			if (Common.isEmpty(sex)) {
				sex = "1";
			}
			vip.setSex(Byte.valueOf(sex));
			if (Common.isEmpty(province)) {
				province = null;
			}
			vip.setProvince(province);
			if (Common.isEmpty(city)) {
				city = null;
			}
			vip.setCity(city);
			if (Common.isEmpty(rcvipid)) {
				rcvipid = null;
			} else {
				vip.setRecommenderid(Integer.valueOf(rcvipid));
			}			
			if (Common.isEmpty(rcdepartmentid)) {
				rcdepartmentid = null;
			} else {
				vip.setDepartmentid(Integer.valueOf(rcdepartmentid));
			}
			
			r = iVipMaintService.bind(appid, openid, vip);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 会员信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/data")
	@ResponseBody
	public String data(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			r = iVipService.queryVipByOpenid(appid, openid);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 更新会员信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/update")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String name = request.getParameter("name");
			String nickname = request.getParameter("nickname");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String county = request.getParameter("county");
			String avatarurl = request.getParameter("avatarurl");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			
			r = iVipMaintService.updateVipInfo(appid, openid, name, nickname, birthday, province, city, county, avatarurl, gender);
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 获取会员积分
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/point")
	@ResponseBody
	public String point(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			
			r = iVipService.queryVipPoint(appid, openid);
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 获取会员储值
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/deposit")
	@ResponseBody
	public String deposit(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			
			r = iVipService.queryVipDeposit(appid, openid);
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 查询会员有效优惠券数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/couponqty")
	@ResponseBody
	public String couponqty(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			
			r = iVipService.queryVipDeposit(appid, openid);
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 查询会员储值、积分、优惠券数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/dpc")
	@ResponseBody
	public String dpc(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			
			r = iVipService.queryVipDPC(appid, openid);
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 购物袋
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/cart")
	@ResponseBody
	public String cart(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String kind = request.getParameter("kind");
			if (Common.isEmpty(kind)) {
				kind = "1";
			}
			
			r = iVipcartService.queryVipcart(openid, appid, Integer.valueOf(kind.trim()));
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 加入购物袋
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/cart/add")
	@ResponseBody
	public String cartadd(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String goodsid = request.getParameter("goodsid");
			String colorid = request.getParameter("colorid");
			String patternid = request.getParameter("patternid");
			String sizeid = request.getParameter("sizeid");
			String quantity = request.getParameter("quantity");
			
			r = iVipcartMaintService.addVipcart(appid, openid, Integer.valueOf(goodsid), Integer.valueOf(colorid),
					Integer.valueOf(patternid), Integer.valueOf(sizeid), Integer.valueOf(quantity));
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 修改购物袋
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/cart/change")
	@ResponseBody
	public String cartchange(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String id = request.getParameter("id");
			String goodsid = request.getParameter("goodsid");
			String colorid = request.getParameter("colorid");
			String patternid = request.getParameter("patternid");
			String sizeid = request.getParameter("sizeid");
			String quantity = request.getParameter("quantity");
			
			r = iVipcartMaintService.changeVipcart(appid, openid, Integer.valueOf(id), Integer.valueOf(goodsid),
					Integer.valueOf(colorid), Integer.valueOf(patternid), Integer.valueOf(sizeid),
					Integer.valueOf(quantity));
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}
	
	/**
	 * 移除购物袋
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip/cart/delete")
	@ResponseBody
	public String cartdelete(HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		Result r = new Result();
		
		try {
			String appid = request.getParameter("appid");
			String openid = request.getParameter("openid");
			String id = request.getParameter("id");
			
			r = iVipcartMaintService.deleteVipcart(appid, openid, Integer.valueOf(id));
			
		} catch (Exception e) {
			e.printStackTrace();
			r.setMessage(e.getMessage());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(r);
		result = jsonObject.toString();
		
		return result;
	}

}
