package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MEmployee;
import com.fast.base.data.entity.MUser;

/**
 * 员工管理
 * @author J
 *
 */
public interface IEmployeeMaintService {
	
	/**
	 * 修改员工
	 * @param employee
	 * @param user
	 * @return
	 */
	public Result changeEmployee(MEmployee employee, MUser user);
	
	/**
	 * 删除员工
	 * @param id
	 * @return
	 */
	public Result deleteEmployee(Integer id);

}
