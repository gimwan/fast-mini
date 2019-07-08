package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MPublicplatformExample;
import com.fast.base.page.PagingView;
import com.fast.service.IPublicPlatformService;
import com.fast.system.log.FastLog;

/**
 * 公众号
 * @author J
 *
 */
@Service
public class PublicPlatformServiceImpl implements IPublicPlatformService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MPublicplatformMapper publicplatformMapper;
	
	/**
	 * 查询所有小程序
	 */
	@Override
	public Result publicplatform() {
		Result result = new Result();

		try {
			MPublicplatformExample example = new MPublicplatformExample();
			example.setOrderByClause("code asc");
			List<MPublicplatform> list = publicplatformMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用PublicPlatformServiceImpl.publicplatform报错：", e);
		}

		return result;
	}

}
