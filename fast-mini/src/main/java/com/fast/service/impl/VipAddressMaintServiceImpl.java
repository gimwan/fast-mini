package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MRegionMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaddressMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MRegion;
import com.fast.base.data.entity.MRegionExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipaddress;
import com.fast.base.data.entity.MVipaddressExample;
import com.fast.base.data.entity.MVipmini;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipAddressMaintService;
import com.fast.service.IVipMiniService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 收货地址
 * @author J
 *
 */
@Service
public class VipAddressMaintServiceImpl implements IVipAddressMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipaddressMapper vipaddressMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IVipMiniService iVipMiniService;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MRegionMapper regionMapper;

	@Override
	public Result saveVipAddress(String appid, String openid, Integer id, String receiver, String phone,
			Integer provinceid, Integer cityid, Integer countyid, String address, Integer isDefault) {
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
			MVip vip = vipMapper.selectByPrimaryKey(vipmini.getVipid());
			Date now = new Date();
			MVipaddress vipaddress = new MVipaddress();
			if (id != null && id.intValue() > 0) {
				vipaddress = vipaddressMapper.selectByPrimaryKey(id);
				if (vipaddress == null || vipaddress.getId() == null) {
					result.setMessage("收货地址无效");
					return result;
				}
			}
			vipaddress.setReceiver(receiver);
			vipaddress.setPhone(phone);
			vipaddress.setProvinceid(provinceid);
			vipaddress.setCityid(cityid);
			vipaddress.setCountyid(countyid);
			vipaddress.setAddress(address);
			vipaddress.setIsdefault(Byte.valueOf(isDefault.toString()));
			vipaddress = resetVipAddressRegion(vipaddress);
			
			if (vipaddress.getId() != null && vipaddress.getId().intValue() > 0) {
				vipaddress.setModifier(vip.getName());
				vipaddress.setModifytime(now);
				vipaddress.setUpdatedtime(now);
				vipaddress = resetVipAddressRegion(vipaddress);
				vipaddressMapper.updateByPrimaryKeySelective(vipaddress);
			}
			// 新增
			else {
				vipaddress.setVipid(vip.getId());
				vipaddress.setCreator(vip.getName());
				vipaddress.setCreatetime(now);
				vipaddress.setUpdatedtime(now);
				vipaddressMapper.insertSelective(vipaddress);
			}
			
			// 默认地址只有一个
			if (vipaddress.getIsdefault().intValue() == 1) {
				MVipaddressExample example = new MVipaddressExample();
				example.createCriteria().andVipidEqualTo(vip.getId()).andIdNotEqualTo(vipaddress.getId()).andIsdefaultEqualTo(Byte.valueOf("1"));
				List<MVipaddress> list = vipaddressMapper.selectByExample(example);
				for (int i = 0; i < list.size(); i++) {
					MVipaddress adr = list.get(i);
					adr.setIsdefault(Byte.valueOf("0"));
					vipaddressMapper.updateByPrimaryKeySelective(adr);
				}
			}
			
			result.setId(vipaddress.getId());
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用VipAddressMaintServiceImpl.saveVipAddress报错：", e);
		}
	
		return result;
	}

	public MVipaddress resetVipAddressRegion(MVipaddress vipaddress) {
		List<Integer> idList = new ArrayList<Integer>();
		if (vipaddress.getProvinceid() != null) {
			idList.add(vipaddress.getProvinceid());
		}
		if (vipaddress.getCityid() != null) {
			idList.add(vipaddress.getCityid());
		}
		if (vipaddress.getCountyid() != null) {
			idList.add(vipaddress.getCountyid());
		}
		MRegionExample example = new MRegionExample();
		example.createCriteria().andIdIn(idList);
		List<MRegion> list = regionMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			MRegion region = list.get(i);
			if (vipaddress.getProvinceid() != null && region.getId().intValue() == vipaddress.getProvinceid().intValue()) {
				vipaddress.setProvince(region.getName());
			} else if (vipaddress.getCityid() != null && region.getId().intValue() == vipaddress.getCityid().intValue()) {
				vipaddress.setCity(region.getName());
			} else if (vipaddress.getCountyid() != null && region.getId().intValue() == vipaddress.getCountyid().intValue()) {
				vipaddress.setCounty(region.getName());
			}
		}
		return vipaddress;
	}

	@Override
	public Result deleteVipAddress(String appid, String openid, Integer id) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (!Common.isActive(r)) {
				return r;
			}
			vipaddressMapper.deleteByPrimaryKey(id);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用VipAddressMaintServiceImpl.deleteVipAddress报错：", e);
		}
	
		return result;
	}
}
