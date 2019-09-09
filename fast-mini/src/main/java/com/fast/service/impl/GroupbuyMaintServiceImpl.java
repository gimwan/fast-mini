package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.dao.MGroupbuyMapper;
import com.fast.base.data.dao.MGroupbuydtlMapper;
import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import com.fast.base.data.entity.MGroupbuy;
import com.fast.base.data.entity.MGroupbuyExample;
import com.fast.base.data.entity.MGroupbuydtl;
import com.fast.base.data.entity.MGroupbuydtlExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IGroupbuyMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

/**
 * 拼团活动
 * @author J
 *
 */
@Service
public class GroupbuyMaintServiceImpl implements IGroupbuyMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGroupbuyMapper groupbuyMapper;
	
	@Autowired
	MGroupbuydtlMapper groupbuydtlMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeGroupbuy(MGroupbuy groupbuy, MUser user) {
		Result result = new Result();

		try {
			MGroupbuyExample example = new MGroupbuyExample();
			if (groupbuy.getId() != null) {
				example.createCriteria().andCodeEqualTo(groupbuy.getCode().trim()).andIdNotEqualTo(groupbuy.getId());
			} else {
				example.createCriteria().andCodeEqualTo(groupbuy.getCode().trim());
			}
			List<MGroupbuy> list = groupbuyMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			// 开始时间小于当前活动开始时间、结束时间大于当前活动开始时间
			example = new MGroupbuyExample();
			if (groupbuy.getId() != null) {				
				example.createCriteria().andBegintimeLessThanOrEqualTo(groupbuy.getBegintime())
						.andEndtimeGreaterThan(groupbuy.getBegintime()).andIdNotEqualTo(groupbuy.getId());
			} else {
				example.createCriteria().andBegintimeLessThanOrEqualTo(groupbuy.getBegintime())
						.andEndtimeGreaterThan(groupbuy.getBegintime());
			}
			list = groupbuyMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("所选时间范围内已有活动");
				return result;
			}
			// 开始时间小于当前活动结束时间、结束时间大于当前活动结束时间
			example = new MGroupbuyExample();
			if (groupbuy.getId() != null) {				
				example.createCriteria().andBegintimeLessThanOrEqualTo(groupbuy.getEndtime())
						.andEndtimeGreaterThan(groupbuy.getEndtime()).andIdNotEqualTo(groupbuy.getId());
			} else {
				example.createCriteria().andBegintimeLessThanOrEqualTo(groupbuy.getEndtime())
						.andEndtimeGreaterThan(groupbuy.getEndtime());
			}
			list = groupbuyMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("所选时间范围内已有活动");
				return result;
			}
			
			Date now = new Date();
			MGroupbuy mGroupbuy = new MGroupbuy();
			groupbuy.setUpdatedtime(now);
			if (groupbuy.getId() != null) {
				mGroupbuy = groupbuyMapper.selectByPrimaryKey(groupbuy.getId());
				BeanUtil.copyPropertiesIgnoreNull(groupbuy, mGroupbuy);
				mGroupbuy.setModifier(user.getName());
				mGroupbuy.setModifytime(now);
				int changeNum = groupbuyMapper.updateByPrimaryKeySelective(mGroupbuy);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mGroupbuy.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(groupbuy, mGroupbuy);
				mGroupbuy.setCreator(user.getName());
				mGroupbuy.setCreatetime(now);
				int key = groupbuyMapper.insertSelective(mGroupbuy);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mGroupbuy.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("groupbuy", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyMaintServiceImpl.changeGroupbuy报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteGroupbuy(Integer id) {
		Result result = new Result();

		try {
			result = delete(id);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyMaintServiceImpl.deleteGroupbuy报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Result delete(Integer id) {
		Result result = new Result();
		int i = groupbuyMapper.deleteByPrimaryKey(id);
		if (i > 0) {
			MGroupbuydtlExample example = new MGroupbuydtlExample();
			example.createCriteria().andGroupbuyidEqualTo(id);
			List<MGroupbuydtl> list = groupbuydtlMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				for (int j = 0; j < list.size(); j++) {
					groupbuydtlMapper.deleteByPrimaryKey(list.get(j).getId());
				}
			}
			result.setErrcode(0);
			result.setMessage("删除成功");
		} else {
			result.setMessage("删除失败");
		}		
		return result;
	}

	@Override
	public Result saveGroupbuydtl(MUser user, Integer groupbuyid, String goodsdatas) {
		Result result = new Result();

		try {			
			goodsdatas = CommonUtil.unescape(goodsdatas);
			List<MGroupbuydtl> groupbuydtls = JSONObject.parseArray(goodsdatas,  MGroupbuydtl.class);
			
			saveDetail(groupbuyid, user.getName(), groupbuydtls);
			
			result.setErrcode(Integer.valueOf(0));
			result.setId(groupbuyid);
			result.setMessage("保存成功");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.saveGroupbuydtl报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveDetail(Integer groupbuyid, String username, List<MGroupbuydtl> groupbuydtls) {
		Date now = new Date();
		List<Integer> deleteList = new ArrayList<>();
		for (int i = 0; i < groupbuydtls.size(); i++) {
			MGroupbuydtl groupbuydtl = groupbuydtls.get(i);
			groupbuydtl.setUpdatedtime(now);
			if (groupbuydtl.getId() != null && groupbuydtl.getId() > 0) {
				groupbuydtl.setModifier(username);
				groupbuydtl.setModifytime(now);
				groupbuydtlMapper.updateByPrimaryKeySelective(groupbuydtl);
			} else {
				groupbuydtl.setCreator(username);
				groupbuydtl.setCreatetime(now);
				groupbuydtlMapper.insertSelective(groupbuydtl);
			}
			deleteList.add(groupbuydtl.getId());
		}
		MGroupbuydtlExample example = new MGroupbuydtlExample();
		example.createCriteria().andGroupbuyidEqualTo(groupbuyid);
		if (deleteList.size() > 0) {
			example = new MGroupbuydtlExample();
			example.createCriteria().andGroupbuyidEqualTo(groupbuyid).andIdNotIn(deleteList);
		}
		groupbuydtlMapper.deleteByExample(example);
	}
	
}
