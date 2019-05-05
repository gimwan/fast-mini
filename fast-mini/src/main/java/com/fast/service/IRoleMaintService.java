package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MUser;

/**
 * 角色管理
 * @author J
 *
 */
public interface IRoleMaintService {
	
	/**
	 * 修改角色
	 * @param role
	 * @param user
	 * @return
	 */
	public Result changeRole(MRole role, MUser user);

}
