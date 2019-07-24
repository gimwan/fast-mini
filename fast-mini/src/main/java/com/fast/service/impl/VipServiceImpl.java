package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipcouponExample;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.base.data.entity.MViptype;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

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
				MVip vip = (MVip) result.getData();
				if (vip != null && vip.getId() != null) {
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
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("0")).andVipidEqualTo(r.getId()).andBegintimeLessThanOrEqualTo(now).andEndtimeGreaterThan(now);
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
				MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(r.getId());
				map.put("point", vipaccount.getPoint() == null ? 0 : vipaccount.getPoint());
				map.put("deposit", vipaccount.getDeposit() == null ? BigDecimal.ZERO : vipaccount.getDeposit());
				
				MVipcouponExample example = new MVipcouponExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("0")).andVipidEqualTo(r.getId()).andBegintimeLessThanOrEqualTo(now).andEndtimeGreaterThan(now);
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

}
