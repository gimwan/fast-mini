package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MUserMapper;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IUserMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

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
	
	@Autowired
	IDataService iDataService;

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
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("user", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用UserMaintServiceImpl.changeUser报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteUser(Integer id, MUser user) {
		Result result = new Result();

		try {
			if (user.getId().intValue() == id.intValue()) {
				result.setMessage("当前登录用户无法删除");
				return result;
			}
			int i = userMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用UserMaintServiceImpl.deleteUser报错：", e);
		}

		return result;
	}
}
