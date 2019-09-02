package com.fast.service.ext;

import com.fast.base.Result;

/**
 * 外部接口
 * @author J
 *
 */
public interface IExtMaintService {
	
	/**
	 * 同步数据
	 * @param type
	 * @return
	 */
	public Result synchronize(String type);
	
	/**
	 * 同步商品数据
	 * @param id
	 * @return
	 */
	public Result syncGoods(Integer id);
	
	/**
	 * 同步会员数据
	 * @param id
	 * @return
	 */
	public Result syncVip(Integer id);

}
