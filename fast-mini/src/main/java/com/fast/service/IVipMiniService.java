package com.fast.service;

import com.fast.base.Result;

public interface IVipMiniService {
	
	/**
	 * 根据openid查询对应小程序记录
	 * @param openid
	 * @return
	 */
	public Result queryVipMiniByOpenid(Integer miniprogramid, String openid);

}
