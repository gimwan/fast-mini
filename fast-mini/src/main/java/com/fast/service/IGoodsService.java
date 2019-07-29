package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

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
	
	/**
	 * 查询商品库存
	 * @param goodsid
	 * @param appid
	 * @param openid
	 * @return
	 */
	public Result queryGoodsStock(Integer goodsid, String appid, String openid);
	
	/**
	 * 小程序商品分类
	 * @param appid
	 * @return
	 */
	public Result queryGoodsClassify(String appid);
	
	/**
	 * 搜索商品
	 * @param appid  小程序appid
	 * @param type 0全部 1分类 2分组
	 * @param id  type in (1,2)是分类/分组id
	 * @param keyword 搜索关键词
	 * @param sortType  排序方式，0综合 1新品 2价格从低到高 3价格从高到低
	 * @param page 分页
	 * @return
	 */
	public Result queryGoodsBySort(String appid, Integer type, Integer id, String keyword, Integer sortType, PagingView page);

}
