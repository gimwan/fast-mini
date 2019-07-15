package com.fast.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipcouponExample;
import com.fast.service.IVipCouponMaintService;
import com.fast.system.log.FastLog;

/**
 * 优惠券
 * @author J
 *
 */
@Service
public class VipCouponMaintServiceImpl implements IVipCouponMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	MCouponMapper couponMapper;

	@Override
	public Result addVipCoupon(Integer couponID, Integer VipID, Integer quantity) {
		Result result = new Result();

		try {
			MCoupon coupon = couponMapper.selectByPrimaryKey(couponID);
			
			if (coupon.getTotalquantity() != null && coupon.getTotalquantity().intValue() > 0) {
				MVipcouponExample example = new MVipcouponExample();
				example.createCriteria().andCouponidEqualTo(couponID);
				int getQuantity = vipcouponMapper.countByExample(example);
				getQuantity = getQuantity + quantity.intValue();
				if (getQuantity > coupon.getTotalquantity().intValue()) {
					result.setMessage("已超出发放总数");
					return result;
				}
			}
			
			if (coupon.getLimitquantity() != null && coupon.getLimitquantity().intValue() > 0) {
				MVipcouponExample example = new MVipcouponExample();
				example.createCriteria().andCouponidEqualTo(couponID).andVipidEqualTo(VipID);
				int vipQuantity = vipcouponMapper.countByExample(example);
				vipQuantity = vipQuantity + quantity.intValue();
				if (vipQuantity > coupon.getTotalquantity().intValue()) {
					result.setMessage("已超出每人限领数");
					return result;
				}
			}
			
			Integer effectiveTime = coupon.getEffectivetime();
			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(now);
	        calendar.add(Calendar.DATE, effectiveTime);
	        Date endTime = calendar.getTime();
	        
	        for (int i = 0; i < quantity.intValue(); i++) {
	        	MVipcoupon vipcoupon = new MVipcoupon();
		        vipcoupon.setVipid(VipID);
		        vipcoupon.setCouponid(couponID);
		        vipcoupon.setGettime(now);
		        vipcoupon.setBegintime(now);
		        vipcoupon.setEndtime(endTime);
		        vipcoupon.setUseflag(Byte.valueOf("0"));
		        vipcoupon.setCreatetime(now);
		        vipcoupon.setCreator("system");
		        vipcoupon.setUpdatedtime(now);
		        vipcoupon.setCode(getCouponCode());
		        vipcouponMapper.insertSelective(vipcoupon);
			}
	        result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipCouponMaintServiceImpl.addVipCoupon报错：", e);
		}

		return result;
	}
	
	/**
	 * 
	 * @return 获取随机卡号
	 * 现金券编号定长12位
	 */
	public String getCouponCode() {
		//编号长度
		Integer noLen = 12;
		String code = String.valueOf(System.currentTimeMillis());
		code = code.substring(code.length() - (noLen - 4));
		String randValue = "000" + (new Random()).nextInt(100000);
		randValue = randValue.substring(randValue.length() - 4);
		code = code + randValue;
		return code;
	}

}
