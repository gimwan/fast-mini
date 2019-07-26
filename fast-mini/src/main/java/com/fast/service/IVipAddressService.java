package com.fast.service;

import com.fast.base.Result;

/**
 * 会员收货地址
 * @author J
 *
 */
public interface IVipAddressService {
	
	/**
	 * 会员所有收货地址
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result vipAddress(String appid, String openid);

}
