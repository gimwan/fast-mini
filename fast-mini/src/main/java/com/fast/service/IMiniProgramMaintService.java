package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MUser;

/**
 * 小程序
 * @author J
 *
 */
public interface IMiniProgramMaintService {
	
	/**
	 * 修改小程序
	 * @param miniprogram
	 * @param user
	 * @return
	 */
	public Result changeMiniProgram(MMiniprogram miniprogram, MUser user);
	
	/**
	 * 删除小程序
	 * @param id
	 * @return
	 */
	public Result deleteMiniProgram(Integer id);

}
