package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MPublicplatformExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IPublicPlatformMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 公众号
 * @author J
 *
 */
@Service
public class PublicPlatformMaintServiceImpl implements IPublicPlatformMaintService, Serializable {
	
private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MPublicplatformMapper publicplatformMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changePublicplatform(MPublicplatform publicplatform, MUser user) {
		Result result = new Result();

		try {
			MPublicplatformExample example = new MPublicplatformExample();
			if (publicplatform.getId() != null) {
				example.createCriteria().andCodeEqualTo(publicplatform.getCode().trim()).andIdNotEqualTo(publicplatform.getId());
			} else {
				example.createCriteria().andCodeEqualTo(publicplatform.getCode().trim());
			}
			List<MPublicplatform> list = publicplatformMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MPublicplatform mPublicplatform = new MPublicplatform();
			publicplatform.setUpdatedtime(now);
			if (publicplatform.getId() != null) {
				mPublicplatform = publicplatformMapper.selectByPrimaryKey(publicplatform.getId());
				BeanUtil.copyPropertiesIgnoreNull(publicplatform, mPublicplatform);
				mPublicplatform.setModifier(user.getName());
				mPublicplatform.setModifytime(now);
				int changeNum = publicplatformMapper.updateByPrimaryKeySelective(mPublicplatform);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mPublicplatform.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(publicplatform, mPublicplatform);
				mPublicplatform.setCreator(user.getName());
				mPublicplatform.setCreatetime(now);
				int key = publicplatformMapper.insertSelective(mPublicplatform);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mPublicplatform.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("publicplatform", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用PublicPlatformMaintServiceImpl.changePublicplatform报错：", e);
		}

		return result;
	}

	@Override
	public Result deletePublicplatform(Integer id) {
		Result result = new Result();

		try {
			int i = publicplatformMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用PublicPlatformMaintServiceImpl.deletePublicplatform报错：", e);
		}

		return result;
	}

}
