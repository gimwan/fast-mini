package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MUser;

/**
 * 应用接口
 * @author J
 *
 */
public interface IExtSystemMaintService {
	
	/**
	 * 修改应用接口
	 * @param employee
	 * @param user
	 * @return
	 */
	public Result changeExtSystem(MExtsystem extsystem, MUser user);
	
	/**
	 * 删除应用接口
	 * @param id
	 * @return
	 */
	public Result deleteExtSystem(Integer id);

}
