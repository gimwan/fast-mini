package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MUser;
import com.fast.service.IColorMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 颜色
 * @author J
 *
 */
@Service
public class ColorMaintServiceImpl implements IColorMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MColorMapper colorMapper;

	@Override
	public Result changeColor(MColor color, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MColor mColor = new MColor();
			color.setUpdatedtime(now);
			if (color.getId() != null) {
				mColor = colorMapper.selectByPrimaryKey(color.getId());
				BeanUtil.copyPropertiesIgnoreNull(color, mColor);
				mColor.setModifier(user.getName());
				mColor.setModifytime(now);
				int changeNum = colorMapper.updateByPrimaryKeySelective(mColor);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mColor.getId());
					result.setData(mColor);
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(color, mColor);
				mColor.setCreator(user.getName());
				mColor.setCreatetime(now);
				int key = colorMapper.insertSelective(mColor);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mColor.getId());
					result.setData(mColor);
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用colorMaintServiceImpl.changecolor报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteColor(Integer id) {
		Result result = new Result();

		try {
			int i = colorMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用colorMaintServiceImpl.deleteColor报错：", e);
		}

		return result;
	}

}
