package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 订单
 * @author J
 *
 */
public interface IOrderService {
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public Result order();
	
	/**
	 * 分页查询订单数据
	 * @param page
	 * @return
	 */
	public Result list(PagingView page);
	
	/**
	 * 结算页数据
	 * @param appid
	 * @param openid
	 * @param cartid  购物袋id，多个用英文,分隔
	 * @return
	 */
	public Result orderConfirmData(String appid, String openid, String cartid);
	
	/**
	 * 结算计算
	 * @param vipid  会员id
	 * @param cartid  购物袋id，多个用英文,分隔
	 * @param couponid  优惠券id
	 * @param usepoint  是否使用积分
	 * @param usedeposit  是否使用储值
	 * @return
	 */
	public Result calculation(Integer vipid, String cartid, Integer couponid, Integer usepoint, Integer usedeposit);
	
	/**
	 * 按状态查询订单
	 * @param appid
	 * @param openid
	 * @param status
	 * @return
	 */
	public Result queryOrderByStatus(PagingView page, String appid, String openid, Integer status);
	
	/**
	 * 订单明细
	 * @param id
	 * @return
	 */
	public Result queryOrderDetail(Integer id);
	
	/**
	 * 拼团订单结算计算
	 * @param groupbuyid
	 * @param skuid
	 * @param quantity
	 * @return
	 */
	public Result groupbuyCalculation(Integer groupbuyid, Integer skuid, Integer quantity);

}
