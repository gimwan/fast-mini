package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MDepartment;

/**
 * 门店管理
 * @author J
 *
 */
public interface IDepartmentService {
	
	/**
	 * 查询所有门店
	 * @return
	 */
	public Result department();
	
	/**
	 * 重置门店省市区
	 * @param department
	 * @return
	 */
	public MDepartment resetDepartmentRegion(MDepartment department);

}
