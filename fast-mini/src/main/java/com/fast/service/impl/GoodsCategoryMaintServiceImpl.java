package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IGoodsCategoryMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 分类
 * @author J
 *
 */
@Service
public class GoodsCategoryMaintServiceImpl implements IGoodsCategoryMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result changeGoodsCategory(MGoodscategory goodscategory, MUser user) {
		Result result = new Result();

		try {
			MGoodscategoryExample example = new MGoodscategoryExample();
			if (goodscategory.getId() != null) {
				example.createCriteria().andCodeEqualTo(goodscategory.getCode().trim()).andIdNotEqualTo(goodscategory.getId());
			} else {
				example.createCriteria().andCodeEqualTo(goodscategory.getCode().trim());
			}
			List<MGoodscategory> list = goodscategoryMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MGoodscategory mGoodscategory = new MGoodscategory();
			goodscategory.setUpdatedtime(now);
			if (goodscategory.getId() != null) {
				mGoodscategory = goodscategoryMapper.selectByPrimaryKey(goodscategory.getId());
				BeanUtil.copyPropertiesIgnoreNull(goodscategory, mGoodscategory);
				mGoodscategory.setModifier(user.getName());
				mGoodscategory.setModifytime(now);
				int changeNum = goodscategoryMapper.updateByPrimaryKeySelective(mGoodscategory);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mGoodscategory.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(goodscategory, mGoodscategory);
				mGoodscategory.setCreator(user.getName());
				mGoodscategory.setCreatetime(now);
				int key = goodscategoryMapper.insertSelective(mGoodscategory);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mGoodscategory.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("goodscategory", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsCategoryMaintServiceImpl.changeGoodsCategory报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteGoodsCategory(Integer id) {
		Result result = new Result();

		try {
			int i = goodscategoryMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsGroupingMaintServiceImpl.deleteGoodsCategory报错：", e);
		}

		return result;
	}

}
