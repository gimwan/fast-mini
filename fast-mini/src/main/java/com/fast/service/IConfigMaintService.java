package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MUser;

/**
 * 系统参数
 * @author J
 *
 */
public interface IConfigMaintService {
	
	/**
	 * 修改参数
	 * @param config
	 * @param user
	 * @return
	 */
	public Result changeConfig(MConfig config, MUser user);

}
