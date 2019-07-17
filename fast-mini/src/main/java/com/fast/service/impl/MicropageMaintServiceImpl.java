package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMicropageMapper;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IMicropageMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 微页面
 * @author J
 *
 */
@Service
public class MicropageMaintServiceImpl implements IMicropageMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMicropageMapper micropageMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeMicropage(MMicropage micropage, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MMicropage mMicropage = new MMicropage();
			micropage.setUpdatedtime(now);
			if (micropage.getId() != null) {
				mMicropage = micropageMapper.selectByPrimaryKey(micropage.getId());
				BeanUtil.copyPropertiesIgnoreNull(micropage, mMicropage);
				mMicropage.setModifier(user.getName());
				mMicropage.setModifytime(now);
				int changeNum = micropageMapper.updateByPrimaryKeySelective(mMicropage);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mMicropage.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(micropage, mMicropage);
				mMicropage.setCreator(user.getName());
				mMicropage.setCreatetime(now);
				int key = micropageMapper.insertSelective(mMicropage);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mMicropage.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("micropage", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageMaintServiceImpl.changeMicropage报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteMicropage(Integer id) {
		Result result = new Result();

		try {
			int i = micropageMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageMaintServiceImpl.deleteMicropage报错：", e);
		}

		return result;
	}

}
