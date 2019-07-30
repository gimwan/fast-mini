package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 会员全部优惠券
 * @author J
 *
 */
public interface IVipCouponService {
	
	/**
	 * 会员优惠券
	 * @param appid 小程序appid
	 * @param openid 会员openid
	 * @param type 0未使用 1已使用 2已过期
	 * @param page
	 * @return
	 */
	public Result queryVipCoupon(String appid, String openid, Integer type, PagingView page);

}
