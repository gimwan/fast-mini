package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MConfigMapper;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MConfigExample;
import com.fast.service.IConfigService;
import com.fast.system.log.FastLog;

/**
 * 系统参数
 * @author J
 *
 */
@Service
public class ConfigServiceImpl implements IConfigService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MConfigMapper mConfigMapper;

	@Override
	public Result config() {
		Result result = new Result();

		try {
			MConfigExample example = new MConfigExample();
			example.setOrderByClause("code asc");
			List<MConfig> list = mConfigMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigServiceImpl.config报错：", e);
		}

		return result;
	}

}
