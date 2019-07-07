package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MUser;

/**
 * 尺码
 * @author J
 *
 */
public interface ISizeMaintService {
	
	/**
	 * 修改尺码
	 * @param size
	 * @param user
	 * @return
	 */
	public Result changeSize(MSize size, MUser user);
	
	/**
	 * 删除尺码
	 * @param id
	 * @return
	 */
	public Result deleteSize(Integer id);

}
