package com.fast.service;

import com.fast.base.Result;

/**
 * 会员
 * @author j
 *
 */
public interface IVipService {
	
	public Result queryVipByOpenid(String appid, String openid);
	
	public Result queryVipByUnionid(String appid, String unionid);
	
	public Result defaultLogin(String appid, String unionid, String openid, Integer guideid, Integer departmentid);

}
