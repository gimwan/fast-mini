package com.fast.service;

import com.fast.base.Result;

/**
 * 优惠券
 * @author J
 *
 */
public interface IVipCouponMaintService {
	
	/**
	 * 领取优惠券
	 * @param couponID  优惠券id
	 * @param VipID  会员id
	 * @param quantity  数量
	 * @return
	 */
	public Result addVipCoupon(Integer couponID, Integer VipID, Integer quantity);

}
