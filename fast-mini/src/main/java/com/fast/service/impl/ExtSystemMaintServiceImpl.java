package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IExtSystemMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 应用接口
 * @author J
 *
 */
@Service
public class ExtSystemMaintServiceImpl implements IExtSystemMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeExtSystem(MExtsystem extsystem, MUser user) {
		Result result = new Result();

		try {
			MExtsystemExample example = new MExtsystemExample();
			if (extsystem.getId() != null) {
				example.createCriteria().andCodeEqualTo(extsystem.getCode().trim()).andIdNotEqualTo(extsystem.getId());
			} else {
				example.createCriteria().andCodeEqualTo(extsystem.getCode().trim());
			}
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MExtsystem mExtsystem = new MExtsystem();
			extsystem.setUpdatedtime(now);
			if (extsystem.getId() != null) {
				mExtsystem = extsystemMapper.selectByPrimaryKey(extsystem.getId());
				BeanUtil.copyPropertiesIgnoreNull(extsystem, mExtsystem);
				mExtsystem.setModifier(user.getName());
				mExtsystem.setModifytime(now);
				int changeNum = extsystemMapper.updateByPrimaryKeySelective(mExtsystem);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mExtsystem.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(extsystem, mExtsystem);
				mExtsystem.setCreator(user.getName());
				mExtsystem.setCreatetime(now);
				int key = extsystemMapper.insertSelective(mExtsystem);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mExtsystem.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("extsystem", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtSystemMaintServiceImpl.changeExtSystem报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteExtSystem(Integer id) {
		Result result = new Result();

		try {
			int i = extsystemMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtSystemMaintServiceImpl.deleteExtSystem报错：", e);
		}

		return result;
	}

}
