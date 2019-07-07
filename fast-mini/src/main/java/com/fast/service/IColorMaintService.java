package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MUser;

/**
 * 颜色
 * @author j
 *
 */
public interface IColorMaintService {
	
	/**
	 * 修改颜色
	 * @param color
	 * @param user
	 * @return
	 */
	public Result changeColor(MColor color, MUser user);
	
	/**
	 * 删除颜色
	 * @param id
	 * @return
	 */
	public Result deleteColor(Integer id);

}
