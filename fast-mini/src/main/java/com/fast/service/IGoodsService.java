package com.fast.service;

import com.fast.base.Result;

/**
 * 商品
 * @author J
 *
 */
public interface IGoodsService {
	
	/**
	 * 查询所有商品
	 * @return
	 */
	public Result goods();
	
	/**
	 * 商品详情
	 * @param id  商品id
	 * @param openid  会员openid
	 * @param appid  小程序appid
	 * @return
	 */
	public Result goodsDetail(Integer id, String openid, String appid);
	
	/**
	 * 商品主图/明细图
	 * @param goodsid 商品id
	 * @return
	 */
	public Result goodsInfo(Integer goodsid);

}
