package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MMicropageMapper;
import com.fast.base.data.dao.MMicropagesetMapper;
import com.fast.base.data.dao.MMicropagesetdraftMapper;
import com.fast.base.data.dao.MMicropagesetdtlMapper;
import com.fast.base.data.dao.MMicropagesetdtldraftMapper;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MMicropageExample;
import com.fast.base.data.entity.MMicropageset;
import com.fast.base.data.entity.MMicropagesetExample;
import com.fast.base.data.entity.MMicropagesetdraft;
import com.fast.base.data.entity.MMicropagesetdraftExample;
import com.fast.base.data.entity.MMicropagesetdtlExample;
import com.fast.base.data.entity.MMicropagesetdtldraft;
import com.fast.base.data.entity.MMicropagesetdtldraftExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IMicropageMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 微页面
 * @author J
 *
 */
@Service
public class MicropageMaintServiceImpl implements IMicropageMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMicropageMapper micropageMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	MMicropagesetdraftMapper micropagesetdraftMapper;
	
	@Autowired
	MMicropagesetdtldraftMapper micropagesetdtldraftMapper;
	
	@Autowired
	MMicropagesetMapper micropagesetMapper;
	
	@Autowired
	MMicropagesetdtlMapper micropagesetdtlMapper;

	@Override
	public Result changeMicropage(MMicropage micropage, MUser user) {
		Result result = new Result();

		try {
			MMicropageExample example = new MMicropageExample();
			if (micropage.getId() != null) {
				example.createCriteria().andCodeEqualTo(micropage.getCode().trim()).andIdNotEqualTo(micropage.getId());
			} else {
				example.createCriteria().andCodeEqualTo(micropage.getCode().trim());
			}
			List<MMicropage> list = micropageMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			result = save(micropage, user);
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("micropage", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageMaintServiceImpl.changeMicropage报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Result save(MMicropage micropage, MUser user) {
		Result result = new Result();
		Date now = new Date();
		MMicropage mMicropage = new MMicropage();
		micropage.setUpdatedtime(now);
		if (micropage.getId() != null) {
			mMicropage = micropageMapper.selectByPrimaryKey(micropage.getId());
			BeanUtil.copyPropertiesIgnoreNull(micropage, mMicropage);
			mMicropage.setModifier(user.getName());
			mMicropage.setModifytime(now);
			int changeNum = micropageMapper.updateByPrimaryKeySelective(mMicropage);
			if (changeNum > 0) {
				result.setErrcode(0);
				result.setId(mMicropage.getId());
				result.setMessage("保存成功");
			} else {
				result.setMessage("保存失败");
			}
		} else {
			BeanUtil.copyPropertiesIgnoreNull(micropage, mMicropage);
			mMicropage.setCreator(user.getName());
			mMicropage.setCreatetime(now);
			int key = micropageMapper.insertSelective(mMicropage);
			if (key > 0) {
				result.setErrcode(0);
				result.setId(mMicropage.getId());
				result.setMessage("新增成功");
			} else {
				result.setMessage("新增失败");
			}
		}
		
		if (Common.isActive(result)) {
			if (mMicropage.getHomeflag().intValue() == 1) {
				MMicropageExample example = new MMicropageExample();
				example.createCriteria().andPublicplatformidEqualTo(mMicropage.getPublicplatformid()).andHomeflagEqualTo(Byte.valueOf("1")).andIdNotEqualTo(mMicropage.getId());
				List<MMicropage> list = micropageMapper.selectByExample(example);
				for (int i = 0; i < list.size(); i++) {
					MMicropage micro = list.get(i);
					micro.setHomeflag(Byte.valueOf("0"));
					micro.setUpdatedtime(now);
					micropageMapper.updateByPrimaryKeySelective(micro);
				}
			}
		}
		
		return result;
	}

	@Override
	public Result deleteMicropage(Integer id) {
		Result result = new Result();

		try {
			int i = micropageMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageMaintServiceImpl.deleteMicropage报错：", e);
		}

		return result;
	}

	@Override
	public Result saveMicropageSetData(Integer pageID, String setdataStr, MUser user) {
		Result result = new Result();

		try {
			savedata(pageID, setdataStr, user);
			
			result.setErrcode(Integer.valueOf(0));
			result.setMessage("保存成功");
			Result r = iDataService.one("micropage", pageID);
			if (Common.isActive(r)) {
				result.setData(r.getData());
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageMaintServiceImpl.saveMicropageSetData报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void savedata(Integer pageID, String setdataStr, MUser user) {
		Date now = new Date();
		String setdata = Common.unescape(setdataStr);
		JSONArray jsonArray = JSONArray.parseArray(setdata);
		List<Integer> setidList = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			MMicropagesetdraft micropagesetdraft = JSONObject.parseObject(jsonArray.getJSONObject(i).toString(), MMicropagesetdraft.class);
			micropagesetdraft.setUpdatedtime(now);
			if (micropagesetdraft.getId() != null && micropagesetdraft.getId().intValue() > 0) {
				micropagesetdraft.setModifier(user.getName());
				micropagesetdraft.setModifytime(now);
				micropagesetdraftMapper.updateByPrimaryKeySelective(micropagesetdraft);
			} else {
				micropagesetdraft.setId(null);
				micropagesetdraft.setCreator(user.getName());
				micropagesetdraft.setCreatetime(now);
				micropagesetdraftMapper.insertSelective(micropagesetdraft);
			}
			setidList.add(micropagesetdraft.getId());
			String details = jsonArray.getJSONObject(i).getString("detail");
			if (!Common.isEmpty(details)) {
				List<MMicropagesetdtldraft> micropagesetdtldrafts = JSONObject.parseArray(details, MMicropagesetdtldraft.class);
				List<Integer> setdtlidList = new ArrayList<>();
				for (int j = 0; j < micropagesetdtldrafts.size(); j++) {
					MMicropagesetdtldraft micropagesetdtldraft = micropagesetdtldrafts.get(j);
					micropagesetdtldraft.setMicropagesetid(micropagesetdraft.getId());
					if (micropagesetdtldraft.getId() != null && micropagesetdtldraft.getId().intValue() > 0) {
						micropagesetdtldraftMapper.updateByPrimaryKeySelective(micropagesetdtldraft);
					} else {
						micropagesetdtldraft.setId(null);
						micropagesetdtldraftMapper.insertSelective(micropagesetdtldraft);
					}
					setdtlidList.add(micropagesetdtldraft.getId());
				}
				// 删除未设置项
				MMicropagesetdtldraftExample micropagesetdtldraftExample = new MMicropagesetdtldraftExample();
				if (setdtlidList.size() > 0) {
					micropagesetdtldraftExample.createCriteria().andIdNotIn(setdtlidList).andMicropagesetidEqualTo(micropagesetdraft.getId());
				} else {
					micropagesetdtldraftExample.createCriteria().andMicropagesetidEqualTo(micropagesetdraft.getId());
				}
				micropagesetdtldraftMapper.deleteByExample(micropagesetdtldraftExample);
			}
		}
		// 删除未设置项
		MMicropagesetdraftExample micropagesetdraftExample = new MMicropagesetdraftExample();
		if (setidList.size() > 0) {
			micropagesetdraftExample.createCriteria().andMicropageidEqualTo(pageID).andIdNotIn(setidList);
		} else {
			micropagesetdraftExample.createCriteria().andMicropageidEqualTo(pageID);
		}
		micropagesetdraftMapper.deleteByExample(micropagesetdraftExample);
		
		MMicropage micropage = new MMicropage();
		micropage.setId(pageID);
		micropage.setModifier(user.getName());
		micropage.setModifytime(now);
		micropage.setUpdatedtime(now);
		micropageMapper.updateByPrimaryKeySelective(micropage);
	}

	@Override
	public Result releaseMicroPage(Integer pageID, MUser user) {
		Result result = new Result();

		try {
			release(pageID, user);
			
			result.setErrcode(Integer.valueOf(0));
			result.setMessage("发布成功");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageMaintServiceImpl.releaseMicroPage报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void release(Integer pageID, MUser user) {
		Date now = new Date();
		MMicropage micropage = micropageMapper.selectByPrimaryKey(pageID);
		micropage.setPublisher(user.getName());
		micropage.setPublishtime(now);
		micropage.setPublishflag(Byte.valueOf("1"));
		micropageMapper.updateByPrimaryKeySelective(micropage);
		
		MMicropagesetExample micropagesetExample = new MMicropagesetExample();
		micropagesetExample.createCriteria().andMicropageidEqualTo(micropage.getId());
		List<MMicropageset> micropagesets = micropagesetMapper.selectByExample(micropagesetExample);
		for (int i = 0; i < micropagesets.size(); i++) {
			MMicropageset micropageset = micropagesets.get(i);
			MMicropagesetdtlExample micropagesetdtlExample = new MMicropagesetdtlExample();
			micropagesetdtlExample.createCriteria().andMicropagesetidEqualTo(micropageset.getId());
			micropagesetdtlMapper.deleteByExample(micropagesetdtlExample);
			micropagesetMapper.deleteByPrimaryKey(micropageset.getId());
		}
		
		String sql = "insert into m_micropageset select * from m_micropagesetdraft where micropageid=" + micropage.getId();
		dataMapper.pageList(sql);
		sql = "insert into m_micropagesetdtl select * from m_micropagesetdtldraft where micropagesetid in (select id from m_micropageset where micropageid="+micropage.getId()+")";
		dataMapper.pageList(sql);
	}

}
