package com.fast.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.page.PagingView;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipCouponService;
import com.fast.service.IVipMiniService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 会员优惠券
 * @author J
 *
 */
@Service
public class VipCouponServiceImpl implements IVipCouponService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IVipMiniService iVipMiniService;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	MCouponMapper couponMapper;

	@Override
	public Result queryVipCoupon(String appid, String openid, Integer type, PagingView page) {
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
			
			List<LinkedHashMap<String, Object>> list = new ArrayList<LinkedHashMap<String,Object>>();
			if (type.intValue() == 2) {
				page.setOrderBy(" order by a.endtime desc");
				String sql = "select a.id,vipid,a.couponid,a.code,b.name,case when b.suittype=2 then isnull(b.amount,1)*10 else isnull(b.amount,0) end as amount,"
						+ "b.enableamount,convert(varchar(100), a.begintime, 20) as begintime,"
						+ "convert(varchar(100), a.endtime, 20) as endtime,b.suittype,isnull(b.hint,'') as hint,isnull(b.color,'') as color "
						+ "from m_vipcoupon a "
						+ "inner join m_coupon b on a.couponid=b.id "
						+ "where a.useflag=0 and a.endtime<=getdate() and a.vipid=" + vipmini.getVipid() + " ";
				list = dataMapper.pageList(sql, page);
			} else if (type.intValue() == 1) {
				page.setOrderBy(" order by a.usetime desc");
				String sql = "select a.id,vipid,a.couponid,a.code,b.name,case when b.suittype=2 then isnull(b.amount,1)*10 else isnull(b.amount,0) end as amount,"
						+ "b.enableamount,convert(varchar(100), a.begintime, 20) as begintime,"
						+ "convert(varchar(100), a.endtime, 20) as endtime,b.suittype,isnull(b.hint,'') as hint,isnull(b.color,'') as color "
						+ "from m_vipcoupon a "
						+ "inner join m_coupon b on a.couponid=b.id "
						+ "where a.useflag=1 and a.vipid=" + vipmini.getVipid() + " ";
				list = dataMapper.pageList(sql, page);
			} else {
				page.setOrderBy(" order by a.gettime desc");
				String sql = "select a.id,vipid,a.couponid,a.code,b.name,case when b.suittype=2 then isnull(b.amount,1)*10 else isnull(b.amount,0) end as amount,"
						+ "b.enableamount,convert(varchar(100), a.begintime, 20) as begintime,"
						+ "convert(varchar(100), a.endtime, 20) as endtime,b.suittype,isnull(b.hint,'') as hint,isnull(b.color,'') as color "
						+ "from m_vipcoupon a "
						+ "inner join m_coupon b on a.couponid=b.id "
						+ "where a.useflag=0 and a.endtime>getdate() and a.vipid=" + vipmini.getVipid() + " ";
				list = dataMapper.pageList(sql, page);
			}
			page.setData(list);
			
			result.setData(page);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipCouponServiceImpl.queryVipCoupon报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipCouponDetail(String appid, String openid, Integer id) {
		Result result = new Result();
		try {
//			if (Common.isEmpty(openid)) {
//				result.setMessage("openid无效");
//				return result;
//			}
//			MMiniprogram miniprogram = new MMiniprogram();
//			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
//			if (Common.isActive(r)) {
//				miniprogram = (MMiniprogram) r.getData();
//			} else {
//				return r;
//			}
//			MVipmini vipmini = new MVipmini();
//			r = iVipMiniService.queryVipMiniByOpenid(miniprogram.getId(), openid);
//			if (Common.isActive(r)) {
//				vipmini = (MVipmini) r.getData();
//			} else {
//				return r;
//			}
			
			MVipcoupon vipcoupon = vipcouponMapper.selectByPrimaryKey(id);
			if (vipcoupon == null || vipcoupon.getId() == null) {
				result.setMessage("无效优惠券");
				return result;
			}
			MCoupon coupon = couponMapper.selectByPrimaryKey(vipcoupon.getCouponid());
			if (coupon == null || coupon.getId() == null) {
				result.setMessage("无效优惠券");
				return result;
			}
			
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("id", vipcoupon.getId());
			data.put("code", vipcoupon.getCode());
			data.put("name", coupon.getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			String begintime = sdf.format(vipcoupon.getBegintime());
			String endtime = sdf.format(vipcoupon.getEndtime());
			data.put("begintime", begintime);
			data.put("endtime", endtime);
			String hint = coupon.getHint() == null ? "" : coupon.getHint();
			data.put("hint", hint);
			String color = coupon.getColor() == null ? "" : coupon.getColor();
			data.put("color", color);
			data.put("memo", coupon.getMemo());
			
			result.setData(data);
			result.setId(vipcoupon.getId());
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipCouponServiceImpl.queryVipCouponDetail报错：", e);
		}
		return result;
	}

}
