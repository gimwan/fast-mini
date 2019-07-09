package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MSizeMapper;
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.ISizeMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 尺码
 * @author J
 *
 */
@Service
public class SizeMaintServiceImpl implements ISizeMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MSizeMapper sizeMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeSize(MSize size, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MSize mSize = new MSize();
			size.setUpdatedtime(now);
			if (size.getId() != null) {
				mSize = sizeMapper.selectByPrimaryKey(size.getId());
				BeanUtil.copyPropertiesIgnoreNull(size, mSize);
				mSize.setModifier(user.getName());
				mSize.setModifytime(now);
				int changeNum = sizeMapper.updateByPrimaryKeySelective(mSize);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mSize.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(size, mSize);
				mSize.setCreator(user.getName());
				mSize.setCreatetime(now);
				int key = sizeMapper.insertSelective(mSize);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mSize.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("size", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用SizeMaintServiceImpl.changeSize报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteSize(Integer id) {
		Result result = new Result();

		try {
			int i = sizeMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用SizeMaintServiceImpl.deleteSize报错：", e);
		}

		return result;
	}

}
