package com.fast.service;

import com.fast.base.Result;

/**
 * 微页面
 * @author J
 *
 */
public interface IMicropageService {
	
	/**
	 * 获取微页面数据
	 * @param appid
	 * @param pageid
	 * @param openid
	 * @param ip
	 * @return
	 */
	public Result micropage(String appid, Integer pageid, String openid, String ip);
	
	/**
	 * 获取微页面数据
	 * @param pageID
	 * @return
	 */
	public Result queryPageData(Integer pageID);

}
