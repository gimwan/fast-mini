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

}
