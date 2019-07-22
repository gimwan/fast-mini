package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MUser;

/**
 * 分组
 * @author J
 *
 */
public interface IGoodsGroupingMaintService {
	
	/**
	 * 修改优惠券
	 * @param employee
	 * @param user
	 * @return
	 */
	public Result changeGoodsGrouping(MGoodsgrouping goodsgrouping, MUser user);
	
	/**
	 * 删除优惠券
	 * @param id
	 * @return
	 */
	public Result deleteGoodsGrouping(Integer id);

}
