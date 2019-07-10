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
	
	/**
	 * 分页查询（补全外键表数据）
	 * @param page
	 * @param tableName  表名
	 * @return
	 */
	public Result list(PagingView page, String tableName);
	
	/**
	 * 查询单条记录
	 * @param tableName  表名
	 * @param id  主键id
	 * @return
	 */
	public Result one(String tableName, Integer id);

}
