package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MRegionMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipaddressMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MRegion;
import com.fast.base.data.entity.MRegionExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipaddress;
import com.fast.base.data.entity.MVipaddressExample;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipcouponExample;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.base.data.entity.MViptype;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipMiniService;
import com.fast.service.IVipService;
import com.fast.service.ext.IExtMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 会员
 * @author J
 *
 */
@Service
public class VipServiceImpl implements IVipService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MVipminiMapper vipminiMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	MRegionMapper regionMapper;
	
	@Autowired
	IVipMiniService iVipMiniService;
	
	@Autowired
	MVipaddressMapper vipaddressMapper;
	
	@Autowired
	IExtMaintService iExtMaintService;
	
	@Override
	public Result vip() {
		Result result = new Result();

		try {
			MVipExample example = new MVipExample();
			example.setOrderByClause("createtime desc");
			List<MVip> list = vipMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.vip报错：", e);
		}

		return result;
	}
	
	@Override
	public Result queryVipByOpenid(String appid, String openid) {
		Result result = new Result();
		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			Integer miniprogramid = 0;
			Result r = iMiniProgramService.queryMiniprogramIDByAppid(appid);
			if (Common.isActive(r)) {
				miniprogramid = (Integer) r.getData();
			}			
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andOpenidEqualTo(openid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> list = vipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					MVip vip = vipMapper.selectByPrimaryKey(vipid);
					if (vip != null && vip.getId() != null) {
						HashMap<String, Object> map = BeanUtil.convertObjToMap(vip);
						MViptype viptype = viptypeMapper.selectByPrimaryKey(vip.getTypeid());
						map.put("type", viptype.getName());
						try {
							Object object = map.get("birthday");
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
					        String birthday = format.format(object);
					        map.put("birthday", birthday);
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						result.setData(map);
						result.setId(vip.getId());
						result.setErrcode(Integer.valueOf(0));
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipByOpenid报错：", e);
		}
		return result;
	}

	@Override
	public Result queryVipByUnionid(String appid, String unionid) {
		Result result = new Result();
		try {
			if (Common.isEmpty(unionid)) {
				result.setMessage("unionid无效");
				return result;
			}
			Integer miniprogramid = 0;
			Result r = iMiniProgramService.queryMiniprogramIDByAppid(appid);
			if (Common.isActive(r)) {
				miniprogramid = (Integer) r.getData();
			}			
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andUnionidEqualTo(unionid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> list = vipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					MVip vip = vipMapper.selectByPrimaryKey(vipid);
					if (vip != null && vip.getId() != null) {
						result.setData(vip);
						result.setId(vip.getId());
						result.setErrcode(Integer.valueOf(0));
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipByUnionid报错：", e);
		}
		return result;
	}

	@Override
	public Result defaultLogin(String appid, String unionid, String openid, Integer guideid, Integer departmentid) {
		Result result = new Result();
		try {
			if (Common.isEmpty(openid) && Common.isEmpty(unionid)) {
				result.setMessage("openid、unionid都无效");
				return result;
			}
			
			boolean login = false;
			Integer miniprogramid = 0;
			Result r = iMiniProgramService.queryMiniprogramIDByAppid(appid);
			if (Common.isActive(r)) {
				miniprogramid = (Integer) r.getData();
			}
			// 使用openid查找会员			
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andOpenidEqualTo(openid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> list = vipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					MVip vip = vipMapper.selectByPrimaryKey(vipid);
					if (vip != null && vip.getId() != null) {
						result.setData(vip);
						result.setId(vip.getId());
						result.setErrcode(Integer.valueOf(0));
						login = true;
					}
				}
			}			
			// openid查找不到会员，则使用unionid查找会员
			if (!login) {
				example = new MVipminiExample();
				example.createCriteria().andUnionidEqualTo(unionid).andMiniprogramidEqualTo(miniprogramid);
				list = vipminiMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					Integer vipid = list.get(0).getVipid();
					if (vipid != null) {
						MVip vip = vipMapper.selectByPrimaryKey(vipid);
						if (vip != null && vip.getId() != null) {
							result.setData(vip);
							result.setId(vip.getId());
							result.setErrcode(Integer.valueOf(0));
							login = true;
						}
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.defaultLogin报错：", e);
		}

		return result;
	}
	
	@Override
	public Result queryVipAccount(String appid, String openid) {
		Result result = new Result();
		try {
			result = queryVipByOpenid(appid, openid);
			if (Common.isActive(result)) {
				JSONObject object = JSONObject.fromObject(result.getData());
				MVip vip = (MVip) JSONObject.toBean(object, MVip.class);
				if (vip != null && vip.getId() != null) {
					// 同步会员信息
					iExtMaintService.syncVip(vip.getId());
					MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(vip.getId());
					if (vipaccount != null && vipaccount.getId() != null) {
						result.setData(vipaccount);
						result.setId(vipaccount.getId());
						result.setErrcode(Integer.valueOf(0));
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipPoint报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipPoint(String appid, String openid) {
		Result result = new Result();
		try {
			result = queryVipAccount(appid, openid);
			if (Common.isActive(result)) {
				MVipaccount vipaccount = (MVipaccount) result.getData();
				if (vipaccount != null && vipaccount.getId() != null) {
					Integer point = vipaccount.getPoint() == null ? 0 : vipaccount.getPoint();
					result.setData(point);
					result.setId(vipaccount.getId());
					result.setErrcode(Integer.valueOf(0));
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipPoint报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipDeposit(String appid, String openid) {
		Result result = new Result();
		try {
			result = queryVipAccount(appid, openid);
			if (Common.isActive(result)) {
				MVipaccount vipaccount = (MVipaccount) result.getData();
				if (vipaccount != null && vipaccount.getId() != null) {
					BigDecimal deposit = vipaccount.getDeposit() == null ? BigDecimal.ZERO : vipaccount.getDeposit();
					result.setData(deposit);
					result.setId(vipaccount.getId());
					result.setErrcode(Integer.valueOf(0));
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipDeposit报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipCouponNumber(String appid, String openid) {
		Result result = new Result();
		try {
			Result r = queryVipByOpenid(appid, openid);
			if (Common.isActive(r)) {
				Date now = new Date();
				MVipcouponExample example = new MVipcouponExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("0")).andVipidEqualTo(Integer.valueOf(r.getId().toString())).andBegintimeLessThanOrEqualTo(now).andEndtimeGreaterThan(now);
				List<MVipcoupon> list = vipcouponMapper.selectByExample(example);
				
				result.setData(list.size());
				result.setId(r.getId());
				result.setErrcode(Integer.valueOf(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipCouponNumber报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipDPC(String appid, String openid) {
		Result result = new Result();
		try {
			Result r = queryVipByOpenid(appid, openid);
			if (Common.isActive(r)) {
				Date now = new Date();
				HashMap<String, Object> map = new HashMap<>();
				// 同步会员信息
				iExtMaintService.syncVip(Integer.valueOf(r.getId().toString()));
				
				MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(Integer.valueOf(r.getId().toString()));
				map.put("point", vipaccount.getPoint() == null ? 0 : vipaccount.getPoint());
				map.put("deposit", vipaccount.getDeposit() == null ? BigDecimal.ZERO : vipaccount.getDeposit());
				
				MVipcouponExample example = new MVipcouponExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("0")).andVipidEqualTo(Integer.valueOf(r.getId().toString())).andBegintimeLessThanOrEqualTo(now).andEndtimeGreaterThan(now);
				List<MVipcoupon> list = vipcouponMapper.selectByExample(example);
				map.put("coupon", list == null ? 0 : list.size());
				
				result.setData(map);
				result.setId(r.getId());
				result.setErrcode(Integer.valueOf(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipDPC报错：", e);
		}

		return result;
	}
	
	@Override
	public MVip resetVipRegion(MVip vip) {
		if (!Common.isEmpty(vip.getProvince())) {
			MRegionExample regionExample = new MRegionExample();
			regionExample.createCriteria().andTypeEqualTo(Byte.valueOf("2")).andNameLike("%"+vip.getProvince()+"%");
			List<MRegion> regions = regionMapper.selectByExample(regionExample);
			if (regions != null && regions.size() > 0) {
				vip.setProvince(regions.get(0).getName());
				vip.setProvinceid(regions.get(0).getId());
			}
		}
		if (!Common.isEmpty(vip.getCity())) {
			MRegionExample regionExample = new MRegionExample();
			regionExample.createCriteria().andTypeEqualTo(Byte.valueOf("3")).andNameLike("%"+vip.getCity()+"%");
			List<MRegion> regions = regionMapper.selectByExample(regionExample);
			if (regions != null && regions.size() > 0) {
				vip.setCity(regions.get(0).getName());
				vip.setCityid(regions.get(0).getId());
			}
		}
		if (!Common.isEmpty(vip.getCounty())) {
			MRegionExample regionExample = new MRegionExample();
			regionExample.createCriteria().andTypeEqualTo(Byte.valueOf("4")).andNameLike("%"+vip.getCounty()+"%");
			List<MRegion> regions = regionMapper.selectByExample(regionExample);
			if (regions != null && regions.size() > 0) {
				vip.setCounty(regions.get(0).getName());
				vip.setCountyid(regions.get(0).getId());
			}
		}
		return vip;
	}

	@Override
	public Result queryVipDefaultAddress(String appid, String openid) {
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
			
			MVipaddress vipaddress = new MVipaddress();
			MVipaddressExample example = new MVipaddressExample();
			example.createCriteria().andVipidEqualTo(vipmini.getVipid());
			example.setOrderByClause(" isdefault desc");
			List<MVipaddress> list = vipaddressMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				vipaddress = list.get(0);
			} else {
				result.setErrcode(Integer.valueOf(2));
				result.setMessage("无收货地址");
				return result;
			}
			
			HashMap<String, Object> map = (HashMap<String, Object>) result.getData();
			map.put("receiver", vipaddress.getReceiver() == null ? "" : vipaddress.getReceiver());
			map.put("phone", vipaddress.getPhone() == null ? "" : vipaddress.getPhone());
			String province = vipaddress.getProvince() == null ? "" : vipaddress.getProvince();
			String city = vipaddress.getCity() == null ? "" : vipaddress.getCity();
			String county = vipaddress.getCounty() == null ? "" : vipaddress.getCounty();
			String address = vipaddress.getAddress() == null ? "" : vipaddress.getAddress();
			map.put("address", province+city+county+address);
			map.put("addressid", vipaddress.getId());
			map.put("isdefault", vipaddress.getIsdefault());
			result.setData(map);
			result.setId(vipaddress.getId());
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用VipServiceImpl.queryVipDefaultAddress报错：", e);
		}

		return result;
	}

}
