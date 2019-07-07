package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MViptype;

/**
 * 会员等级
 * @author J
 *
 */
public interface IViptypeMaintService {
	
	/**
	 * 修改会员等级
	 * @param viptype
	 * @param user
	 * @return
	 */
	public Result changeVipType(MViptype viptype, MUser user);
	
	/**
	 * 删除会员等级
	 * @param id
	 * @return
	 */
	public Result deleteVipType(Integer id);

}
