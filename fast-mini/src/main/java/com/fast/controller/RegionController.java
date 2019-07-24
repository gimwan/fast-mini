package com.fast.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.page.PagingView;
import com.fast.service.IRegionService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 地区
 * @author J
 *
 */
@RequestMapping(value = "/region", produces = "application/json; charset=utf-8")
@Controller
public class RegionController {
	
	@Autowired
	IRegionService iRegionService;
	
	/**
	 * 按类型查询地区
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
			String type = request.getParameter("grade");
			String parentid = request.getParameter("parentid");
			String pageNo = request.getParameter("pageno");
			String pageSize = request.getParameter("pagesize");
			if (Common.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (Common.isEmpty(pageSize)) {
				pageSize = "15";
			}
			if (Common.isEmpty(type)) {
				type = "1";
			}
			if (Common.isEmpty(parentid)) {
				parentid = "0";
			}
			PagingView page = new PagingView(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize).intValue());
			
			Result result = iRegionService.list(page, Integer.valueOf(type.trim()) + 1, Integer.valueOf(parentid.trim()));
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
