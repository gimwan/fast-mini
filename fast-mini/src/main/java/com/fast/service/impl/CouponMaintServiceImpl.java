package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MCouponExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.ICouponMaintService;
import com.fast.service.IDataService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 优惠券
 * @author J
 *
 */
@Service
public class CouponMaintServiceImpl implements ICouponMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MCouponMapper couponMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeCoupon(MCoupon coupon, MUser user) {
		Result result = new Result();

		try {
			MCouponExample example = new MCouponExample();
			if (coupon.getId() != null) {
				example.createCriteria().andCodeEqualTo(coupon.getCode().trim()).andIdNotEqualTo(coupon.getId());
			} else {
				example.createCriteria().andCodeEqualTo(coupon.getCode().trim());
			}
			List<MCoupon> list = couponMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MCoupon mCoupon = new MCoupon();
			coupon.setUpdatedtime(now);
			if (coupon.getId() != null) {
				mCoupon = couponMapper.selectByPrimaryKey(coupon.getId());
				BeanUtil.copyPropertiesIgnoreNull(coupon, mCoupon);
				mCoupon.setModifier(user.getName());
				mCoupon.setModifytime(now);
				int changeNum = couponMapper.updateByPrimaryKeySelective(mCoupon);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mCoupon.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(coupon, mCoupon);
				mCoupon.setCreator(user.getName());
				mCoupon.setCreatetime(now);
				int key = couponMapper.insertSelective(mCoupon);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mCoupon.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("coupon", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用CouponMaintServiceImpl.changeCoupon报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteCoupon(Integer id) {
		Result result = new Result();

		try {
			int i = couponMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用CouponMaintServiceImpl.deleteCoupon报错：", e);
		}

		return result;
	}

}
