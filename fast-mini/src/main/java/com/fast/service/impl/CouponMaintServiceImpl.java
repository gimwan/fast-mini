package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast.base.Result;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MCoupondepartmentMapper;
import com.fast.base.data.dao.MCoupongoodsMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MCouponExample;
import com.fast.base.data.entity.MCoupondepartment;
import com.fast.base.data.entity.MCoupondepartmentExample;
import com.fast.base.data.entity.MCoupongoods;
import com.fast.base.data.entity.MCoupongoodsExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.ICouponMaintService;
import com.fast.service.IDataService;
import com.fast.service.ext.IExtMaintService;
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
	
	@Autowired
	MCoupongoodsMapper mCoupongoodsMapper;
	
	@Autowired
	MCoupondepartmentMapper mCoupondepartmentMapper;
	
	@Autowired
	IExtMaintService iExtMaintService;

	@Override
	public Result changeCoupon(MCoupon coupon, MUser user, String suitGoodsStr, String suitDepartmentsStr) {
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
			
			result = saveData(coupon, user, suitGoodsStr, suitDepartmentsStr);
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("coupon", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
				
				if (coupon.getModifytime() != null) {
					syncCoupon(coupon.getId());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用CouponMaintServiceImpl.changeCoupon报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	private Result saveData(MCoupon coupon, MUser user,String suitGoodsStr, String suitDepartmentsStr) {
		Result result = new Result();
		
		Date now = new Date();
		MCoupon mCoupon = new MCoupon();
		coupon.setUpdatedtime(now);
		if (coupon.getId() != null) {
			mCoupon = couponMapper.selectByPrimaryKey(coupon.getId());
			BeanUtil.copyPropertiesIgnoreNull(coupon, mCoupon);
			mCoupon.setModifier(user.getName());
			mCoupon.setModifytime(now);
			couponMapper.updateByPrimaryKeySelective(mCoupon);
		} else {
			BeanUtil.copyPropertiesIgnoreNull(coupon, mCoupon);
			mCoupon.setCreator(user.getName());
			mCoupon.setCreatetime(now);
			couponMapper.insertSelective(mCoupon);
		}
		
		MCoupongoodsExample coupongoodsExample = new MCoupongoodsExample();
		coupongoodsExample.createCriteria().andCouponidEqualTo(mCoupon.getId());
		mCoupongoodsMapper.deleteByExample(coupongoodsExample);
		
		if (mCoupon.getSuitgoodstype().intValue() == 1) {
			String suitGoodsData = Common.unescape(suitGoodsStr);
			String[] suitGoodsArray = suitGoodsData.split(",");
			for (int i = 0; i < suitGoodsArray.length; i++) {
				MCoupongoods coupongoods = new MCoupongoods();
				coupongoods.setCouponid(mCoupon.getId());
				coupongoods.setGoodsid(Integer.valueOf(suitGoodsArray[i]));
				coupongoods.setShowindex(i);
				coupongoods.setUpdatedtime(now);
				mCoupongoodsMapper.insertSelective(coupongoods);
			}
		}
		
		MCoupondepartmentExample coupondepartmentExample = new MCoupondepartmentExample();
		coupondepartmentExample.createCriteria().andCouponidEqualTo(mCoupon.getId());
		mCoupondepartmentMapper.deleteByExample(coupondepartmentExample);
		
		if (mCoupon.getSuitdepartmenttype().intValue() ==1) {
			String suitDepartmentsData = Common.unescape(suitDepartmentsStr);
			String[] suitDepartmentsArray = suitDepartmentsData.split(",");
			for (int i = 0; i < suitDepartmentsArray.length; i++) {
				MCoupondepartment coupondepartment = new MCoupondepartment();
				coupondepartment.setCouponid(mCoupon.getId());
				coupondepartment.setDepartmentid(Integer.valueOf(suitDepartmentsArray[i]));
				coupondepartment.setShowindex(i);
				coupondepartment.setUpdatedtime(now);
				mCoupondepartmentMapper.insertSelective(coupondepartment);
			}
		}
		
		result.setErrcode(0);
		result.setId(mCoupon.getId());
		result.setMessage("保存成功");
		return result;
	}
	
	public void syncCoupon(Integer couponid) {
		try {
			syncCouponThread thread = new syncCouponThread();
            thread.setCouponid(couponid);
            Thread t = new Thread(thread);
            t.start();
		} catch (Exception e) {
			FastLog.error("调用CouponMaintServiceImpl.syncCoupon报错：", e);
		}
	}
	
	public class syncCouponThread implements Runnable {
		private Integer couponid;	
		
		public Integer getCouponid() {
			return couponid;
		}
		
		public void setCouponid(Integer couponid) {
			this.couponid = couponid;
		}
		
		@Override
		public void run() {
			iExtMaintService.updateCoupon(couponid);
		}
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
