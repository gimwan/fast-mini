package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MUser;
import com.fast.service.IMiniProgramMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

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

	@Override
	public Result changeMiniProgram(MMiniprogram miniprogram, MUser user) {
		Result result = new Result();

		try {
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
					result.setData(mMiniprogram);
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
					result.setData(mMiniprogram);
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramMaintServiceImpl.changeMiniProgram报错：", e);
		}

		return result;
	}

}
