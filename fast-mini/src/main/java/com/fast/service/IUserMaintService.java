package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MUser;

/**
 * 用户管理
 * @author J
 *
 */
public interface IUserMaintService {
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public Result changeUser(MUser user);

}
