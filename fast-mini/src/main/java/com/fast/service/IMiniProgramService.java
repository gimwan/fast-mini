package com.fast.service;

import com.fast.base.Result;

/**
 * 小程序
 * @author J
 *
 */
public interface IMiniProgramService {
	
	/**
	 * 查询所有小程序
	 * @return
	 */
	public Result miniprogram();
	
	/**
	 * 根据appid查询
	 * @param appid
	 * @return
	 */
	public Result queryMiniprogramByAppid(String appid);
	
	/**
	 * 根据appid查询id
	 * @param appid
	 * @return
	 */
	public Result queryMiniprogramIDByAppid(String appid);

}
