package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.service.IBrandService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 品牌
 * @author J
 *
 */
@Service
public class BrandServiceImpl implements IBrandService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MBrandMapper brandMapper;

	@Override
	public Result brand() {
		Result result = new Result();

		try {
			MBrandExample example = new MBrandExample();
			example.setOrderByClause("code asc");
			List<MBrand> list = brandMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用BrandServiceImpl.brand报错：", e);
		}

		return result;
	}
	
}
