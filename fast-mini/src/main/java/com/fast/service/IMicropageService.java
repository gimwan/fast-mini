package com.fast.service;

import com.fast.base.Result;

/**
 * 微页面
 * @author J
 *
 */
public interface IMicropageService {
	
	public Result micropage(String appid, Integer pageid, String openid, String ip);

}
