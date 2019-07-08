package com.fast.service;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

public interface IDataService {
	
	/**
	 * 分页查询
	 * @param page
	 * @param tableName  表名
	 * @return
	 */
	public Result pageList(PagingView page, String tableName);

}
