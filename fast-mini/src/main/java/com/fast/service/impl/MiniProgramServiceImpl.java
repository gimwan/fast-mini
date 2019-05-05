package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MMiniprogramExample;
import com.fast.service.IMiniProgramService;
import com.fast.system.log.FastLog;

/**
 * 小程序
 * @author J
 *
 */
@Service
public class MiniProgramServiceImpl implements IMiniProgramService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMiniprogramMapper mMiniprogramMapper;
	
	/**
	 * 查询所有小程序
	 */
	@Override
	public Result miniprogram() {
		Result result = new Result();

		try {
			MMiniprogramExample example = new MMiniprogramExample();
			example.setOrderByClause("code asc");
			List<MMiniprogram> list = mMiniprogramMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramServiceImpl.miniprogram报错：", e);
		}

		return result;
	}

}
