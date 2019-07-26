package com.fast.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.dao.MVipaddressMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MVipaddress;
import com.fast.base.data.entity.MVipaddressExample;
import com.fast.base.data.entity.MVipmini;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipAddressService;
import com.fast.service.IVipMiniService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 收货地址
 * @author J
 *
 */
@Service
public class VipAddressServiceImpl implements IVipAddressService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipaddressMapper vipaddressMapper;
	
	@Autowired
	MVipminiMapper vipminiMapper;
	
	@Autowired
	MMiniprogramMapper miniprogramMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IVipMiniService iVipMiniService;

	@Override
	public Result vipAddress(String appid, String openid) {
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
			MVipaddressExample example = new MVipaddressExample();
			example.createCriteria().andVipidEqualTo(vipmini.getVipid());
			example.setOrderByClause(" updatedtime desc");
			List<MVipaddress> list = vipaddressMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setData(data);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用VipAddressService.vipAddress报错：", e);
		}
	
		return result;
	}
	
	

}
