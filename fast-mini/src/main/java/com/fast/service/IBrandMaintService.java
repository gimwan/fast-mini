package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MUser;

/**
 * 品牌
 * @author J
 *
 */
public interface IBrandMaintService {
	
	/**
	 * 修改品牌
	 * @param employee
	 * @param user
	 * @return
	 */
	public Result changeBrand(MBrand brand, MUser user);
	
	/**
	 * 删除品牌
	 * @param id
	 * @return
	 */
	public Result deleteBrand(Integer id);

}
