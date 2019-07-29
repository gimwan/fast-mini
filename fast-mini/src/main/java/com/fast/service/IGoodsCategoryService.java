package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 分类
 * @author J
 *
 */
public interface IGoodsCategoryService {
	
	/**
	 * 查询分类
	 * @return
	 */
	public Result goodsCategory();
	
	/**
	 * 按等级查询分类
	 * @param page
	 * @param grade  等级
	 * @param parentID  父类
	 * @return
	 */
	public Result list(PagingView page, Integer grade, Integer parentID);
	
	public Result category();

}
