package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MGoodsgroupingMapper;
import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MGoodsgroupingExample;
import com.fast.service.IGoodsGroupingService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 分组
 * @author J
 *
 */
@Service
public class GoodsGroupingServiceImpl implements IGoodsGroupingService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGoodsgroupingMapper goodsgroupingMapper;

	@Override
	public Result goodsGrouping() {
		Result result = new Result();

		try {
			MGoodsgroupingExample example = new MGoodsgroupingExample();
			example.setOrderByClause("code asc");
			List<MGoodsgrouping> list = goodsgroupingMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsGroupingServiceImpl.goodsGrouping报错：", e);
		}

		return result;
	}

}
