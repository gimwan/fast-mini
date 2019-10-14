package com.fast.service.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast.base.Result;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipcouponExample;
import com.fast.base.data.entity.MVipmini;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipCouponMaintService;
import com.fast.service.IVipMiniService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

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
	
	@Autowired
	IMiniProgramService iMiniProgramService;

	@Autowired
	IVipMiniService iVipMiniService;
	
	@Override
	public Result addVipCoupon(Integer couponID, Integer VipID, Integer quantity) {
		Result result = new Result();

		try {			
			MCoupon coupon = couponMapper.selectByPrimaryKey(couponID);
			if (coupon == null || coupon.getId() == null) {
				result.setMessage("优惠券不存在");
				return result;
			}
			
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
			
			saveData(VipID, coupon, quantity);
	        
	        result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipCouponMaintServiceImpl.addVipCoupon报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveData(Integer vipid, MCoupon coupon, Integer quantity) {
		Date now = new Date();
		
		Date beginTime = coupon.getBegintime();
		Date endTime = coupon.getBegintime();		
		if (coupon.getTimetype().intValue() == 2) {
			Calendar ca = Calendar.getInstance();
			ca.setTime(now);
			ca.set(Calendar.HOUR_OF_DAY, 0);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			beginTime = ca.getTime();
			
			Integer effectiveTime = coupon.getEffectivetime();
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(now);
	        calendar.add(Calendar.DATE, effectiveTime);
	        calendar.set(Calendar.HOUR_OF_DAY, 23);
	        calendar.set(Calendar.MINUTE, 59);
	        calendar.set(Calendar.SECOND, 59);
	        endTime = calendar.getTime();
		}
        
		for (int i = 0; i < quantity.intValue(); i++) {
        	MVipcoupon vipcoupon = new MVipcoupon();
	        vipcoupon.setVipid(vipid);
	        vipcoupon.setCouponid(coupon.getId());
	        vipcoupon.setGettime(now);
	        vipcoupon.setBegintime(beginTime);
	        vipcoupon.setEndtime(endTime);
	        vipcoupon.setUseflag(Byte.valueOf("0"));
	        vipcoupon.setCreatetime(now);
	        vipcoupon.setCreator("system");
	        vipcoupon.setUpdatedtime(now);
	        vipcoupon.setCode(getCouponCode());
	        vipcouponMapper.insertSelective(vipcoupon);
		}
	}
	
	/**
	 * 随机券号
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

	@Override
	public Result gainVipCoupon(String appid, String openid, Integer id) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			MMiniprogram miniprogram = new MMiniprogram();
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				miniprogram = (MMiniprogram) r.getData();
			} else {
				return r;
			}
			MVipmini vipmini = new MVipmini();
			r = iVipMiniService.queryVipMiniByOpenid(miniprogram.getId(), openid);
			if (Common.isActive(r)) {
				vipmini = (MVipmini) r.getData();
			} else {
				return r;
			}
			
			result = addVipCoupon(id, vipmini.getVipid(), 1);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipCouponMaintServiceImpl.gainVipCoupon报错：", e);
		}

		return result;
	}

}
