package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MUser;

/**
 * 版型
 * @author J
 *
 */
public interface IPatternMaintService {
	
	/**
	 * 修改版型
	 * @param pattern
	 * @param user
	 * @return
	 */
	public Result changePattern(MPattern pattern, MUser user);
	
	/**
	 * 删除版型
	 * @param id
	 * @return
	 */
	public Result deletePattern(Integer id);

}
