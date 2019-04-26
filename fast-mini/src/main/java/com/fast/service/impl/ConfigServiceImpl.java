package com.fast.service.impl;

import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.service.IConfigService;
import com.fast.system.log.FastLog;

/**
 * 系统参数
 * @author gimwan
 *
 */
@Service
public class ConfigServiceImpl implements IConfigService {

	@Override
	public Result miniProgramConfig() {
		Result result = new Result();

		try {
			FastLog.debug("test debug");
			FastLog.error("test error");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigServiceImpl.miniProgramConfig报错：", e);
		}

		return result;
	}

}
