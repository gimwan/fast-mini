package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MGoodsgroupingMapper;
import com.fast.base.data.dao.MGoodsingroupMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MGoodsingroup;
import com.fast.base.data.entity.MGoodsingroupExample;
import com.fast.base.data.entity.MViptype;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.service.IGoodsService;
import com.fast.service.IVipService;
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
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	MGoodsgroupingMapper goodsgroupingMapper;
	
	@Autowired
	MGoodsingroupMapper goodsingroupMapper;
	
	@Autowired
	IVipService iVipService;
	
	@Autowired
	MViptypeMapper viptypeMapper;

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
			
			// 会员折扣
			BigDecimal discount = BigDecimal.ONE;
			if (!Common.isEmpty(openid)) {
				Result r = iVipService.queryVipByOpenid(appid, openid);
				if (Common.isActive(r)) {
					HashMap<String, Object> map = (HashMap<String, Object>) r.getData();
					MViptype viptype = viptypeMapper.selectByPrimaryKey(Integer.valueOf(map.get("typeid").toString()));
					if (viptype != null && viptype.getId() != null) {
						discount = viptype.getDiscount() == null ? BigDecimal.ONE : viptype.getDiscount();
					}
				}
			}			
			
			String sql = "select * from m_goods where id=" + id;
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				list = iDataService.completeData(list, "goods");
				LinkedHashMap<String, Object> goods = list.get(0);
				
				BigDecimal price = goods.get("price") == null ? BigDecimal.ZERO : new BigDecimal(goods.get("price").toString().trim());
				BigDecimal newPrice = price.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
				goods.put("price", newPrice);
				
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
				
				String stocknum = "0";
				sql = "select isnull(sum(quantity),0) as stock from m_goodssku where goodsid=" + id + " and quantity>0";
				List<LinkedHashMap<String, Object>> stock = dataMapper.pageList(sql);
				if (stock != null && stock.size() > 0) {
					stocknum = stock.get(0).get("stock") == null ? "0" : stock.get(0).get("stock").toString().trim();
				}
				goods.put("stock", stocknum);
				
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

	@Override
	public Result queryGoodsStock(Integer goodsid, String appid, String openid) {
		Result result = new Result();

		try {
			MGoods goods = goodsMapper.selectByPrimaryKey(goodsid);
			if (goods != null && goods.getId() != null) {
				HashMap<String, Object> map = new HashMap<>();
				map.put("goodsid", goods.getId());
				map.put("goodsname", goods.getName() == null ? "" : goods.getName());
				map.put("price", goods.getPrice() == null ? BigDecimal.ZERO : goods.getPrice());
				map.put("point", goods.getExchangepoint() == null ? 0 : goods.getExchangepoint());
				map.put("imageurl", goods.getPhotourl() == null ? "" : goods.getPhotourl());
				map.put("showcolor", goods.getShowcolor() == null ? 1 : goods.getShowcolor());
				map.put("showpattern", goods.getShowpattern() == null ? 1 : goods.getShowpattern());
				map.put("showsize", goods.getShowsize() == null ? 1 : goods.getShowsize());
				map.put("atlist", goods.getOnsale() == null ? 1 : goods.getOnsale());
				map.put("kind", goods.getKind() == null ? 1 : goods.getKind());
				map.put("saleonweb", goods.getOnlyshow() == null ? 0 : goods.getOnlyshow());
				
				String sql = "select a.*,b.name as color,c.name as pattern,d.name as size "
						+ "from m_goodssku a "
						+ "left join m_color b on a.colorid=b.id "
						+ "left join m_pattern c on a.patternid=c.id "
						+ "left join m_size d on a.sizeid=d.id "
						+ "where a.goodsid=" + goods.getId();
				List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
				list = CommonUtil.transformUpperCase(list);
				List<HashMap<String, Object>> stockList = new ArrayList<>();
				List<HashMap<String, Object>> colorList = new ArrayList<>();
				List<HashMap<String, Object>> patternList = new ArrayList<>();
				List<HashMap<String, Object>> sizeList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					// 颜色
					HashMap<String, Object> color = new HashMap<>();
					boolean canAdd = true;
					if (colorList.size() > 0) {
						for (int j = 0; j < colorList.size(); j++) {
							if (colorList.get(j).get("id").toString().equals(list.get(i).get("colorid").toString())) {
								canAdd = false;
								break;
							}
						}
					}
					if (canAdd) {
						color.put("id", list.get(i).get("colorid"));
						color.put("name", list.get(i).get("color"));
						colorList.add(color);
					}
					// 版型
					HashMap<String, Object> pattern = new HashMap<>();
					canAdd = true;
					if (patternList.size() > 0) {
						for (int j = 0; j < patternList.size(); j++) {
							if (patternList.get(j).get("id").toString().equals(list.get(i).get("patternid").toString())) {
								canAdd = false;
								break;
							}
						}
					}
					if (canAdd) {
						pattern.put("id", list.get(i).get("patternid"));
						pattern.put("name", list.get(i).get("pattern"));
						patternList.add(pattern);
					}
					// 尺码
					HashMap<String, Object> size = new HashMap<>();
					canAdd = true;
					if (sizeList.size() > 0) {
						for (int j = 0; j < sizeList.size(); j++) {
							if (sizeList.get(j).get("id").toString().equals(list.get(i).get("sizeid").toString())) {
								canAdd = false;
								break;
							}
						}
					}
					if (canAdd) {
						size.put("id", list.get(i).get("sizeid"));
						size.put("name", list.get(i).get("size"));
						sizeList.add(size);
					}					
					//库存
					HashMap<String, Object> stock = new HashMap<>();
					stock.put("id", list.get(i).get("id"));
					stock.put("goodsid", list.get(i).get("goodsid"));
					stock.put("colorid", list.get(i).get("colorid"));
					stock.put("patternid", list.get(i).get("patternid"));
					stock.put("sizeid", list.get(i).get("sizeid"));
					stock.put("baseprice", goods.getBaseprice() == null ? BigDecimal.ZERO : goods.getBaseprice());
					stock.put("price", goods.getPrice() == null ? BigDecimal.ZERO : goods.getPrice());
					stock.put("point", goods.getExchangepoint() == null ? 0 : goods.getExchangepoint());
					stock.put("quantity", list.get(i).get("quantity"));
					stockList.add(stock);
				}
				HashMap<String, Object> speclist = new HashMap<>();
				speclist.put("color", colorList);
				speclist.put("pattern", patternList);
				speclist.put("size", sizeList);
				map.put("speclist", speclist);
				
				map.put("stocklist", stockList);
				result.setData(map);
				result.setErrcode(Integer.valueOf(0));
				result.setId(goods.getId());
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.queryGoodsStock报错：", e);
		}

		return result;
	}

	@Override
	public Result queryGoodsClassify(String appid) {
		Result result = new Result();

		try {
			MGoodscategoryExample example = new MGoodscategoryExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1"));
			List<MGoodscategory> list = goodscategoryMapper.selectByExample(example);
			List<HashMap<String, Object>> dataList = BeanUtil.toMapList(list);
			
			List<HashMap<String, Object>> category = new ArrayList<>();
			List<HashMap<String, Object>> middle = new ArrayList<>();
			List<HashMap<String, Object>> small = new ArrayList<>();
			for (int i = 0; i < dataList.size(); i++) {
				Integer grade = Integer.valueOf(dataList.get(i).get("grade").toString());
				if (grade.intValue() == 1) {
					category.add(dataList.get(i));
				} else if (grade.intValue() == 2) {
					middle.add(dataList.get(i));
				} else if (grade.intValue() == 3) {
					small.add(dataList.get(i));
				}
			}
			for (int i = 0; i < category.size(); i++) {
				Integer id = Integer.valueOf(category.get(i).get("id").toString());
				List<HashMap<String, Object>> childs = new ArrayList<>();
				for (int j = 0; j < middle.size(); j++) {
					Integer parentid = Integer.valueOf(middle.get(j).get("parentid").toString());
					if (parentid.intValue() == id.intValue()) {
						Integer mid = Integer.valueOf(middle.get(j).get("id").toString());
						List<HashMap<String, Object>> sub = new ArrayList<>();
						for (int k = 0; k < small.size(); k++) {
							Integer pid = Integer.valueOf(small.get(k).get("parentid").toString());
							if (pid.intValue() == mid.intValue()) {
								sub.add(small.get(k));
							}
						}
						middle.get(j).put("childs", sub);
						childs.add(middle.get(j));
					}
				}
				category.get(i).put("childs", childs);
			}
			
			result.setData(category);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.queryGoodsClassify报错：", e);
		}

		return result;
	}

	@Override
	public Result queryGoodsBySort(String appid, Integer type, Integer id, String keyword, Integer sortType, PagingView page) {
		Result result = new Result();

		try {
			List<LinkedHashMap<String, Object>> goods = new ArrayList<>();
			String sql = "select id,code,name,photourl,kind,price,exchangepoint as point from m_goods where useflag=1 and onsale=1 ";
			// 分类
			if (type.intValue() == 1) {
				MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(id);
				if (goodscategory.getGrade().intValue() == 1) {
					sql += " and bigcategory=" + goodscategory.getId();
				} else if (goodscategory.getGrade().intValue() == 2) {
					sql += " and middlecategory=" + goodscategory.getId();
				} else if (goodscategory.getGrade().intValue() == 3) {
					sql += " and smallcategory=" + goodscategory.getId();
				}
			}
			// 分组
			else if (type.intValue() == 2) {
				MGoodsingroupExample example = new MGoodsingroupExample();
				example.createCriteria().andGroupingidEqualTo(id);
				List<MGoodsingroup> list = goodsingroupMapper.selectByExample(example);
				List<String> idList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					idList.add(list.get(i).getGoodsid().toString());
				}
				if (idList != null && idList.size() > 0) {
					sql += " and id in (" + StringUtils.join(idList.toArray(), ",") + ")";
				}
			}
			
			// 关键词搜索
			if (!Common.isEmpty(keyword)) {
				sql += " and (code like '%" + keyword.trim() + "%' or name like '%" + keyword.trim() + "%') ";
			}
			
			// 0综合 1新品 2价格从低到高 3价格从高到低
			if (sortType.intValue() == 1) {
				page.setOrderBy(" order by onsaletime desc,createtime,code asc,name asc");
			} else if (sortType.intValue() == 2) {
				page.setOrderBy(" order by price desc,onsaletime desc,code asc,name asc");
			} else if (sortType.intValue() == 3) {
				page.setOrderBy(" order by price asc,onsaletime desc,code asc,name asc");
			} else {
				page.setOrderBy(" order by updatedtime desc,modifytime desc,onsaletime desc");
			}
			
			goods = dataMapper.pageList(sql, page);
			page.setData(goods);
					
			result.setData(page);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsServiceImpl.queryGoodsBySort报错：", e);
		}

		return result;
	}

}
