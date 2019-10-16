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
	
	/**
	 * 会员领取优惠券
	 * @param appid  小程序appid
	 * @param openid  会员openid
	 * @param id  优惠券id
	 * @return
	 */
	public Result gainVipCoupon(String appid, String openid, Integer id);
	
	/**
	 * 适用优惠券
	 * @param code
	 * @param vipcode
	 * @return
	 */
	public Result useVipCoupon(String code, String vipcode);

}
