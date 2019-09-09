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
	
	/**
	 * 商品缩略图
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("/field/goodsthumbnail")
	@ResponseBody
	public String goodsthumbnail(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadGoodsThumbnail(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 商品主图/细节图
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("/field/goodsdtl")
	@ResponseBody
	public String goodsdtl(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadGoodsDetail(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 员工头像
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("/field/employee")
	@ResponseBody
	public String employee(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadEmployeePhoto(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 应用
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("/field/platform")
	@ResponseBody
	public String platform(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadpPlatformPhoto(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 参数
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("/field/config")
	@ResponseBody
	public String config(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadpConfigPhoto(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 参数
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping("/field/marketing")
	@ResponseBody
	public String marketing(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
		String r = "";
		
		try {
			Result result = iFieldUploadService.uploadpMarketingPhoto(request, file);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

}
