package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodsdtlMapper;
import com.fast.base.data.dao.MGoodsingroupMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.dao.MOrderdtlMapper;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import com.fast.base.data.entity.MGoodsdtl;
import com.fast.base.data.entity.MGoodsdtlExample;
import com.fast.base.data.entity.MGoodsingroup;
import com.fast.base.data.entity.MGoodsingroupExample;
import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import com.fast.base.data.entity.MOrderdtl;
import com.fast.base.data.entity.MOrderdtlExample;
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
	
	@Autowired
	MGoodsingroupMapper goodsingroupMapper;
	
	@Autowired
	MGoodsskuMapper goodsskuMapper;
	
	@Autowired
	MOrderdtlMapper orderdtlMapper;

	@Override
	public Result changeGoods(MGoods goods, MUser user) {
		Result result = new Result();

		try {
			MGoodsExample example = new MGoodsExample();
			if (goods.getId() != null) {
				example.createCriteria().andCodeEqualTo(goods.getCode().trim()).andIdNotEqualTo(goods.getId());
			} else {
				example.createCriteria().andCodeEqualTo(goods.getCode().trim());
			}
			List<MGoods> list = goodsMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
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
				Result r = iDataService.one("goods", Integer.valueOf(result.getId().toString()));
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
			MOrderdtlExample example = new MOrderdtlExample();
			example.createCriteria().andGoodsidEqualTo(id);
			List<MOrderdtl> list = orderdtlMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("删除失败！该商品已使用");
				return result;
			}
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
	public Result goodsImages(MUser user, Integer goodsid, String images, String groups) {
		Result result = new Result();

		try {
			
			images = CommonUtil.unescape(images);
			groups = CommonUtil.unescape(groups);
			List<MGoodsdtl> goodsdtls = JSONObject.parseArray(images,  MGoodsdtl.class);
			List<MGoodsingroup> grouping = JSONObject.parseArray(groups,  MGoodsingroup.class);
			
			saveImages(goodsid, user.getName(), goodsdtls, grouping);
			
			result.setErrcode(Integer.valueOf(0));
			result.setId(goodsid);
			result.setMessage("保存成功");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.goodsImages报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveImages(Integer goodsid, String username, List<MGoodsdtl> goodsdtls, List<MGoodsingroup> grouping) {
		Date now = new Date();
		List<Integer> deleteList = new ArrayList<>();
		for (int i = 0; i < goodsdtls.size(); i++) {
			MGoodsdtl goodsdtl = goodsdtls.get(i);
			goodsdtl.setUpdatedtime(now);
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
		
		deleteList = new ArrayList<>();
		for (int i = 0; i < grouping.size(); i++) {
			MGoodsingroup goodsingroup = grouping.get(i);
			goodsingroup.setUpdatedtime(now);
			if (goodsingroup.getId() != null && goodsingroup.getId() > 0) {
				goodsingroup.setModifier(username);
				goodsingroup.setModifytime(now);
				goodsingroupMapper.updateByPrimaryKeySelective(goodsingroup);
			} else {
				goodsingroup.setCreator(username);
				goodsingroup.setCreatetime(now);
				goodsingroupMapper.insertSelective(goodsingroup);
			}
			deleteList.add(goodsingroup.getId());
		}
		MGoodsingroupExample goodsingroupExample = new MGoodsingroupExample();
		goodsingroupExample.createCriteria().andGoodsidEqualTo(goodsid);
		if (deleteList.size() > 0) {
			goodsingroupExample = new MGoodsingroupExample();
			goodsingroupExample.createCriteria().andGoodsidEqualTo(goodsid).andIdNotIn(deleteList);
		}
		goodsingroupMapper.deleteByExample(goodsingroupExample);
	}

	@Override
	public Result saveGoodsSku(MUser user, Integer goodsid, String skus) {
		Result result = new Result();

		try {
			
			skus = CommonUtil.unescape(skus);
			List<MGoodssku> goodsskus = JSONObject.parseArray(skus,  MGoodssku.class);
			
			saveSku(goodsid, user.getName(), goodsskus);
			
			result.setErrcode(Integer.valueOf(0));
			result.setId(goodsid);
			result.setMessage("保存成功");
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsMaintServiceImpl.saveGoodsSku报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void saveSku(Integer goodsid, String username, List<MGoodssku> goodsskus) {
		Date now = new Date();
		List<Integer> deleteList = new ArrayList<>();
		for (int i = 0; i < goodsskus.size(); i++) {
			MGoodssku goodssku = goodsskus.get(i);
			goodssku.setUpdatedtime(now);
			if (goodssku.getId() != null && goodssku.getId() > 0) {
				goodssku.setModifier(username);
				goodssku.setModifytime(now);
				goodsskuMapper.updateByPrimaryKeySelective(goodssku);
			} else {
				goodssku.setCreator(username);
				goodssku.setCreatetime(now);
				goodsskuMapper.insertSelective(goodssku);
			}
			deleteList.add(goodssku.getId());
		}
		MGoodsskuExample example = new MGoodsskuExample();
		example.createCriteria().andGoodsidEqualTo(goodsid);
		if (deleteList.size() > 0) {
			example = new MGoodsskuExample();
			example.createCriteria().andGoodsidEqualTo(goodsid).andIdNotIn(deleteList);
		}
		goodsskuMapper.deleteByExample(example);
	}

}
