package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast.base.Result;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import com.fast.service.IDataService;
import com.fast.service.IViptypeMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 会员等级
 * @author J
 *
 */
@Service
public class ViptypeMaintServiceImpl implements IViptypeMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeVipType(MViptype viptype, MUser user) {
		Result result = new Result();

		try {
			MViptypeExample example = new MViptypeExample();
			if (viptype.getId() != null) {
				example.createCriteria().andCodeEqualTo(viptype.getCode().trim()).andIdNotEqualTo(viptype.getId());
			} else {
				example.createCriteria().andCodeEqualTo(viptype.getCode().trim());
			}
			List<MViptype> list = viptypeMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			result = saveType(viptype, user.getName());
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("viptype", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ViptypeMaintServiceImpl.changeVipType报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Result saveType(MViptype viptype, String username) {
		Result result = new Result();
		Date now = new Date();
		MViptype mViptype = new MViptype();
		viptype.setUpdatedtime(now);
		if (viptype.getId() != null) {
			mViptype = viptypeMapper.selectByPrimaryKey(viptype.getId());
			BeanUtil.copyPropertiesIgnoreNull(viptype, mViptype);
			mViptype.setModifier(username);
			mViptype.setModifytime(now);
			int changeNum = viptypeMapper.updateByPrimaryKeySelective(mViptype);
			if (changeNum > 0) {
				result.setErrcode(0);
				result.setId(mViptype.getId());
				result.setMessage("保存成功");
			} else {
				result.setMessage("保存失败");
			}
		} else {
			BeanUtil.copyPropertiesIgnoreNull(viptype, mViptype);
			mViptype.setCreator(username);
			mViptype.setCreatetime(now);
			int key = viptypeMapper.insertSelective(mViptype);
			if (key > 0) {
				result.setErrcode(0);
				result.setId(mViptype.getId());
				result.setMessage("新增成功");
			} else {
				result.setMessage("新增失败");
			}
		}
		// 默认等级只能有一个
		if (Common.isActive(result)) {
			if (mViptype.getDefaultflag().intValue() == 1) {
				MViptypeExample example = new MViptypeExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andDefaultflagEqualTo(Byte.valueOf("1")).andIdNotEqualTo(mViptype.getId());
				List<MViptype> list = viptypeMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						MViptype record = list.get(i);
						record.setDefaultflag(Byte.valueOf("0"));
						record.setUpdatedtime(now);
						viptypeMapper.updateByPrimaryKeySelective(record);
					}
				}
				
			}
		}
		
		return result;
	}

	@Override
	public Result deleteVipType(Integer id) {
		Result result = new Result();

		try {
			int i = viptypeMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ViptypeMaintServiceImpl.deleteVipType报错：", e);
		}

		return result;
	}

}
