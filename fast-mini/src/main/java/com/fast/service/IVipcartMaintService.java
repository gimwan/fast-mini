package com.fast.service;

import com.fast.base.Result;

/**
 * 购物袋
 * @author J
 *
 */
public interface IVipcartMaintService {
	
	public Result addVipcart(String appid, String openid, Integer goodsid, Integer colorid, Integer patternid, Integer sizeid, Integer quantity);
	
	public Result changeVipcart(String appid, String openid, Integer id, Integer goodsid, Integer colorid, Integer patternid, Integer sizeid, Integer quantity);
	
	public Result deleteVipcart(String appid, String openid, Integer id);

}
