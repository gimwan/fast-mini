package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MUserMapper;
import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MUser;
import com.fast.service.IUserMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 用户管理
 * @author J
 *
 */
@Service
public class UserMaintServiceImpl implements IUserMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MUserMapper userMapper;

	@Override
	public Result changeUser(MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MUser mUser = new MUser();
			user.setUpdatedtime(now);
			if (user.getId() != null) {
				mUser = userMapper.selectByPrimaryKey(user.getId());
				BeanUtil.copyPropertiesIgnoreNull(user, mUser);
				mUser.setModifier(user.getName());
				mUser.setModifytime(now);
				int changeNum = userMapper.updateByPrimaryKeySelective(mUser);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mUser.getId());
					result.setData(mUser);
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(user, mUser);
				mUser.setCreator(user.getName());
				mUser.setCreatetime(now);
				int key = userMapper.insertSelective(mUser);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mUser.getId());
					result.setData(mUser);
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用UserMaintServiceImpl.changeUser报错：", e);
		}

		return result;
	}
}
