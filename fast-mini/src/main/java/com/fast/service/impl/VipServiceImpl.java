package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipService;
import com.fast.system.log.FastLog;
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
	MVipMapper mVipMapper;
	
	@Autowired
	MVipminiMapper mVipminiMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
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
			if (r != null && r.getErrcode() != null && r.getErrcode().intValue() == 0) {
				miniprogramid = (Integer) r.getData();
			}			
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andOpenidEqualTo(openid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> list = mVipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					MVip vip = mVipMapper.selectByPrimaryKey(vipid);
					if (vip != null && vip.getId() != null) {
						result.setData(vip);
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
			if (r != null && r.getErrcode() != null && r.getErrcode().intValue() == 0) {
				miniprogramid = (Integer) r.getData();
			}			
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andUnionidEqualTo(unionid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> list = mVipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					MVip vip = mVipMapper.selectByPrimaryKey(vipid);
					if (vip != null && vip.getId() != null) {
						result.setData(vip);
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
			if (r != null && r.getErrcode() != null && r.getErrcode().intValue() == 0) {
				miniprogramid = (Integer) r.getData();
			}
			// 使用openid查找会员			
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andOpenidEqualTo(openid).andMiniprogramidEqualTo(miniprogramid);
			List<MVipmini> list = mVipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					MVip vip = mVipMapper.selectByPrimaryKey(vipid);
					if (vip != null && vip.getId() != null) {
						result.setData(vip);
						result.setErrcode(Integer.valueOf(0));
						login = true;
					}
				}
			}			
			// openid查找不到会员，则使用unionid查找会员
			if (!login) {
				example = new MVipminiExample();
				example.createCriteria().andUnionidEqualTo(unionid).andMiniprogramidEqualTo(miniprogramid);
				list = mVipminiMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					Integer vipid = list.get(0).getVipid();
					if (vipid != null) {
						MVip vip = mVipMapper.selectByPrimaryKey(vipid);
						if (vip != null && vip.getId() != null) {
							result.setData(vip);
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

}
