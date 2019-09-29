package com.fast.controller.ext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.service.ext.IExtMaintService;
import com.fast.service.ext.IExtService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 外部接口
 * @author J
 *
 */
@RequestMapping(value = "/ext", produces = "application/json; charset=utf-8")
@Controller
public class ExtController {
	
	@Autowired
	IExtMaintService iExtMaintService;
	
	@Autowired
	IExtService iExtService;
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	/**
	 * 同步
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/synchronize")
	@ResponseBody
	public String synchronize(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String type = request.getParameter("type");
			Result result = iExtMaintService.synchronize(type);
			if (Common.isEmpty(result.getMessage())) {
				result.setMessage("同步失败");
			}
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 获取商品信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sync/goods")
	@ResponseBody
	public String syncgoods(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {			
			String code = request.getParameter("code");
			Result result = iExtService.queryGoodsByCode(code);
			if (!Common.isActive(result) && Common.isEmpty(result.getMessage())) {
				result.setMessage("无此商品");
			}
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 同步商品SKU
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sync/sku")
	@ResponseBody
	public String syncsku(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String id = request.getParameter("id");
			Result result = iExtMaintService.syncGoodsSKu(Integer.valueOf(id.trim()));
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
