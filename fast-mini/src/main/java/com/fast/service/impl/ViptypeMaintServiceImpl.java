package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MViptype;
import com.fast.service.IViptypeMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

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

	@Override
	public Result changeVipType(MViptype viptype, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MViptype mViptype = new MViptype();
			viptype.setUpdatedtime(now);
			if (viptype.getId() != null) {
				mViptype = viptypeMapper.selectByPrimaryKey(viptype.getId());
				BeanUtil.copyPropertiesIgnoreNull(viptype, mViptype);
				mViptype.setModifier(user.getName());
				mViptype.setModifytime(now);
				int changeNum = viptypeMapper.updateByPrimaryKeySelective(mViptype);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mViptype.getId());
					result.setData(mViptype);
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(viptype, mViptype);
				mViptype.setCreator(user.getName());
				mViptype.setCreatetime(now);
				int key = viptypeMapper.insertSelective(mViptype);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mViptype.getId());
					result.setData(mViptype);
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ViptypeMaintServiceImpl.changeVipType报错：", e);
		}

		return result;
	}

}
