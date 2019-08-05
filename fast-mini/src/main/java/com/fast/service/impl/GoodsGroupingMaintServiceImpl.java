package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MGoodsgroupingMapper;
import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MGoodsgroupingExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IGoodsGroupingMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 分组
 * @author J
 *
 */
@Service
public class GoodsGroupingMaintServiceImpl implements IGoodsGroupingMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGoodsgroupingMapper goodsgroupingMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeGoodsGrouping(MGoodsgrouping goodsgrouping, MUser user) {
		Result result = new Result();

		try {
			MGoodsgroupingExample example = new MGoodsgroupingExample();
			if (goodsgrouping.getId() != null) {
				example.createCriteria().andCodeEqualTo(goodsgrouping.getCode().trim()).andIdNotEqualTo(goodsgrouping.getId());
			} else {
				example.createCriteria().andCodeEqualTo(goodsgrouping.getCode().trim());
			}
			List<MGoodsgrouping> list = goodsgroupingMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MGoodsgrouping mGoodsgrouping = new MGoodsgrouping();
			goodsgrouping.setUpdatedtime(now);
			if (goodsgrouping.getId() != null) {
				mGoodsgrouping = goodsgroupingMapper.selectByPrimaryKey(goodsgrouping.getId());
				BeanUtil.copyPropertiesIgnoreNull(goodsgrouping, mGoodsgrouping);
				mGoodsgrouping.setModifier(user.getName());
				mGoodsgrouping.setModifytime(now);
				int changeNum = goodsgroupingMapper.updateByPrimaryKeySelective(mGoodsgrouping);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mGoodsgrouping.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(goodsgrouping, mGoodsgrouping);
				mGoodsgrouping.setCreator(user.getName());
				mGoodsgrouping.setCreatetime(now);
				int key = goodsgroupingMapper.insertSelective(mGoodsgrouping);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mGoodsgrouping.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("goodsgrouping", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsGroupingMaintServiceImpl.changeGoodsGrouping报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteGoodsGrouping(Integer id) {
		Result result = new Result();

		try {
			int i = goodsgroupingMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsGroupingMaintServiceImpl.deleteGoodsGrouping报错：", e);
		}

		return result;
	}

}
