package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 省市区
 * @author J
 *
 */
public interface IRegionService {
	
	/**
	 * 归类省市区
	 * @return
	 */
	public Result region();
	
	/**
	 * 按分类查询地区
	 * @param page
	 * @param type
	 * @param parentID
	 * @return
	 */
	public Result list(PagingView page, Integer type, Integer parentID);

}
