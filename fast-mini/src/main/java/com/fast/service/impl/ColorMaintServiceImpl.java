package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;
import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IColorMaintService;
import com.fast.service.IDataService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

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
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MGoodsskuMapper goodsskuMapper;

	@Override
	public Result changeColor(MColor color, MUser user) {
		Result result = new Result();

		try {
			MColorExample example = new MColorExample();
			if (color.getId() != null) {
				example.createCriteria().andCodeEqualTo(color.getCode().trim()).andIdNotEqualTo(color.getId());
			} else {
				example.createCriteria().andCodeEqualTo(color.getCode().trim());
			}
			List<MColor> list = colorMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
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
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("color", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
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
			MGoodsskuExample example = new MGoodsskuExample();
			example.createCriteria().andColoridEqualTo(id);
			List<MGoodssku> list = goodsskuMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("删除失败！此颜色已被使用");
				return result;
			}
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
