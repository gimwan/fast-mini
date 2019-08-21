package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.service.IExtSystemService;
import com.fast.system.log.FastLog;

/**
 * 应用接口
 * @author J
 *
 */
@Service
public class ExtSystemServiceImpl implements IExtSystemService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MExtsystemMapper extsystemMapper;

	@Override
	public Result extsystem() {
		Result result = new Result();

		try {
			MExtsystemExample example = new MExtsystemExample();
			example.setOrderByClause("code asc");
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtSystemServiceImpl.extsystem报错：", e);
		}

		return result;
	}

}
