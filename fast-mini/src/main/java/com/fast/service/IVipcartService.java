package com.fast.service;

import com.fast.base.Result;

/**
 * 购物袋
 * @author J
 *
 */
public interface IVipcartService {
	
	/**
	 * 查询购物袋商品
	 * @param openid
	 * @param appid
	 * @param kind
	 * @return
	 */
	public Result queryVipcart(String openid, String appid, Integer kind);

}
