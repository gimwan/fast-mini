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
	
	public Result changeEmployee(MEmployee employee, MUser user);

}
