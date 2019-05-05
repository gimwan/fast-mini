package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MEmployeeMapper;
import com.fast.base.data.entity.MEmployee;
import com.fast.base.data.entity.MEmployeeExample;
import com.fast.service.IEmployeeService;
import com.fast.system.log.FastLog;

/**
 * 员工管理
 * @author J
 *
 */
@Service
public class EmployeeImpl implements IEmployeeService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MEmployeeMapper mEmployeeMapper;

	@Override
	public Result employee() {
		Result result = new Result();

		try {
			MEmployeeExample example = new MEmployeeExample();
			example.setOrderByClause("code asc");
			List<MEmployee> list = mEmployeeMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用EmployeeImpl.employee报错：", e);
		}

		return result;
	}

}
