package com.fast.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fast.base.Result;
import com.fast.service.IFieldUploadService;

import net.sf.json.JSONObject;

/**
 * 文件上传
 * @author J
 *
 */
@RequestMapping(value="/upload",produces = "application/json; charset=utf-8")
@Controller
public class FieldUploadController {
	
	@Autowired
	IFieldUploadService iFieldUploadService;
	
	/**
	 * 图片上传
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/image")
	@ResponseBody
	public String image(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadImage(request);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	@RequestMapping("/field/image")
	@ResponseBody
	public String image(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadFieldImage(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
