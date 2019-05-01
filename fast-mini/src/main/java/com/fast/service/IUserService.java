package com.fast.service;

import com.fast.base.Result;

public interface IUserService {
	
	/**
	 * 登录检验
	 * @param code  用户编号
	 * @param password  用户密码
	 * @return
	 */
	public Result checkLogin(String code, String password);

}
