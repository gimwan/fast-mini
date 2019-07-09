package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import com.fast.service.IViptypeService;
import com.fast.system.log.FastLog;

/**
 * 会员等级
 * @author J
 *
 */
@Service
public class ViptypeServiceImpl implements IViptypeService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MViptypeMapper viptypeMapper;

	/**
	 * 查询所有会员等级
	 */
	@Override
	public Result viptype() {
		Result result = new Result();

		try {
			MViptypeExample example = new MViptypeExample();
			example.setOrderByClause("code asc");
			List<MViptype> list = viptypeMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ViptypeServiceImpl.viptype报错：", e);
		}

		return result;
	}

}
