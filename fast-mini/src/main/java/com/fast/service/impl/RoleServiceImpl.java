package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MRoleMapper;
import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MRoleExample;
import com.fast.service.IRoleService;
import com.fast.system.log.FastLog;

/**
 * 角色管理
 * @author J
 *
 */
@Service
public class RoleServiceImpl implements IRoleService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MRoleMapper mRoleMapper;

	@Override
	public Result role() {
		Result result = new Result();

		try {
			MRoleExample example = new MRoleExample();
			example.setOrderByClause("code asc");
			List<MRole> list = mRoleMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用RoleServiceImpl.role报错：", e);
		}

		return result;
	}
}
