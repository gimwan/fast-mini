package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MUser;

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
	
	/**
	 * 支付后操作
	 * @param orderno 单号
	 * @param wechatno 微信支付单号
	 * @return
	 */
	public Result afterPay(String orderno, String wechatno);
	
	/**
	 * 订单发货
	 * @param user
	 * @param orderid
	 * @param logisticsid
	 * @param logisticsno
	 * @return
	 */
	public Result deliverOrder(MUser user, Integer orderid, Integer logisticsid, String logisticsno);
	
	/**
	 * 生成拼团订单
	 * @param appid
	 * @param vipid
	 * @param skuid
	 * @param quantity
	 * @param groupbuyid
	 * @param addressid
	 * @return
	 */
	public Result createGroupbuyOrder(String appid, Integer vipid, Integer skuid, Integer quantity, Integer groupbuyid, Integer addressid);
	
	/**
	 * 定时任务
	 * 自动取消订单
	 * @return
	 */
	public Result cancelOrderTask();
	
	/**
	 * 取消订单
	 * @param orderid
	 * @return
	 */
	public Result cancelOrder(Integer orderid);

}
