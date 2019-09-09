package com.fast.service.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MGroupbuyMapper;
import com.fast.base.data.dao.MGroupbuydtlMapper;
import com.fast.base.data.entity.MGroupbuy;
import com.fast.base.data.entity.MGroupbuyExample;
import com.fast.base.data.entity.MGroupbuydtl;
import com.fast.base.data.entity.MGroupbuydtlExample;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.service.IDataService;
import com.fast.service.IGoodsService;
import com.fast.service.IGroupbuyService;
import com.fast.service.IMiniProgramService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

/**
 * 拼团活动
 * @author J
 *
 */
@Service
public class GroupbuyServiceImpl implements IGroupbuyService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGroupbuyMapper groupbuyMapper;
	
	@Autowired
	MGroupbuydtlMapper groupbuydtlMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IGoodsService iGoodsService;

	@Override
	public Result groupbuy() {
		Result result = new Result();

		try {
			MGroupbuyExample example = new MGroupbuyExample();
			example.setOrderByClause("code asc");
			List<MGroupbuy> list = groupbuyMapper.selectByExample(example);
			List<HashMap<String, Object>> data = BeanUtil.toMapList(list);
			result.setErrcode(0);
			result.setData(data);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.groupbuy报错：", e);
		}

		return result;
	}

	@Override
	public Result groupbuyDetail(Integer groupbuyid) {
		Result result = new Result();

		try {
			String sql = "select * from m_groupbuydtl where groupbuyid=" + groupbuyid + " order by id";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "groupbuydtl");
			result.setData(list);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.groupbuyDetail报错：", e);
		}

		return result;
	}
	
	@Override
	public Result queryGroupBuy(String appid) {
		Result result = new Result();

		try {
			HashMap<String, Object> data = new HashMap<>();			
			HashMap<String, Object> active = new HashMap<>();
			HashMap<String, Object> soon = new HashMap<>();
			
			Result r = queryActiveGroupBuy(appid);
			if (Common.isActive(r)) {
				active = (HashMap<String, Object>) r.getData();
			}
			data.put("active", active);
			
			r = querySoonGroupBuy(appid);
			if (Common.isActive(r)) {
				soon = (HashMap<String, Object>) r.getData();
			}
			data.put("soon", soon);
			
			result.setData(data);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.queryGroupBuy报错：", e);
		}

		return result;
	}

	@Override
	public Result queryActiveGroupBuy(String appid) {
		Result result = new Result();

		try {
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				MMiniprogram miniprogram = (MMiniprogram) r.getData();
				
				Date now = new Date();
				MGroupbuyExample example = new MGroupbuyExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("1"))
						.andPublicplatformidEqualTo(miniprogram.getPublicplatformid())
						.andBegintimeLessThanOrEqualTo(now).andEndtimeGreaterThan(now);
				example.setOrderByClause(" createtime desc");
				List<MGroupbuy> list = groupbuyMapper.selectByExample(example);
				HashMap<String, Object> data = new HashMap<>();
				if (list != null && list.size() > 0) {
					MGroupbuy groupbuy = list.get(0);
					data.put("id", groupbuy.getId());
					data.put("code", groupbuy.getCode());
					data.put("name", groupbuy.getName());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String begintime = sf.format(groupbuy.getBegintime());
					String endtime = sf.format(groupbuy.getEndtime());
					data.put("begintime", begintime);
					data.put("endtime", endtime);
					data.put("minimum", groupbuy.getMinimum());
					data.put("photourl", groupbuy.getPhotourl() == null ? "" : groupbuy.getPhotourl());
					
					String sql = "select a.goodsid,b.code,b.name,b.photourl,b.price as saleprice,a.price,isnull(c.groupnum,0) as groupnum "
							+ "from m_groupbuydtl a "
							+ "inner join m_goods b on a.goodsid=b.id "
							+ "left join (select aa.goodsid,count(bb.id) as groupnum "
							+ "from m_orderdtl aa "
							+ "inner join m_order bb on aa.orderid=bb.id "
							+ "where bb.status>2 and bb.kind=3 and bb.marketingid="+groupbuy.getId()+" "
							+ "group by aa.goodsid) c on c.goodsid=a.goodsid "
							+ "where b.useflag=1 and b.onsale=1 and b.kind=1 and b.onlyshow<>1 and a.groupbuyid="+groupbuy.getId();
					List<LinkedHashMap<String, Object>> goods = dataMapper.pageList(sql);
					data.put("detail", goods);
				}
				result.setData(data);
				result.setErrcode(Integer.valueOf(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.queryActiveGroupBuy报错：", e);
		}

		return result;
	}
	
	@Override
	public Result querySoonGroupBuy(String appid) {
		Result result = new Result();

		try {
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				MMiniprogram miniprogram = (MMiniprogram) r.getData();
				
				Date now = new Date();
				MGroupbuyExample example = new MGroupbuyExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("1"))
						.andPublicplatformidEqualTo(miniprogram.getPublicplatformid())
						.andBegintimeGreaterThan(now).andEndtimeGreaterThan(now);
				example.setOrderByClause(" begintime asc");
				List<MGroupbuy> list = groupbuyMapper.selectByExample(example);
				HashMap<String, Object> data = new HashMap<>();
				if (list != null && list.size() > 0) {
					MGroupbuy groupbuy = list.get(0);
					data.put("id", groupbuy.getId());
					data.put("code", groupbuy.getCode());
					data.put("name", groupbuy.getName());
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String begintime = sf.format(groupbuy.getBegintime());
					String endtime = sf.format(groupbuy.getEndtime());
					data.put("begintime", begintime);
					data.put("endtime", endtime);
					data.put("minimum", groupbuy.getMinimum());
					data.put("photourl", groupbuy.getPhotourl() == null ? "" : groupbuy.getPhotourl());
					
					String sql = "select a.goodsid,b.code,b.name,b.photourl,b.price as saleprice,a.price,isnull(c.groupnum,0) as groupnum "
							+ "from m_groupbuydtl a "
							+ "inner join m_goods b on a.goodsid=b.id "
							+ "left join (select aa.goodsid,count(bb.id) as groupnum "
							+ "from m_orderdtl aa "
							+ "inner join m_order bb on aa.orderid=bb.id "
							+ "where bb.status>2 and bb.kind=3 and bb.marketingid="+groupbuy.getId()+" "
							+ "group by aa.goodsid) c on c.goodsid=a.goodsid "
							+ "where b.useflag=1 and b.onsale=1 and b.kind=1 and b.onlyshow<>1 and a.groupbuyid="+groupbuy.getId();
					List<LinkedHashMap<String, Object>> goods = dataMapper.pageList(sql);
					data.put("detail", goods);
				}
				result.setData(data);
				result.setErrcode(Integer.valueOf(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.querySoonGroupBuy报错：", e);
		}

		return result;
	}

	@Override
	public Result queryGroupbuyDetail(Integer groupbuyid, Integer goodsid) {
		Result result = new Result();

		try {
			MGroupbuy groupbuy = groupbuyMapper.selectByPrimaryKey(groupbuyid);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String begintime = sf.format(groupbuy.getBegintime());
			String endtime = sf.format(groupbuy.getEndtime());
			int active = 0;
			Date now = new Date();
			if (groupbuy.getBegintime().getTime() <= now.getTime() && groupbuy.getEndtime().getTime() > now.getTime() ) {
				active = 1;
			} else if (groupbuy.getBegintime().getTime() > now.getTime()) {
				active = 2;
			}
			
			String sql = "select a.groupbuyid,a.goodsid,b.code,b.name,b.photourl,b.price as saleprice,a.price,"
					+ "case when b.useflag=1 and b.onsale=1 and b.onlyshow<>1 then 1 else 0 end as onsale,b.showcolor,b.showsize,b.showpattern "
					+ "from m_groupbuydtl a "
					+ "inner join m_goods b on a.goodsid=b.id "
					+ "where b.kind=1 and a.groupbuyid=" + groupbuy.getId();
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				LinkedHashMap<String, Object> goods = list.get(0);
				goods.put("groupbuyname", groupbuy.getName());
				goods.put("groupbuyphotourl", groupbuy.getPhotourl());
				goods.put("minimum", groupbuy.getMinimum());
				goods.put("begintime", begintime);
				goods.put("endtime", endtime);
				goods.put("active", active);
				
				// 主图/明细图
				List<Object> mainPhoto = new ArrayList<>();
				List<Object> detailPhoto = new ArrayList<>();
				sql = "select * from m_goodsdtl where goodsid=" + goodsid + " order by type asc,showindex asc";
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
			FastLog.error("调用GroupbuyServiceImpl.queryGroupbuyDetail报错：", e);
		}

		return result;
	}

	@Override
	public Result queryGoodsStock(Integer groupbuyid, Integer goodsid, String appid, String openid) {
		Result result = new Result();

		try {
			Result r = iGoodsService.queryGoodsStock(goodsid, appid, openid);
			if (Common.isActive(r)) {
				HashMap<String, Object> map = (HashMap<String, Object>) r.getData();
				if (map != null) {
					MGroupbuydtlExample example = new MGroupbuydtlExample();
					example.createCriteria().andGroupbuyidEqualTo(groupbuyid).andGoodsidEqualTo(goodsid);
					List<MGroupbuydtl> list = groupbuydtlMapper.selectByExample(example);
					if (list != null && list.size() > 0) {
						MGroupbuydtl groupbuydtl = list.get(0);
						map.put("saleprice", map.get("price") == null ? 0 : map.get("price"));
						map.put("price", groupbuydtl.getPrice());
						List<HashMap<String, Object>> stockList = (List<HashMap<String, Object>>) map.get("stocklist");
						for (int i = 0; i < stockList.size(); i++) {
							HashMap<String, Object> stock = stockList.get(i);
							stock.put("saleprice", stock.get("price") == null ? 0 : stock.get("price"));
							stock.put("price", groupbuydtl.getPrice());
						}
					}
				}
				result.setData(map);
				result.setErrcode(Integer.valueOf(0));
				result.setId(r.getId());
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GroupbuyServiceImpl.queryGoodsStock报错：", e);
		}

		return result;
	}

}
