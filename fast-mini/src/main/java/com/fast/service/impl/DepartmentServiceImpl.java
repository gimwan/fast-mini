package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MDepartmentMapper;
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MDepartmentExample;
import com.fast.base.page.PagingView;
import com.fast.service.IDepartmentService;
import com.fast.system.log.FastLog;

/**
 * 门店管理
 * @author J
 *
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MDepartmentMapper mDepartmentMapper;
	
	@Override
	public Result department() {
		Result result = new Result();

		try {
			MDepartmentExample example = new MDepartmentExample();
			example.setOrderByClause("code asc");
			List<MDepartment> list = mDepartmentMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用DepartmentServiceImpl.department报错：", e);
		}

		return result;
	}

}
