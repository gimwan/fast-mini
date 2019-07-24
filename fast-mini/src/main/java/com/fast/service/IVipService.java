package com.fast.service;

import com.fast.base.Result;

/**
 * 会员
 * @author j
 *
 */
public interface IVipService {
	
	/**
	 * 查询所有会员
	 * @return
	 */
	public Result vip();
	
	/**
	 * 根据openid查询会员
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryVipByOpenid(String appid, String openid);
	
	/**
	 * 根据unionid查询会员
	 * @param appid
	 * @param unionid
	 * @return
	 */
	public Result queryVipByUnionid(String appid, String unionid);
	
	/**
	 * 根据openid/unionid获取会员
	 * @param appid
	 * @param unionid
	 * @param openid
	 * @param guideid
	 * @param departmentid
	 * @return
	 */
	public Result defaultLogin(String appid, String unionid, String openid, Integer guideid, Integer departmentid);
	
	/**
	 * 查询会员账户信息
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryVipAccount(String appid, String openid);
	
	/**
	 * 查询会员积分
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryVipPoint(String appid, String openid);
	
	/**
	 * 查询会员储值
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryVipDeposit(String appid, String openid);
	
	/**
	 * 查询会员优惠券数量
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryVipCouponNumber(String appid, String openid);

	/**
	 * 查询会员储值、积分、优惠券数量
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryVipDPC(String appid, String openid);
}
