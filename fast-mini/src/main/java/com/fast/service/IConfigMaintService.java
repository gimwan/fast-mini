package com.fast.service;

import com.fast.base.Result;

/**
 * 系统参数
 * @author J
 *
 */
public interface IConfigMaintService {
	
	/**
	 * 修改参数
	 * @param id  参数id
	 * @param value  参数值
	 * @return
	 */
	public Result changeConfig(Integer id, String value);

}
