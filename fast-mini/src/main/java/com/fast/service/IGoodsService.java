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
	 * 根据条件查询前4款商品
	 * @param id  对应类型的id
	 * @param type  1分类 2分组
	 * @param orderBy 排序条件
	 * @return
	 */
	public Result topFour(Integer id, Integer type, Integer orderBy);
	
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
	
	/**
	 * 获取商品SKU
	 * @param goodsid
	 * @return
	 */
	public Result goodsSKU(Integer goodsid);

}
