package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.service.IVipMiniService;
import com.fast.system.log.FastLog;

@Service
public class VipMiniServiceImpl implements IVipMiniService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipminiMapper vipminiMapper;

	@Override
	public Result queryVipMiniByOpenid(Integer miniprogramid, String openid) {
		Result result = new Result();

		try {
			MVipminiExample vipminiExample = new MVipminiExample();
			vipminiExample.createCriteria().andOpenidEqualTo(openid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> vipminis = vipminiMapper.selectByExample(vipminiExample);
			if (vipminis != null && vipminis.size() > 0) {
				result.setData(vipminis.get(0));
				result.setErrcode(Integer.valueOf(0));
			} else {
				result.setMessage("会员无效");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipMiniServiceImpl.queryVipMiniByOpenid报错：", e);
		}

		return result;
	}

}
