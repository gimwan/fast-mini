package com.fast.service;

import com.fast.base.Result;

/**
 * 优惠券
 * @author j
 *
 */
public interface ICouponService {
	
	/**
	 * 查询所有优惠券
	 * @return
	 */
	public Result coupon();
	
	/**
	 * 查询优惠券适用商品
	 * @param couponid
	 * @return
	 */
	public Result queryCouponSuitGoods(Integer couponid);
	
	/**
	 * 查询优惠券适用门店
	 * @param couponid
	 * @return
	 */
	public Result queryCouponSuitDepartment(Integer couponid);

}
