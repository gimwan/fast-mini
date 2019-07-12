package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MUser;

/**
 * 优惠券
 * @author J
 *
 */
public interface ICouponMaintService {
	
	/**
	 * 修改优惠券
	 * @param employee
	 * @param user
	 * @return
	 */
	public Result changeCoupon(MCoupon coupon, MUser user);
	
	/**
	 * 删除优惠券
	 * @param id
	 * @return
	 */
	public Result deleteCoupon(Integer id);

}
