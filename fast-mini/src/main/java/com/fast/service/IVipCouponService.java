package com.fast.service;

import com.fast.base.Result;

/**
 * 会员全部优惠券
 * @author J
 *
 */
public interface IVipCouponService {
	
	/**
	 * 会员优惠券
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryUseableCoupon(String appid, String openid);

}
