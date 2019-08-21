package com.fast.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fast.base.Result;
import com.fast.base.data.entity.MUser;
import com.fast.service.IConfigService;
import com.fast.system.RedisCache;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 主页
 * @author J
 *
 */
@RequestMapping(value = "/home", produces = "application/json; charset=utf-8")
@Controller
public class HomeController {
	
	@Autowired
	IConfigService iConfigService;
	
	@RequestMapping("")
	public ModelAndView mainView(HttpServletRequest request, HttpServletResponse response) {
		String sessionid = request.getSession().getId();
		MUser user = (MUser) RedisCache.retake(sessionid);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		return new ModelAndView("home", map);
	}
	
	/**
	 * 菜单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/menu")
	@ResponseBody
	public String menu(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = new Result();
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> main = new HashMap<String, Object>();
			main.put("name", "系统管理");
			main.put("link", "");
			List<HashMap<String, Object>> sub = new ArrayList<HashMap<String,Object>>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", "参数");
			map.put("link", "config");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "角色");
			map.put("link", "role");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "用户");
			map.put("link", "user");
			sub.add(map);
			
			// 与线下ERP联用
			Integer ext = 0;
			Result re = iConfigService.queryConfigValueByCode("7001");
			if (Common.isActive(re)) {
				ext = (re.getData() == null || "".equals(re.getData().toString().trim())) ? 0 : Integer.valueOf(re.getData().toString().trim());
			}
			if (ext.intValue() == 1) {
				map = new HashMap<String, Object>();
				map.put("name", "接口");
				map.put("link", "extsystem");
				sub.add(map);
			}
			
			main.put("sub", sub);
			list.add(main);
			
			main = new HashMap<String, Object>();
			main.put("name", "资料管理");
			main.put("link", "");
			sub = new ArrayList<HashMap<String,Object>>();
			map = new HashMap<String, Object>();
			map.put("name", "门店");
			map.put("link", "department");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "员工");
			map.put("link", "employee");
			sub.add(map);
			main.put("sub", sub);
			list.add(main);
			
			main = new HashMap<String, Object>();
			main.put("name", "商品管理");
			main.put("link", "");
			sub = new ArrayList<HashMap<String,Object>>();
			map = new HashMap<String, Object>();
			map.put("name", "颜色");
			map.put("link", "color");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "版型");
			map.put("link", "pattern");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "尺码");
			map.put("link", "size");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "品牌");
			map.put("link", "brand");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "分类");
			map.put("link", "goodscategory");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "分组");
			map.put("link", "goodsgrouping");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "商品");
			map.put("link", "goods");
			sub.add(map);
			main.put("sub", sub);
			list.add(main);
			
			main = new HashMap<String, Object>();
			main.put("name", "会员管理");
			main.put("link", "");
			sub = new ArrayList<HashMap<String,Object>>();
			map = new HashMap<String, Object>();
			map.put("name", "等级");
			map.put("link", "viptype");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "会员");
			map.put("link", "vip");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "优惠券");
			map.put("link", "coupon");
			sub.add(map);
			main.put("sub", sub);
			list.add(main);
			
			main = new HashMap<String, Object>();
			main.put("name", "订单管理");
			main.put("link", "");
			sub = new ArrayList<HashMap<String,Object>>();
			map = new HashMap<String, Object>();
			map.put("name", "订单");
			map.put("link", "order");
			sub.add(map);
			main.put("sub", sub);
			list.add(main);
			
			main = new HashMap<String, Object>();
			main.put("name", "应用管理");
			main.put("link", "");
			sub = new ArrayList<HashMap<String,Object>>();
			map = new HashMap<String, Object>();
			map.put("name", "公众号");
			map.put("link", "publicplatform");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "小程序");
			map.put("link", "miniprogram");
			sub.add(map);
			map = new HashMap<String, Object>();
			map.put("name", "微页面");
			map.put("link", "micropage");
			sub.add(map);
			main.put("sub", sub);
			list.add(main);
			
			result.setData(list);
			result.setErrcode(Integer.valueOf(0));
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
