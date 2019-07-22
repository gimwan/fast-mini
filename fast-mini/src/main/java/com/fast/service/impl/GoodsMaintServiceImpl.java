package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodsdtlMapper;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsdtl;
import com.fast.base.data.entity.MGoodsdtlExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IGoodsMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

/**
 * 商品
 * @author J
 *
 */
@Service
public class GoodsMaintServiceImpl implements IGoodsMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGoodsMapper goodsMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MGoodsdtlMapper goodsdtlMapper;

	@Override
	public Result changeGoods(MGoods goods, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MGoods mGoods = new MGoods();
			goods.setUpdatedtime(now);
			if (goods.getId() != null) {
				mGoods = goodsMapper.selectByPrimaryKey(goods.getId());
				BeanUtil.copyPropertiesIgnoreNull(goods, mGoods);
				mGoods.setModifier(user.getName());
				mGoods.setModifytime(now);
				int changeNum = goodsMapper.updateByPrimaryKeySelective(mGoods);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mGoods.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(goods, mGoods);
				mGoods.setCreator(user.getName());
				mGoods.setCreatetime(now);
				int key = goodsMapper.insertSelective(mGoods);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mGoods.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("goods", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.changeGoods报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteGoods(Integer id) {
		Result result = new Result();

		try {
			int i = goodsMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.deleteGoods报错：", e);
		}

		return result;
	}

	@Override
	public Result onsaleGoods(MUser user, Integer id, Integer onsale) {
		Result result = new Result();

		try {
			MGoods goods = goodsMapper.selectByPrimaryKey(id);
			if (goods != null && goods.getId() != null) {
				goods.setOnsale(onsale.byteValue());
				goods.setOnsaleer(user.getName());
				goods.setOnsaletime(new Date());
				goodsMapper.updateByPrimaryKeySelective(goods);
				
				result.setErrcode(0);
				result.setMessage("上架成功");
				if (onsale.intValue() < 1) {
					result.setMessage("下架成功");
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.onsaleGoods报错：", e);
		}

		return result;
	}

	@Override
	public Result goodsImages(MUser user, String images) {
		Result result = new Result();

		try {
			Date now = new Date();
			Integer goodsid = 0;
			List<Integer> deleteList = new ArrayList<>();
			images = CommonUtil.unescape(images);
			List<MGoodsdtl> goodsdtls = JSONObject.parseArray(images,  MGoodsdtl.class);
			for (int i = 0; i < goodsdtls.size(); i++) {
				MGoodsdtl goodsdtl = goodsdtls.get(i);
				goodsdtl.setUpdatedtime(now);
				goodsid = goodsdtl.getGoodsid();
				if (goodsdtl.getId() != null && goodsdtl.getId() > 0) {
					goodsdtlMapper.updateByPrimaryKeySelective(goodsdtl);
				} else {
					goodsdtlMapper.insertSelective(goodsdtl);
				}
				deleteList.add(goodsdtl.getId());
			}
			
			MGoodsdtlExample example = new MGoodsdtlExample();
			example.createCriteria().andGoodsidEqualTo(goodsid);
			if (deleteList.size() > 0) {
				example = new MGoodsdtlExample();
				example.createCriteria().andGoodsidEqualTo(goodsid).andIdNotIn(deleteList);
			}
			goodsdtlMapper.deleteByExample(example);
			result.setErrcode(Integer.valueOf(0));
			result.setId(goodsid);
			result.setMessage("保存成功");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.goodsImages报错：", e);
		}

		return result;
	}

}
