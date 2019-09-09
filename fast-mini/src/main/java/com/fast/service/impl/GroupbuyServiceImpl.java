package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MGroupbuyMapper;
import com.fast.base.data.entity.MGroupbuy;
import com.fast.base.data.entity.MGroupbuyExample;
import com.fast.service.IDataService;
import com.fast.service.IGroupbuyService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.CommonUtil;

/**
 * 拼团活动
 * @author J
 *
 */
@Service
public class GroupbuyServiceImpl implements IGroupbuyService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGroupbuyMapper groupbuyMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result groupbuy() {
		Result result = new Result();

		try {
			MGroupbuyExample example = new MGroupbuyExample();
			example.setOrderByClause("code asc");
			List<MGroupbuy> list = groupbuyMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.groupbuy报错：", e);
		}

		return result;
	}

	@Override
	public Result groupbuyDetail(Integer groupbuyid) {
		Result result = new Result();

		try {
			String sql = "select * from m_groupbuydtl where groupbuyid=" + groupbuyid + " order by id";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "groupbuydtl");
			result.setData(list);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.groupbuyDetail报错：", e);
		}

		return result;
	}

}
