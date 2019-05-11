package com.fast.service;

import javax.servlet.http.HttpServletRequest;

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

}
