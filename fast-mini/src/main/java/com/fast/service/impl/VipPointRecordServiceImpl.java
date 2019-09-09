package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MVippointrecordMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.page.PagingView;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipMiniService;
import com.fast.service.IVipPointRecordService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 会员积分记录
 * @author J
 *
 */
@Service
public class VipPointRecordServiceImpl implements IVipPointRecordService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVippointrecordMapper vippointrecordMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IVipMiniService iVipMiniService;
	
	@Autowired
	DataMapper dataMapper;

	@Override
	public Result queryVipPointRecordByOpenid(String appid, String openid, PagingView page) {
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
			page.setOrderBy(" order by updatedtime desc");
			String sql = "select id,vipid,point,case type when 1 then '订单支付' when 2 then '取消订单' else '其它' end as source,"
					+ "convert(varchar(100), updatedtime, 20) as time "
					+ "from m_vippointrecord "
					+ "where vipid=" + vipmini.getVipid();
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);			
			page.setData(list);
			
			result.setData(page);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipPointRecordServiceImpl.queryVipPointRecordByOpenid报错：", e);
		}

		return result;
	}

}
