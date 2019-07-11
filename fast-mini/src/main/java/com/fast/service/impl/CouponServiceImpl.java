package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MCouponExample;
import com.fast.service.ICouponService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 优惠券
 * @author J
 *
 */
@Service
public class CouponServiceImpl implements ICouponService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MCouponMapper couponMapper;

	@Override
	public Result coupon() {
		Result result = new Result();

		try {
			MCouponExample example = new MCouponExample();
			example.setOrderByClause("code asc");
			List<MCoupon> list = couponMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用CouponServiceImpl.coupon报错：", e);
		}

		return result;
	}

}
