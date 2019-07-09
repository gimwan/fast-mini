package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MSizeMapper;
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MSizeExample;
import com.fast.service.ISizeService;
import com.fast.system.log.FastLog;

/**
 * 尺码
 * @author J
 *
 */
@Service
public class SizeServiceImpl implements ISizeService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MSizeMapper sizeMapper;

	@Override
	public Result size() {
		Result result = new Result();

		try {
			MSizeExample example = new MSizeExample();
			example.setOrderByClause("code asc");
			List<MSize> list = sizeMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用SizeServiceImpl.size报错：", e);
		}

		return result;
	}

}
