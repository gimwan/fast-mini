package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MRoleMapper;
import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MRoleExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IRoleMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 角色管理
 * @author J
 *
 */
@Service
public class RoleMaintServiceImpl implements IRoleMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MRoleMapper roleMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeRole(MRole role, MUser user) {
		Result result = new Result();

		try {
			MRoleExample example = new MRoleExample();
			if (role.getId() != null) {
				example.createCriteria().andCodeEqualTo(role.getCode().trim()).andIdNotEqualTo(role.getId());
			} else {
				example.createCriteria().andCodeEqualTo(role.getCode().trim());
			}
			List<MRole> list = roleMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MRole mRole = new MRole();
			role.setUpdatedtime(now);
			if (role.getId() != null) {
				mRole = roleMapper.selectByPrimaryKey(role.getId());
				BeanUtil.copyPropertiesIgnoreNull(role, mRole);
				mRole.setModifier(user.getName());
				mRole.setModifytime(now);
				int changeNum = roleMapper.updateByPrimaryKeySelective(mRole);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mRole.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(role, mRole);
				mRole.setCreator(user.getName());
				mRole.setCreatetime(now);
				int key = roleMapper.insertSelective(mRole);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mRole.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("role", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用RoleMaintServiceImpl.changeRole报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteRole(Integer id) {
		Result result = new Result();

		try {
			int i = roleMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用RoleMaintServiceImpl.deleteRole报错：", e);
		}

		return result;
	}

}
