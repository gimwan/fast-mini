package com.fast.controller.open;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IConfigService;
import com.fast.service.IRegionService;

import net.sf.json.JSONObject;

/**
 * 基础数据
 * @author J
 *
 */
@Controller
public class Base extends MiniMaster {
	
	@Autowired
	IConfigService iConfigService;
	
	@Autowired
	IRegionService iRegionService;
	
	/**
	 * 基础参数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/config")
	@ResponseBody
	public String config(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			List<String> codeList = new ArrayList<>();
			codeList.add("1001");
			codeList.add("1002");
			codeList.add("1003");
			codeList.add("1004");
			codeList.add("1005");
			codeList.add("1006");
			codeList.add("2001");
			codeList.add("2002");
			codeList.add("3001");
			codeList.add("3002");
			codeList.add("4001");
			
			Result result = iConfigService.queryConfigByCodeList(codeList);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	@RequestMapping("/region")
	@ResponseBody
	public String region(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			
			Result result = iRegionService.region();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
