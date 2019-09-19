package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.dao.MPatternMapper;
import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MPatternExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IPatternMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 版型
 * @author J
 *
 */
@Service
public class PatternMaintServiceImpl implements IPatternMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MPatternMapper patternMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MGoodsskuMapper goodsskuMapper;

	@Override
	public Result changePattern(MPattern pattern, MUser user) {
		Result result = new Result();

		try {
			MPatternExample example = new MPatternExample();
			if (pattern.getId() != null) {
				example.createCriteria().andCodeEqualTo(pattern.getCode().trim()).andIdNotEqualTo(pattern.getId());
			} else {
				example.createCriteria().andCodeEqualTo(pattern.getCode().trim());
			}
			List<MPattern> list = patternMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MPattern mPattern = new MPattern();
			pattern.setUpdatedtime(now);
			if (pattern.getId() != null) {
				mPattern = patternMapper.selectByPrimaryKey(pattern.getId());
				BeanUtil.copyPropertiesIgnoreNull(pattern, mPattern);
				mPattern.setModifier(user.getName());
				mPattern.setModifytime(now);
				int changeNum = patternMapper.updateByPrimaryKeySelective(mPattern);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mPattern.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(pattern, mPattern);
				mPattern.setCreator(user.getName());
				mPattern.setCreatetime(now);
				int key = patternMapper.insertSelective(mPattern);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mPattern.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("pattern", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用PatternMaintServiceImpl.changePattern报错：", e);
		}

		return result;
	}

	@Override
	public Result deletePattern(Integer id) {
		Result result = new Result();

		try {
			MGoodsskuExample example = new MGoodsskuExample();
			example.createCriteria().andPatternidEqualTo(id);
			List<MGoodssku> list = goodsskuMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("删除失败！此版型已被使用");
				return result;
			}
			int i= patternMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用PatternMaintServiceImpl.deletePattern报错：", e);
		}

		return result;
	}

}
