package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MMiniprogramExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IMiniProgramMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 小程序
 * @author J
 *
 */
@Service
public class MiniProgramMaintServiceImpl implements IMiniProgramMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMiniprogramMapper miniprogramMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeMiniProgram(MMiniprogram miniprogram, MUser user) {
		Result result = new Result();

		try {
			MMiniprogramExample example = new MMiniprogramExample();
			if (miniprogram.getId() != null) {
				example.createCriteria().andCodeEqualTo(miniprogram.getCode().trim()).andIdNotEqualTo(miniprogram.getId());
			} else {
				example.createCriteria().andCodeEqualTo(miniprogram.getCode().trim());
			}
			List<MMiniprogram> list = miniprogramMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MMiniprogram mMiniprogram = new MMiniprogram();
			miniprogram.setUpdatedtime(now);
			if (miniprogram.getId() != null) {
				mMiniprogram = miniprogramMapper.selectByPrimaryKey(miniprogram.getId());
				BeanUtil.copyPropertiesIgnoreNull(miniprogram, mMiniprogram);
				mMiniprogram.setModifier(user.getName());
				mMiniprogram.setModifytime(now);
				int changeNum = miniprogramMapper.updateByPrimaryKeySelective(mMiniprogram);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mMiniprogram.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(miniprogram, mMiniprogram);
				mMiniprogram.setCreator(user.getName());
				mMiniprogram.setCreatetime(now);
				int key = miniprogramMapper.insertSelective(mMiniprogram);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mMiniprogram.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("miniprogram", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramMaintServiceImpl.changeMiniProgram报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteMiniProgram(Integer id) {
		Result result = new Result();

		try {
			int i = miniprogramMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramMaintServiceImpl.deleteMiniProgram报错：", e);
		}

		return result;
	}

}
