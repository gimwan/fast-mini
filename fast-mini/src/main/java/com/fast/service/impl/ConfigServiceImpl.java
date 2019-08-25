package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MConfigMapper;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MConfigExample;
import com.fast.service.IConfigService;
import com.fast.service.IDataService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

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
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result config() {
		Result result = new Result();

		try {
			String sql = "select * from m_config where useflag=1 order by code asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "config");
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigServiceImpl.config报错：", e);
		}

		return result;
	}

	@Override
	public Result queryConfigValueByCode(String code) {
		Result result = new Result();

		try {
			String value = "";
			
			if (Common.isEmpty(code)) {
				result.setMessage("code无效");
				return result;
			}
			
			MConfigExample example = new MConfigExample();
			example.createCriteria().andCodeEqualTo(code.trim());
			List<MConfig> list = mConfigMapper.selectByExample(example);
			if (list.size() > 0) {
				value = list.get(0).getValue();
			}
			result.setErrcode(0);
			result.setData(value);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigServiceImpl.queryConfigByCode报错：", e);
		}

		return result;
	}
	
	@Override
	public Result queryConfigByCode(String code) {
		Result result = new Result();

		try {			
			if (Common.isEmpty(code)) {
				result.setMessage("code无效");
				return result;
			}
			
			MConfig config = new MConfig();
			MConfigExample example = new MConfigExample();
			example.createCriteria().andCodeEqualTo(code.trim());
			List<MConfig> list = mConfigMapper.selectByExample(example);
			if (list.size() > 0) {
				config = list.get(0);
			}
			result.setErrcode(0);
			result.setData(config);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigServiceImpl.queryConfigByCode报错：", e);
		}

		return result;
	}

	@Override
	public Result queryConfigByCodeList(List<String> codeList) {
		Result result = new Result();

		try {
			if (codeList == null || codeList.size() < 1) {
				result.setMessage("List<code>无效");
				return result;
			}
			
			MConfigExample example = new MConfigExample();
			example.createCriteria().andCodeIn(codeList);
			example.setOrderByClause("code asc");
			List<MConfig> list = mConfigMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigServiceImpl.queryConfigByCodeList报错：", e);
		}

		return result;
	}

}
