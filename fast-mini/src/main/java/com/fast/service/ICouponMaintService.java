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
	 * @param suitGoodsStr escape加密适用商品字符串
	 * @param suitDepartmentsStr escape加密适用门店字符串
	 * @return
	 */
	public Result changeCoupon(MCoupon coupon, MUser user, String suitGoodsStr, String suitDepartmentsStr);
	
	/**
	 * 删除优惠券
	 * @param id
	 * @return
	 */
	public Result deleteCoupon(Integer id);

}
