package com.fast.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.fast.base.Result;

/**
 * 文件上传
 * @author J
 *
 */
public interface IFieldUploadService {
	
	/**
	 * 上传图片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Result uploadImage(HttpServletRequest request) throws Exception;
	
	/**
	 * 上传图片
	 * @param request
	 * @param file
	 * @return
	 */
	public Result uploadFieldImage(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 上传商品缩略图
	 * @param request
	 * @param file
	 * @return
	 */
	public Result uploadGoodsThumbnail(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 上传商品主图/细节图
	 * @param request
	 * @param file
	 * @return
	 */
	public Result uploadGoodsDetail(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 上传员工头像
	 * @param request
	 * @param file
	 * @return
	 */
	public Result uploadEmployeePhoto(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 上传应用图片
	 * @param request
	 * @param file
	 * @return
	 */
	public Result uploadpPlatformPhoto(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 系统参数
	 * @param request
	 * @param file
	 * @return
	 */
	public Result uploadpConfigPhoto(HttpServletRequest request, MultipartFile file);

}
