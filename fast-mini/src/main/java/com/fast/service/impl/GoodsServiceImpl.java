package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import com.fast.service.IDataService;
import com.fast.service.IGoodsService;
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
public class GoodsServiceImpl implements IGoodsService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGoodsMapper goodsMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MGoodsskuMapper goodsskuMapper;

	@Override
	public Result goods() {
		Result result = new Result();

		try {
			MGoodsExample example = new MGoodsExample();
			example.setOrderByClause("code asc");
			List<MGoods> list = goodsMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.goods报错：", e);
		}

		return result;
	}
	
	@Override
	public Result topFour(Integer id, Integer type, Integer orderBy) {
		Result result = new Result();

		try {
			String sql = "select top 4 * from m_goods where useflag=1 ";
			if (type.intValue() == 1) {
				sql += "and (bigcategory="+id+" or middlecategory="+id+" or smallcategory="+id+")";
			} else if (type.intValue() == 2) {
				sql += "and id in (select goodsid from m_goodsingroup where groupingid="+id+")";
			}
			
			List<LinkedHashMap<String, Object>> goodsList = dataMapper.pageList(sql);
			goodsList = CommonUtil.transformUpperCase(goodsList);
			
			result.setData(goodsList);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.topFour报错：", e);
		}

		return result;
	}

	@Override
	public Result goodsDetail(Integer id, String openid, String appid) {
		Result result = new Result();

		try {
			if (id == null || id.intValue() < 1) {
				result.setMessage("商品id无效");
				return result;
			}
			
			String sql = "select * from m_goods where id=" + id;
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				list = iDataService.completeData(list, "goods");
				LinkedHashMap<String, Object> goods = list.get(0);
				// 主图/明细图
				List<Object> mainPhoto = new ArrayList<>();
				List<Object> detailPhoto = new ArrayList<>();
				sql = "select * from m_goodsdtl where goodsid=" + id + " order by type asc,showindex asc";
				List<LinkedHashMap<String, Object>> detailList = dataMapper.pageList(sql);
				if (detailList != null && detailList.size() > 0) {
					detailList = CommonUtil.transformUpperCase(detailList);
					for (int i = 0; i < detailList.size(); i++) {
						Integer type = Integer.valueOf(detailList.get(i).get("type").toString());
						if (type.intValue() == 1) {
							mainPhoto.add(detailList.get(i).get("photourl"));
						} else {
							detailPhoto.add(detailList.get(i).get("photourl"));
						}
					}
				}
				goods.put("mainphoto", mainPhoto);
				goods.put("detailphoto", detailPhoto);
				
				// 参数
				List<LinkedHashMap<String, Object>> parameter = new ArrayList<>();
				LinkedHashMap<String, Object> parameterMap = new LinkedHashMap<>();
				parameterMap.put("key", "编号");
				parameterMap.put("value", goods.get("code"));
				parameter.add(parameterMap);
				parameterMap = new LinkedHashMap<>();
				parameterMap.put("key", "名称");
				parameterMap.put("value", goods.get("name"));
				parameter.add(parameterMap);
				String brand = goods.get("brand") == null ? "" : goods.get("brand").toString();
				if (!Common.isEmpty(brand)) {
					parameterMap = new LinkedHashMap<>();
					parameterMap.put("key", "品牌");
					parameterMap.put("value", brand);
					parameter.add(parameterMap);
				}
				String bigcategoryname = goods.get("bigcategoryname") == null ? "" : goods.get("bigcategoryname").toString();
				if (!Common.isEmpty(bigcategoryname)) {
					parameterMap = new LinkedHashMap<>();
					parameterMap.put("key", "大类");
					parameterMap.put("value", bigcategoryname);
					parameter.add(parameterMap);
				}
				String middlecategoryname = goods.get("middlecategoryname") == null ? "" : goods.get("middlecategoryname").toString();
				if (!Common.isEmpty(middlecategoryname)) {
					parameterMap = new LinkedHashMap<>();
					parameterMap.put("key", "中类");
					parameterMap.put("value", middlecategoryname);
					parameter.add(parameterMap);
				}
				String smallcategoryname = goods.get("smallcategoryname") == null ? "" : goods.get("smallcategoryname").toString();
				if (!Common.isEmpty(smallcategoryname)) {
					parameterMap = new LinkedHashMap<>();
					parameterMap.put("key", "小类");
					parameterMap.put("value", smallcategoryname);
					parameter.add(parameterMap);
				}
				goods.put("parameter", parameter);
				
				result.setData(goods);
				result.setErrcode(Integer.valueOf(0));
			} else {
				result.setMessage("无此商品");
				return result;
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.goodsDetail报错：", e);
		}

		return result;
	}

	@Override
	public Result goodsInfo(Integer goodsid) {
		Result result = new Result();

		try {
			HashMap<String, Object> map = new HashMap<>();
			String sql = "select * from m_goodsdtl where goodsid=" + goodsid + " order by showindex";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
			}
			map.put("images", list);
			sql = "select a.*,case when b.id is not null then 1 else 0 end as checked,case when b.id is null then 0 else b.id end as checkedid "
					+ "from m_goodsgrouping a "
					+ "left join m_goodsingroup b on a.id=b.groupingid and b.goodsid="+goodsid+" "
					+ "where a.useflag=1 "
					+ "order by code";
			list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
			}
			map.put("groups", list);
			result.setData(map);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.goodsImages报错：", e);
		}

		return result;
	}

	@Override
	public Result goodsSKU(Integer goodsid) {
		Result result = new Result();

		try {
			String sql = "select * from m_goodssku where goodsid=" + goodsid + " order by colorid,patternid,sizeid";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "goodssku");
			result.setData(list);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.goodsSKU报错：", e);
		}

		return result;
	}

}
