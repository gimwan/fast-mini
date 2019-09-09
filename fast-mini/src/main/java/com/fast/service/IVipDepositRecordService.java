package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 会员储值记录
 * @author J
 *
 */
public interface IVipDepositRecordService {
	
	/**
	 * 储值记录
	 * @param appid
	 * @param openid
	 * @param page
	 * @return
	 */
	public Result queryVipDepositRecordByOpenid(String appid, String openid, PagingView page);

}
