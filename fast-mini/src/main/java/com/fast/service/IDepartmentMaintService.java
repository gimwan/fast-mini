package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MUser;

/**
 * 门店
 * @author J
 *
 */
public interface IDepartmentMaintService {
	
	/**
	 * 修改门店
	 * @param department
	 * @param user
	 * @return
	 */
	public Result changeDepartment(MDepartment department, MUser user);
	
	/**
	 * 删除门店
	 * @param id
	 * @return
	 */
	public Result deleteDepartment(Integer id);

}
