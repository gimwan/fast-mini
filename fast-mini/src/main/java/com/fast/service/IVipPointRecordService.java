package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 会员积分记录
 * @author J
 *
 */
public interface IVipPointRecordService {
	
	/**
	 * 积分记录
	 * @param appid
	 * @param openid
	 * @param page
	 * @return
	 */
	public Result queryVipPointRecordByOpenid(String appid, String openid, PagingView page);

}
