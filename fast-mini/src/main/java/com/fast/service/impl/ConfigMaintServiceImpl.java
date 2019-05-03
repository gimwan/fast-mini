package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MConfigMapper;
import com.fast.base.data.entity.MConfig;
import com.fast.service.IConfigMaintService;
import com.fast.system.log.FastLog;

/**
 * 系统参数
 * @author J
 *
 */
@Service
public class ConfigMaintServiceImpl implements IConfigMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MConfigMapper mConfigMapper;

	@Override
	public Result changeConfig(Integer id, String value) {
		Result result = new Result();

		try {
			Date now = new Date();
			MConfig mConfig = new MConfig();
			mConfig.setId(id);
			mConfig.setValue(value.trim());
			mConfig.setModifier("");
			mConfig.setModifytime(now);
			mConfig.setUpdatedtime(now);
			int changeNum = mConfigMapper.updateByPrimaryKeySelective(mConfig);
			if (changeNum > 0) {
				result.setErrcode(0);
				result.setId(id);
				result.setMessage("保存成功");
			} else {
				result.setMessage("保存失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ConfigMaintServiceImpl.changeConfig报错：", e);
		}

		return result;
	}

}
