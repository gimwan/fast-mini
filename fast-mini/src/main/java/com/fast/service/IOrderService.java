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
	 * @param cartid
	 * @return
	 */
	public Result orderConfirmData(String appid, String openid, String cartid);
	
	/**
	 * 结算计算
	 * @param vipid
	 * @param cartid
	 * @param couponid
	 * @param usepoint
	 * @param usedeposit
	 * @return
	 */
	public Result calculation(Integer vipid, String cartid, Integer couponid, Integer usepoint, Integer usedeposit);

}
