package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MUser;

/**
 * 微页面
 * @author J
 *
 */
public interface IMicropageMaintService {
	
	/**
	 * 修改微页面
	 * @param micropage
	 * @param user
	 * @return
	 */
	public Result changeMicropage(MMicropage micropage, MUser user);
	
	/**
	 * 删除微页面
	 * @param id
	 * @return
	 */
	public Result deleteMicropage(Integer id);

}
