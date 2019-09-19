package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IBrandMaintService;
import com.fast.service.IDataService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 品牌
 * @author J
 *
 */
@Service
public class BrandMaintServiceImpl implements IBrandMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MBrandMapper brandMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MGoodsMapper goodsMaper;

	@Override
	public Result changeBrand(MBrand brand, MUser user) {
		Result result = new Result();

		try {
			MBrandExample example = new MBrandExample();
			if (brand.getId() != null) {
				example.createCriteria().andCodeEqualTo(brand.getCode().trim()).andIdNotEqualTo(brand.getId());
			} else {
				example.createCriteria().andCodeEqualTo(brand.getCode().trim());
			}
			List<MBrand> list = brandMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MBrand mBrand = new MBrand();
			brand.setUpdatedtime(now);
			if (brand.getId() != null) {
				mBrand = brandMapper.selectByPrimaryKey(brand.getId());
				BeanUtil.copyPropertiesIgnoreNull(brand, mBrand);
				mBrand.setModifier(user.getName());
				mBrand.setModifytime(now);
				int changeNum = brandMapper.updateByPrimaryKeySelective(mBrand);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mBrand.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(brand, mBrand);
				mBrand.setCreator(user.getName());
				mBrand.setCreatetime(now);
				int key = brandMapper.insertSelective(mBrand);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mBrand.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("brand", Integer.valueOf(result.getId().toString()));
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用BrandMaintServiceImpl.changeBrand报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteBrand(Integer id) {
		Result result = new Result();

		try {
			MGoodsExample example = new MGoodsExample();
			example.createCriteria().andBrandidEqualTo(id);
			List<MGoods> list = goodsMaper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("删除失败！此品牌已被使用");
				return result;
			}
			int i = brandMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用BrandMaintServiceImpl.deleteBrand报错：", e);
		}

		return result;
	}

}
