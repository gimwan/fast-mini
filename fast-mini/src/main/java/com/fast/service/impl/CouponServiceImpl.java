package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
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
	
	@Autowired
	DataMapper dataMapper;

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

	@Override
	public Result queryCouponSuitGoods(Integer couponid) {
		Result result = new Result();

		try {
			String sql = "select a.id,a.code,a.name,(a.code + ' ' + a.name) as title,case when b.id is not null then 1 else 0 end as checked "
					+ "from m_goods a "
					+ "left join m_coupongoods b on a.id=b.goodsid and couponid=" + couponid + " "
					+ "where a.useflag=1 "
					+ "order by a.code desc";
			List<LinkedHashMap<String, Object>>  list = dataMapper.pageList(sql);
			result.setData(list);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用CouponServiceImpl.queryCouponSuitGoods报错：", e);
		}

		return result;
	}

	@Override
	public Result queryCouponSuitDepartment(Integer couponid) {
		Result result = new Result();
		
		try {
			String sql = "select a.id,a.code,a.name,(a.code + ' ' + a.name) as title,case when b.id is not null then 1 else 0 end as checked "
					+ "from m_department a "
					+ "left join m_coupondepartment b on a.id=b.departmentid and couponid=" + couponid + " "
					+ "where a.useflag=1 "
					+ "order by a.code";
			List<LinkedHashMap<String, Object>>  list = dataMapper.pageList(sql);
			result.setData(list);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用CouponServiceImpl.queryCouponSuitDepartment报错：", e);
		}

		return result;
	}

}
