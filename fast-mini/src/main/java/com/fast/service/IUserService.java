package com.fast.service;

import com.fast.base.Result;

/**
 * 用户管理
 * @author J
 *
 */
public interface IUserService {
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public Result user();
	
	/**
	 * 登录检验
	 * @param code  用户编号
	 * @param password  用户密码
	 * @return
	 */
	public Result checkLogin(String code, String password);

}
