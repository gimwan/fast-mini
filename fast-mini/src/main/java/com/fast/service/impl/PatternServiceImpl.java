package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MPatternMapper;
import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MPatternExample;
import com.fast.base.page.PagingView;
import com.fast.service.IPatternService;
import com.fast.system.log.FastLog;

/**
 * 版型
 * @author J
 *
 */
@Service
public class PatternServiceImpl implements IPatternService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MPatternMapper patternMapper;

	@Override
	public Result pattern() {
		Result result = new Result();

		try {
			MPatternExample example = new MPatternExample();
			example.setOrderByClause("code asc");
			List<MPattern> list = patternMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用PatternServiceImpl.pattern报错：", e);
		}

		return result;
	}

}
