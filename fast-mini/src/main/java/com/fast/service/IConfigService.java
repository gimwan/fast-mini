package com.fast.service;

import java.util.List;

import com.fast.base.Result;

/**
 * 系统参数
 * @author J
 *
 */
public interface IConfigService {
	
	/**
	 * 小程序系统参数
	 * @return
	 */
	public Result config();
	
	/**
	 * 根据code查询参数
	 * @param code
	 * @return
	 */
	public Result queryConfigValueByCode(String code);
	
	/**
	 * 根据code查询多个参数
	 * @param codeList
	 * @return
	 */
	public Result queryConfigByCodeList(List<String> codeList);
	
}
