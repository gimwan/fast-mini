package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MRoleMapper;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MUser;
import com.fast.service.IRoleMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 角色管理
 * @author J
 *
 */
@Service
public class RoleMaintServiceImpl implements IRoleMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MRoleMapper mRoleMapper;

	@Override
	public Result changeRole(MRole role, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MRole mRole = new MRole();
			role.setUpdatedtime(now);
			if (role.getId() != null) {
				mRole = mRoleMapper.selectByPrimaryKey(role.getId());
				BeanUtil.copyPropertiesIgnoreNull(role, mRole);
				mRole.setModifier(user.getName());
				mRole.setModifytime(now);
				int changeNum = mRoleMapper.updateByPrimaryKeySelective(mRole);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mRole.getId());
					result.setData(mRole);
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(role, mRole);
				mRole.setCreator(user.getName());
				mRole.setCreatetime(now);
				int key = mRoleMapper.insertSelective(mRole);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mRole.getId());
					result.setData(mRole);
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用RoleMaintServiceImpl.changeConfig报错：", e);
		}

		return result;
	}

}
