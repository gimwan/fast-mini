package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MVipcartMapper;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipcart;
import com.fast.base.data.entity.MVipcartExample;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipService;
import com.fast.service.IVipcartMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 购物袋
 * @author J
 *
 */
@Service
public class VipcartMaintServiceImpl implements IVipcartMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipcartMapper vipcartMapper;
	
	@Autowired
	MGoodsMapper goodsMapper;
	
	@Autowired
	IVipService iVipService;
	
	@Autowired
	IMiniProgramService iMiniProgramService;

	@Override
	public Result addVipcart(String appid, String openid, Integer goodsid, Integer colorid, Integer patternid,
			Integer sizeid, Integer quantity) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			Integer publicplatformid = 0;
			Result re = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(re)) {
				MMiniprogram miniprogram = (MMiniprogram) re.getData();
				publicplatformid = miniprogram.getPublicplatformid();
			}
			Byte kind = 1;
			MGoods goods = goodsMapper.selectByPrimaryKey(goodsid);
			if (goods != null && goods.getId() != null) {
				kind = goods.getKind();
			}
			Result r = iVipService.queryVipByOpenid(appid, openid);
			if (Common.isActive(r)) {
				JSONObject object = JSONObject.fromObject(r.getData());
				MVip vip = (MVip) JSONObject.toBean(object, MVip.class);
				Date now = new Date();
				MVipcartExample example = new MVipcartExample();
				example.createCriteria().andVipidEqualTo(vip.getId()).andPublicplatformidEqualTo(publicplatformid)
						.andGoodsidEqualTo(goodsid).andColoridEqualTo(colorid).andPatternidEqualTo(patternid)
						.andSizeidEqualTo(sizeid).andTypeEqualTo(kind);
				List<MVipcart> list = vipcartMapper.selectByExample(example);
				MVipcart vipcart = new MVipcart();
				if (list != null && list.size() > 0) {
					vipcart = list.get(0);
					Integer qty = vipcart.getQuantity() == null ? 0 : vipcart.getQuantity();
					qty = qty + quantity;
					vipcart.setQuantity(qty);
					vipcart.setModifier(vip.getName());
					vipcart.setModifytime(now);
					vipcart.setUpdatedtime(now);
					vipcartMapper.updateByPrimaryKeySelective(vipcart);
				} else {
					vipcart.setVipid(vip.getId());
					vipcart.setGoodsid(goodsid);
					vipcart.setColorid(colorid);
					vipcart.setPatternid(patternid);
					vipcart.setSizeid(sizeid);
					vipcart.setQuantity(quantity);
					vipcart.setPatternid(patternid);
					vipcart.setType(kind);
					vipcart.setPublicplatformid(publicplatformid);
					vipcart.setCreator(vip.getName());
					vipcart.setCreatetime(now);
					vipcart.setUpdatedtime(now);
					vipcartMapper.insertSelective(vipcart);
				}
				result.setErrcode(Integer.valueOf(0));
				result.setId(vipcart.getId());
				result.setData(vipcart);
			} else {
				result.setMessage("会员无效");
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipcartMaintServiceImpl.addVipcart报错：", e);
		}

		return result;
	}

	@Override
	public Result changeVipcart(String appid, String openid, Integer id, Integer goodsid, Integer colorid,
			Integer patternid, Integer sizeid, Integer quantity) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			Integer publicplatformid = 0;
			Result re = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(re)) {
				MMiniprogram miniprogram = (MMiniprogram) re.getData();
				publicplatformid = miniprogram.getPublicplatformid();
			}	
			Result r = iVipService.queryVipByOpenid(appid, openid);
			if (Common.isActive(r)) {
				JSONObject object = JSONObject.fromObject(r.getData());
				MVip vip = (MVip) JSONObject.toBean(object, MVip.class);
				MVipcart vipcart = vipcartMapper.selectByPrimaryKey(id);
				if (vipcart != null && vipcart.getId() != null) {
					Date now = new Date();
					vipcart.setVipid(vip.getId());
					vipcart.setGoodsid(goodsid);
					vipcart.setColorid(colorid);
					vipcart.setPatternid(patternid);
					vipcart.setSizeid(sizeid);
					vipcart.setQuantity(quantity);
					vipcart.setPublicplatformid(publicplatformid);
					vipcart.setModifier(vip.getName());
					vipcart.setModifytime(now);
					vipcart.setUpdatedtime(now);
					vipcartMapper.updateByPrimaryKeySelective(vipcart);
					
					result.setErrcode(Integer.valueOf(0));
					result.setData(vipcart);
					result.setId(vipcart.getId());
				} else {
					result.setMessage("购物袋记录无效");
					return result;
				}
			} else {
				result.setMessage("会员无效");
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipcartMaintServiceImpl.changeVipcart报错：", e);
		}

		return result;
	}

	@Override
	public Result deleteVipcart(String appid, String openid, Integer id) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			Integer publicplatformid = 0;
			Result re = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(re)) {
				MMiniprogram miniprogram = (MMiniprogram) re.getData();
				publicplatformid = miniprogram.getPublicplatformid();
			}	
			Result r = iVipService.queryVipByOpenid(appid, openid);
			if (Common.isActive(r)) {
				Integer vipid = r.getId();
				MVipcart vipcart = vipcartMapper.selectByPrimaryKey(id);
				if (vipcart != null && vipcart.getId() != null) {
					vipcartMapper.deleteByPrimaryKey(vipcart.getId());
					
					result.setErrcode(Integer.valueOf(0));
					result.setId(vipid);
				} else {
					result.setMessage("此记录已删除");
					return result;
				}
			} else {
				result.setMessage("会员无效");
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipcartMaintServiceImpl.deleteVipcart报错：", e);
		}

		return result;
	}

}
