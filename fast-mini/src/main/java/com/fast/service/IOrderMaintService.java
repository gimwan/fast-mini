package com.fast.service;

import com.fast.base.Result;

/**
 * 订单
 * @author J
 *
 */
public interface IOrderMaintService {
	
	/**
	 * 生成订单
	 * @param appid 小程序appid
	 * @param vipid  会员id
	 * @param cartid  购物袋id，多个用英文,分隔
	 * @param addressid  地址id
	 * @param couponid  优惠券id
	 * @param usepoint 是否使用积分
	 * @param usedeposit  是否使用储值
	 * @return
	 */
	public Result createOrder(String appid, Integer vipid, String cartid, Integer addressid, Integer couponid, Integer usepoint, Integer usedeposit);

}
