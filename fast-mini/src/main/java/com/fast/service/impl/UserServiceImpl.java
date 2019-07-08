package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MUserMapper;
import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MUserExample;
import com.fast.base.page.PagingView;
import com.fast.service.IUserService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 系统用户
 * @author J
 *
 */
@Service
public class UserServiceImpl implements IUserService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MUserMapper userMapper;
	
	/**
	 * 查询所有用户
	 * @return
	 */
	@Override
	public Result user() {
		Result result = new Result();

		try {
			MUserExample example = new MUserExample();
			example.setOrderByClause("code asc");
			List<MUser> list = userMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用UserServiceImpl.user报错：", e);
		}

		return result;
	}

	/**
	 * 登录校验
	 * @param code
	 * @param password
	 * @return
	 */
	@Override
	public Result checkLogin(String code, String password) {
		Result result = new Result();

		try {
			if (Common.isEmpty(code)) {
				result.setMessage("用户名或密码错误");
				return result;
			}
			if (Common.isEmpty(password)) {
				result.setMessage("用户名或密码错误");
				return result;
			}
			MUserExample mUserExample = new MUserExample();
			mUserExample.createCriteria().andCodeEqualTo(code);
			List<MUser> mUsersList = userMapper.selectByExample(mUserExample);
			if (mUsersList.size() < 1) {
				result.setMessage("用户名或密码错误");
				return result;
			} else {
				MUser mUser = mUsersList.get(0);
				
				result.setErrcode(0);
				result.setId(mUser.getId());
				result.setData(mUser);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用UserServiceImpl.checkLogin报错：", e);
		}

		return result;
	}

}
