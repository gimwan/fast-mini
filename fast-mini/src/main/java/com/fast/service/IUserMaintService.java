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
	 * @param currentUser  当前登入者
	 * @return
	 */
	public Result changeUser(MUser user, MUser currentUser);
	
	/**
	 * 删除用户
	 * @param id
	 * @param user
	 * @return
	 */
	public Result deleteUser(Integer id, MUser user);

}
