package com.fast.service;

import com.fast.base.Result;

/**
 * 拼团活动
 * @author J
 *
 */
public interface IGroupbuyService {
	
	/**
	 * 查询所有拼团活动
	 * @return
	 */
	public Result groupbuy();
	
	/**
	 * 拼团活动明细
	 * @param groupbuyid
	 * @return
	 */
	public Result groupbuyDetail(Integer groupbuyid);
	
	/**
	 * 查询拼团活动
	 * @param appid
	 * @return
	 */
	public Result queryGroupBuy(String appid);
	
	/**
	 * 查询拼团中的活动
	 * @param appid
	 * @return
	 */
	public Result queryActiveGroupBuy(String appid);
	
	/**
	 * 查询即将开始的拼团活动
	 * @param appid
	 * @return
	 */
	public Result querySoonGroupBuy(String appid);
	
	/**
	 * 拼团活动明细
	 * @param groupbuyid
	 * @param goodsid
	 * @return
	 */
	public Result queryGroupbuyDetail(Integer groupbuyid, Integer goodsid);
	
	/**
	 * 商品库存
	 * @param groupbuyid
	 * @param goodsid
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryGoodsStock(Integer groupbuyid, Integer goodsid, String appid, String openid);
}
