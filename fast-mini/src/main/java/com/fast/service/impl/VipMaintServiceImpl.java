package com.fast.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MRegionMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import com.fast.service.IConfigService;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipMaintService;
import com.fast.service.IVipService;
import com.fast.service.ext.IExtMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 会员
 * @author J
 *
 */
@Service
public class VipMaintServiceImpl implements IVipMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	MVipminiMapper vipminiMapper;
	
	@Autowired
	MRegionMapper regionMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	IVipService iVipService;
	
	@Autowired
	IConfigService iConfigService;
	
	@Autowired
	IExtMaintService iExtMaintService;
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	@Autowired
	DataMapper dataMapper;

	@Override
	public Result bind(String appid, String openid, MVip vip) {
		Result result = new Result();

		try {
			Date now = new Date();
			vip = resetVip(vip, now);
			// 默认会员类别
			if (vip.getTypeid() == null) {
				MViptypeExample example = new MViptypeExample();
				example.createCriteria().andDefaultflagEqualTo(Byte.valueOf("1"));
				List<MViptype> list = viptypeMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					vip.setTypeid(list.get(0).getId());
				} else {
					result.setMessage("无默认会员类别");
					return result;
				}
			}
			// 默认门店
			if (Common.isEmpty(String.valueOf(vip.getDepartmentid()))) {
				String departmentid = "";
				Result r = iConfigService.queryConfigValueByCode("6001");
				if (Common.isActive(r)) {
					departmentid = (String) r.getData();
				}
				if (Common.isEmpty(departmentid)) {
					result.setMessage("缺少默认门店");
					return result;
				} else {
					vip.setDepartmentid(Integer.valueOf(departmentid.trim()));
				}
			}
			vip.setUseflag(Byte.valueOf("1"));
			vip = iVipService.resetVipRegion(vip);			
			
			vip = saveVip(appid, openid, vip);
			
			// 取最新会员信息
			vip = vipMapper.selectByPrimaryKey(vip.getId());
			
			result.setErrcode(Integer.valueOf(0));
			result.setData(vip);
			result.setId(vip.getId());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipMaintServiceImpl.bind报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public MVip saveVip(String appid, String openid, MVip vip) {
		if (vip.getId() != null) {
			vipMapper.updateByPrimaryKeySelective(vip);
		} else {
			String code = newCode(1);
			vip.setCode(code);
			vipMapper.insertSelective(vip);
		}
		
		MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(vip.getId());
		if (vipaccount == null || vipaccount.getId() == null) {
			vipaccount = new MVipaccount();
			vipaccount.setId(vip.getId());
			vipaccount.setCreatetime(vip.getUpdatedtime());
			vipaccount.setCreator("system");
			vipaccount.setUpdatedtime(vip.getUpdatedtime());
			vipaccountMapper.insertSelective(vipaccount);
		}
		resetVipMini(vip, appid, openid);
		// 同步会员信息
		syncVip(vip.getId());
		return vip;
	}
	
	/**
	 * 重构vip数据
	 * @param vip
	 * @param now
	 * @return
	 */
	public MVip resetVip(MVip vip, Date now) {
		MVipExample example = new MVipExample();
		example.createCriteria().andMobilephoneEqualTo(vip.getMobilephone());
		List<MVip> list = vipMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			MVip mVip = list.get(0);
			mVip.setNickname(vip.getNickname());
			if (Common.isEmpty(mVip.getName())) {
				mVip.setName(vip.getName());
			}
			mVip.setPhotourl(vip.getPhotourl());
			mVip.setUnionid(vip.getUnionid());
			if (Common.isEmpty(mVip.getProvince())) {
				mVip.setProvince(vip.getProvince());
			}
			if (Common.isEmpty(mVip.getCity())) {
				mVip.setCity(vip.getCity());
			}
			if (Common.isEmpty(String.valueOf(mVip.getRecommenderid()))) {
				mVip.setRecommenderid(vip.getRecommenderid());
			}
			if (Common.isEmpty(String.valueOf(mVip.getDepartmentid()))) {
				mVip.setDepartmentid(vip.getDepartmentid());
			}
			vip = mVip;
			vip.setModifytime(now);
			vip.setModifier("system");
			vip.setUpdatedtime(now);
		} else {
			vip.setRegisttime(now);
			vip.setCreatetime(now);
			vip.setCreator("system");
			vip.setUpdatedtime(now);
		}
		return vip;
	}
	
	/**
	 * 会员卡号生成
	 * @param type 1随机 2顺序
	 * @return
	 */
	public String newCode(int type) {
		StringBuffer codeBuf = new StringBuffer();
		int len = 12;
		if (type == 1) {
			Random rand = new Random();
			int bounds[];
			if (len >= 10) {
				bounds = new int[] { 1, 1 };
			} else {
				bounds = new int[] { 1 };
			}
			int templen = 0;
			for (int blen = 0; blen < bounds.length; blen++) {
				int temp = len / bounds.length;

				if (blen == bounds.length - 1) {
					temp = len - templen;
				} else
					templen = templen + temp;
				for (int i = 0; i < temp; i++) {
					bounds[blen] = bounds[blen] * 10;
				}
			}

			while (true) {
				String code = "00000000000000000000";
				for (int blen = 0; blen < bounds.length; blen++) {
					Integer r1 = rand.nextInt(bounds[blen]);
					code = code + String.valueOf(r1);
					code = code.substring(code.length() - len);
				}
				MVipExample example = new MVipExample();
				example.createCriteria().andCodeEqualTo(codeBuf.toString()+code);
				List<MVip> list = vipMapper.selectByExample(example);
				if (list == null || list.size() < 1) {
					codeBuf.append(code);
					break;
				}
			}
		} else {
			MVipExample example = new MVipExample();
			example.setOrderByClause(" code desc");
			List<MVip> list = vipMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				codeBuf.append(this.incCode(list.get(0).getCode(), "", len));
			} else {
				codeBuf.append(this.snToStr(1, len));
			}
		}
		
		return codeBuf.toString();
	}
	
	private String snToStr(Integer sn, int snLen) {
		String result = "0000000000" + String.valueOf(sn);
		return result.substring(result.length() - snLen);
	}

	private String incCode(String code, String pre, int len) {
		String sn = code.substring(pre.length());
		return pre + this.snToStr(Integer.valueOf(sn) + 1, len);
	}
	
	/**
	 * 记录openid、unionid
	 * @param vip
	 * @param appid
	 * @param openid
	 */
	public void resetVipMini(MVip vip, String appid, String openid) {
		try {
			Result result = iMiniProgramService.queryMiniprogramIDByAppid(appid);
			if (result != null && result.getErrcode() != null && result.getErrcode().intValue() == 0) {
				Integer miniprogramid = (Integer) result.getData();
				if (miniprogramid != null) {
					MVipminiExample example = new MVipminiExample();
					example.createCriteria().andMiniprogramidEqualTo(miniprogramid).andVipidEqualTo(vip.getId());
					List<MVipmini> list = vipminiMapper.selectByExample(example);
					if (list != null && list.size() > 0) {
						MVipmini mVipmini = list.get(0);
						if (Common.isEmpty(mVipmini.getUnionid())) {
							mVipmini.setUnionid(vip.getUnionid());
						}
						if (Common.isEmpty(openid)) {
							mVipmini.setOpenid(openid);
						}
						mVipmini.setUpdatedtime(new Date());
						vipminiMapper.updateByPrimaryKeySelective(mVipmini);
					} else {
						example = new MVipminiExample();
						example.createCriteria().andMiniprogramidEqualTo(miniprogramid).andOpenidEqualTo(openid);
						list = vipminiMapper.selectByExample(example);
						if (list != null && list.size() > 0) {
							MVipmini mVipmini = list.get(0);
							if (!Common.isEmpty(mVipmini.getUnionid())) {
								mVipmini.setUnionid(vip.getUnionid());
							}
							mVipmini.setVipid(vip.getId());
							mVipmini.setUpdatedtime(new Date());
							vipminiMapper.updateByPrimaryKeySelective(mVipmini);
						} else {
							boolean add = true;
							if (!Common.isEmpty(vip.getUnionid())) {
								example = new MVipminiExample();
								example.createCriteria().andMiniprogramidEqualTo(miniprogramid).andUnionidEqualTo(vip.getUnionid());
								list = vipminiMapper.selectByExample(example);
								if (list != null && list.size() > 0) {
									MVipmini mVipmini = list.get(0);
									if (!Common.isEmpty(openid)) {
										mVipmini.setOpenid(openid);
									}
									mVipmini.setVipid(vip.getId());
									mVipmini.setUpdatedtime(new Date());
									vipminiMapper.updateByPrimaryKeySelective(mVipmini);
									add = false;
								}
							}
							if (add) {
								MVipmini mVipmini = new MVipmini();
								mVipmini.setVipid(vip.getId());
								if (!Common.isEmpty(openid)) {
									mVipmini.setOpenid(openid);
								}
								if (Common.isEmpty(openid)) {
									mVipmini.setOpenid(openid);
								}
								mVipmini.setMiniprogramid(miniprogramid);
								mVipmini.setUpdatedtime(new Date());
								vipminiMapper.insertSelective(mVipmini);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			FastLog.error("调用VipMaintServiceImpl.resetVipMini报错：", e);
		}
	}

	@Override
	public Result updateVipInfo(String appid, String openid, String name, String nickname, String birthday,
			String province, String city, String county, String avatarurl, String gender) {
		Result result = new Result();

		try {
			Result r = iVipService.queryVipByOpenid(appid, openid);
			if (r != null && r.getErrcode() != null && r.getErrcode().intValue() == 0) {
				JSONObject object = JSONObject.fromObject(r.getData());
				MVip vip = (MVip) JSONObject.toBean(object, MVip.class);
				
				if (!Common.isEmpty(name)) {
					vip.setName(name);
				}
				if (!Common.isEmpty(nickname)) {
					vip.setNickname(nickname);
				}
				if (!Common.isEmpty(birthday)) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						vip.setBirthday(sdf.parse(birthday));
					} catch (Exception e) {
						FastLog.error("VipMaintServiceImpl.updateVipInfo日期转换出错：" + e);
					}
				}
				boolean resetRegin = false;
				if (!Common.isEmpty(province)) {
					vip.setProvince(province);
					resetRegin = true;
				}
				if (!Common.isEmpty(city)) {
					vip.setCity(city);
					resetRegin = true;
				}
				if (!Common.isEmpty(county)) {
					vip.setCounty(county);
					resetRegin = true;
				}
				if (!Common.isEmpty(avatarurl)) {
					vip.setPhotourl(avatarurl);
				}
				if (!Common.isEmpty(gender)) {
					vip.setSex(Byte.valueOf(gender));
				}
				
				if (resetRegin) {
					vip = iVipService.resetVipRegion(vip);
				}
				
				vipMapper.updateByPrimaryKeySelective(vip);
				
				syncVipData(vip.getId());
				
				result.setErrcode(Integer.valueOf(0));
				result.setId(vip.getId());
			}
		} catch (Exception e) {
			FastLog.error("调用VipMaintServiceImpl.updateVipInfo报错：", e);
		}
		
		return result;
	}
	
	public void syncVip(Integer vipid) {
		try {
			syncVipThread thread = new syncVipThread();
            thread.setVipid(vipid);
            Thread t = new Thread(thread);
            t.start();
		} catch (Exception e) {
			FastLog.error("调用VipMaintServiceImpl.syncVip报错：", e);
		}
	}
	public class syncVipThread implements Runnable {
		private Integer vipid;		
		public Integer getVipid() {
			return vipid;
		}
		public void setVipid(Integer vipid) {
			this.vipid = vipid;
		}

		@Override
		public void run() {
			iExtMaintService.syncVip(vipid);
		}
	}
	
	public void syncVipData(Integer vipid) {
		try {
			iExtMaintService.changeVipInfo(vipid);			
		} catch (Exception e) {
			FastLog.error("调用VipMaintServiceImpl.syncVipData报错：", e);
		}
	}

}
