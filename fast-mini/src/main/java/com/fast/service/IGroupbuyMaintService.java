package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MGroupbuy;
import com.fast.base.data.entity.MUser;

/**
 * 拼团活动
 * @author J
 *
 */
public interface IGroupbuyMaintService {
	
	/**
	 * 修改拼团活动
	 * @param employee
	 * @param user
	 * @return
	 */
	public Result changeGroupbuy(MGroupbuy groupbuy, MUser user);
	
	/**
	 * 删除拼团活动
	 * @param id
	 * @return
	 */
	public Result deleteGroupbuy(Integer id);
	
	/**
	 * 保存拼团活动明细
	 * @param user
	 * @param groupbuyid
	 * @param goodsdatas
	 * @return
	 */
	public Result saveGroupbuydtl(MUser user, Integer groupbuyid, String goodsdatas);

}
