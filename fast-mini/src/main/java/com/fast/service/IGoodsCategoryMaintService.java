package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MUser;

/**
 * 分类
 * @author J
 *
 */
public interface IGoodsCategoryMaintService {
	
	/**
	 * 修改分类
	 * @param goodsgrouping
	 * @param user
	 * @return
	 */
	public Result changeGoodsCategory(MGoodscategory goodscategory, MUser user);
	
	/**
	 * 删除分类
	 * @param id
	 * @return
	 */
	public Result deleteGoodsCategory(Integer id);

}
