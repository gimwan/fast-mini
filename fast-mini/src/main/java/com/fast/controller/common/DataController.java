package com.fast.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 数据
 * @author J
 *
 */
@RequestMapping(value = "/data", produces = "application/json; charset=utf-8")
@Controller
public class DataController {
	
	@Autowired
	IDataService iDataService;
	
	/**
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/page")
	@ResponseBody
	public String page(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			String table = request.getParameter("table");
			String pageNo = request.getParameter("pageno");
			String pageSize = request.getParameter("pagesize");
			if (Common.isEmpty(pageNo)) {
				pageNo = "1";
			}
			if (Common.isEmpty(pageSize)) {
				pageSize = "30";
			}
			PagingView page = new PagingView(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize).intValue());
			
			String tableName = "m_" + table;
			Result result = iDataService.pageList(page, tableName);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
