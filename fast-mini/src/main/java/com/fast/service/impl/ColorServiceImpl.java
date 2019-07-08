package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;
import com.fast.base.page.PagingView;
import com.fast.service.IColorService;
import com.fast.system.log.FastLog;

/**
 * 颜色
 * @author J
 *
 */
@Service
public class ColorServiceImpl implements IColorService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MColorMapper colorMapper;

	@Override
	public Result color() {
		Result result = new Result();

		try {
			MColorExample example = new MColorExample();
			example.setOrderByClause("code asc");
			List<MColor> list = colorMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ColorServiceImpl.color报错：", e);
		}

		return result;
	}

}
