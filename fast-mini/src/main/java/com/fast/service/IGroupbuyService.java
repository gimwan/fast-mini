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

}
