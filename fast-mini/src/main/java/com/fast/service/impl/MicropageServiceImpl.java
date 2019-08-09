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
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MGoodsgroupingMapper;
import com.fast.base.data.dao.MMicropageMapper;
import com.fast.base.data.dao.MMicropagesetMapper;
import com.fast.base.data.dao.MMicropagesetdtlMapper;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MMicropageExample;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.service.IMicropageService;
import com.fast.service.IMiniProgramService;
import com.fast.service.IWechatService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

/**
 * 微页面
 * @author J
 *
 */
@Service
public class MicropageServiceImpl implements IMicropageService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMicropageMapper micropageMapper;
	
	@Autowired
	MMicropagesetMapper micropagesetMapper;
	
	@Autowired
	MMicropagesetdtlMapper micropagesetdtlMapper;
	
	@Autowired
	IWechatService iWechatService;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	MPublicplatformMapper publicPlatformMapper;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	MGoodsgroupingMapper goodsgroupingMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MGoodsMapper goodsMapper;
	
	@Autowired
	MCouponMapper couponMapper;
	
	@Override
	public Result list(PagingView page, Integer publicPlatformID) {
		Result result = new Result();

		try {
			page.setOrderBy("order by code,name");
			String sql = "select * from m_micropage";
			if (publicPlatformID != null && publicPlatformID.intValue() > 0) {
				sql = sql + " where publicplatformid="+publicPlatformID;
			}
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "micropage");
			page.setData(list);
			result.setErrcode(0);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageServiceImpl.list报错：", e);
		}

		return result;
	}

	@Override
	public Result micropage(String appid, Integer pageid, String openid, String ip) {
		Result result = new Result();

		try {
			MMicropage micropage = null;
			// 指定id的微页面
			if (pageid != null) {
				if (pageid.intValue() > 0) {
					micropage = micropageMapper.selectByPrimaryKey(pageid);
				}
			}
			// 首页
			else {
				Integer publicplatformid = null;
				Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
				if (Common.isActive(r)) {
					MMiniprogram miniprogram = (MMiniprogram) r.getData();
					if (miniprogram != null && miniprogram.getId() != null) {
						publicplatformid = miniprogram.getPublicplatformid();
					}
				}
				
				MMicropageExample example = new MMicropageExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andHomeflagEqualTo(Byte.valueOf("1")).andPublicplatformidEqualTo(publicplatformid);
				List<MMicropage> list = micropageMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					micropage = list.get(0);
				}
			}

			if (micropage == null || micropage.getId() == null) {
				result.setMessage("无微页面");
				return result;
			}
			
			result = queryPageData(micropage.getId(), false);
			result.setId(micropage.getId());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageServiceImpl.micropage报错：", e);
		}

		return result;
	}

	@Override
	public Result queryPageData(Integer pageID, boolean isDraft) {
		Result result = new Result();

		try {
			HashMap<String, Object> dataMap = new HashMap<>();
			String sql = "select id,code,name,homeflag,memo,useflag,publishflag,publisher,publicplatformid,miniprogramid from m_micropage where id=" + pageID;
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				LinkedHashMap<String, Object> map = list.get(0);
				String publicPlatformName = "";
				String publicPlatformCode = "";
				Integer publicPlatformID = Integer.valueOf(map.get("publicplatformid").toString());
				MPublicplatform publicplatform = publicPlatformMapper.selectByPrimaryKey(publicPlatformID);
				if (publicplatform != null) {
					publicPlatformName = publicplatform.getName();
					publicPlatformCode = publicplatform.getCode();
				}
				map.put("publicplatform", publicPlatformName);
				map.put("publicplatformcode", publicPlatformCode);
				dataMap.put("micropage", map);
				
				sql = "select id,micropageid,kind,showindex,showname,showprice,imagestyle,orderby,memo from m_micropageset where micropageid=" + pageID + " order by showindex";
				if (isDraft) {
					sql = "select id,micropageid,kind,showindex,showname,showprice,imagestyle,orderby,memo from m_micropagesetdraft where micropageid=" + pageID + " order by showindex";
				}
				List<LinkedHashMap<String, Object>> setList = dataMapper.pageList(sql);
				if (setList != null && setList.size() > 0) {
					setList = CommonUtil.transformUpperCase(setList);
					List<String> setIDList = new ArrayList<>();
					
					for (int i = 0; i < setList.size(); i++) {
						setIDList.add(setList.get(i).get("id").toString().trim());
					}
					sql = "select id,micropagesetid,showindex,first,second,third,text,photourl,targetpath,type from m_micropagesetdtl where micropagesetid in (" + StringUtils.join(setIDList.toArray(), ",") + ") order by showindex";
					if (isDraft) {
						sql = "select id,micropagesetid,showindex,first,second,third,text,photourl,targetpath,type from m_micropagesetdtldraft where micropagesetid in (" + StringUtils.join(setIDList.toArray(), ",") + ") order by showindex";
					}
					List<LinkedHashMap<String, Object>> setDtlList = dataMapper.pageList(sql);
					for (int i = 0; i < setList.size(); i++) {
						LinkedHashMap<String, Object> setMap = setList.get(i);
						// 1广告 2搜索 3导航 4公告 5栏目标题 6辅助空白 7商品分组 8商品分类 9商品列表
						String kind = setMap.get("kind") == null ? "" : setMap.get("kind").toString().trim();
						List<LinkedHashMap<String, Object>> dtlList = new ArrayList<>();
						if (setDtlList != null && setDtlList.size() > 0) {
							setDtlList = CommonUtil.transformUpperCase(setDtlList);
							for (int j = 0; j < setDtlList.size(); j++) {
								if (setMap.get("id").toString().trim().equals(setDtlList.get(j).get("micropagesetid").toString().trim())) {
									String first = setDtlList.get(j).get("first") == null ? "" : setDtlList.get(j).get("first").toString().trim();
									String second = setDtlList.get(j).get("second") == null ? "" : setDtlList.get(j).get("second").toString().trim();
									String third = setDtlList.get(j).get("third") == null ? "" : setDtlList.get(j).get("third").toString().trim();
									//String linkType = setDtlList.get(j).get("type") == null ? "0" : setDtlList.get(j).get("type").toString().trim();
									String grouping = "";
									String goodsname = "";
									BigDecimal price = BigDecimal.ZERO;
									Integer point = 0;
									Integer type = setDtlList.get(j).get("type") == null ? 0 : Integer.valueOf(setDtlList.get(j).get("type").toString().trim());
									String category = "";
									String categoryone = "";
									String categorytwo = "";
									String categorythree = "";
									List<LinkedHashMap<String, Object>> subList = new ArrayList<>();
									if (!Common.isEmpty(first)) {
										// 分组
										if ("7".equals(kind)) {
											MGoodsgrouping goodsgrouping = goodsgroupingMapper.selectByPrimaryKey(Integer.valueOf(first));
											if (goodsgrouping != null && goodsgrouping.getId() > 0) {
												grouping = goodsgrouping.getName();
												sql = "select top 4 * from m_goods where useflag=1 and id in (select goodsid from m_goodsingroup where groupingid="+goodsgrouping.getId()+") order by id";
												List<LinkedHashMap<String, Object>> goodsList = dataMapper.pageList(sql);
												goodsList = CommonUtil.transformUpperCase(goodsList);
												for (int k = 0; k < goodsList.size(); k++) {
													LinkedHashMap<String, Object> goodsMap = goodsList.get(k);
													LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
													newMap.put("id", goodsMap.get("id"));
													newMap.put("name", goodsMap.get("name") == null ? "" : goodsMap.get("name"));
													newMap.put("photourl", goodsMap.get("photourl") == null ? "" : goodsMap.get("photourl"));
													newMap.put("price", goodsMap.get("price") == null ? 0 : goodsMap.get("price"));
													newMap.put("point", goodsMap.get("exchangepoint") == null ? 0 : goodsMap.get("exchangepoint"));
													newMap.put("kind", goodsMap.get("kind") == null ? 1 : goodsMap.get("kind"));
													subList.add(newMap);
												}
											}
										}
										// 分类
										else if ("8".equals(kind) || type.intValue() == 3) {
											if (!Common.isEmpty(third)) {
												MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(third));
												if (goodscategory != null && goodscategory.getId() > 0) {
													category = goodscategory.getName();
													categorythree = goodscategory.getName();
													sql = "select top 4 * from m_goods where useflag=1 and smallcategory="+goodscategory.getId()+" order by id";
													List<LinkedHashMap<String, Object>> goodsList = dataMapper.pageList(sql);
													goodsList = CommonUtil.transformUpperCase(goodsList);
													for (int k = 0; k < goodsList.size(); k++) {
														LinkedHashMap<String, Object> goodsMap = goodsList.get(k);
														LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
														newMap.put("id", goodsMap.get("id"));
														newMap.put("name", goodsMap.get("name") == null ? "" : goodsMap.get("name"));
														newMap.put("photourl", goodsMap.get("photourl") == null ? "" : goodsMap.get("photourl"));
														newMap.put("price", goodsMap.get("price") == null ? 0 : goodsMap.get("price"));
														newMap.put("point", goodsMap.get("exchangepoint") == null ? 0 : goodsMap.get("exchangepoint"));
														newMap.put("kind", goodsMap.get("kind") == null ? 1 : goodsMap.get("kind"));
														subList.add(newMap);
													}
												}
												List<Integer> idList = new ArrayList<>();
												idList.add(Integer.valueOf(first));
												idList.add(Integer.valueOf(second));
												MGoodscategoryExample example = new MGoodscategoryExample();
												example.createCriteria().andIdIn(idList);
												List<MGoodscategory> goodscategories = goodscategoryMapper.selectByExample(example);
												for (int k = 0; k < goodscategories.size(); k++) {
													if (first.trim().equals(goodscategories.get(k).getId().toString())) {
														categoryone = goodscategories.get(k).getName();
													}
													if (second.trim().equals(goodscategories.get(k).getId().toString())) {
														categorytwo = goodscategories.get(k).getName();
													}
												}
											}
											if (!Common.isEmpty(second) && Common.isEmpty(category)) {
												MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(second));
												if (goodscategory != null && goodscategory.getId() > 0) {
													category = goodscategory.getName();
													categorytwo = goodscategory.getName();
													sql = "select top 4 * from m_goods where useflag=1 and middlecategory="+goodscategory.getId()+" order by id";
													List<LinkedHashMap<String, Object>> goodsList = dataMapper.pageList(sql);
													goodsList = CommonUtil.transformUpperCase(goodsList);
													for (int k = 0; k < goodsList.size(); k++) {
														LinkedHashMap<String, Object> goodsMap = goodsList.get(k);
														LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
														newMap.put("id", goodsMap.get("id"));
														newMap.put("name", goodsMap.get("name") == null ? "" : goodsMap.get("name"));
														newMap.put("photourl", goodsMap.get("photourl") == null ? "" : goodsMap.get("photourl"));
														newMap.put("price", goodsMap.get("price") == null ? 0 : goodsMap.get("price"));
														newMap.put("point", goodsMap.get("exchangepoint") == null ? 0 : goodsMap.get("exchangepoint"));
														newMap.put("kind", goodsMap.get("kind") == null ? 1 : goodsMap.get("kind"));
														subList.add(newMap);
													}
												}
												goodscategory = new MGoodscategory();
												goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(first));
												if (goodscategory != null && goodscategory.getId() > 0) {
													categoryone = goodscategory.getName();
												}
											}
											if (!Common.isEmpty(first) && Common.isEmpty(category)) {
												MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(first));
												if (goodscategory != null && goodscategory.getId() > 0) {
													category = goodscategory.getName();
													categoryone = goodscategory.getName();
													sql = "select top 4 * from m_goods where useflag=1 and bigcategory="+goodscategory.getId()+" order by id";
													List<LinkedHashMap<String, Object>> goodsList = dataMapper.pageList(sql);
													goodsList = CommonUtil.transformUpperCase(goodsList);
													for (int k = 0; k < goodsList.size(); k++) {
														LinkedHashMap<String, Object> goodsMap = goodsList.get(k);
														LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
														newMap.put("id", goodsMap.get("id"));
														newMap.put("name", goodsMap.get("name") == null ? "" : goodsMap.get("name"));
														newMap.put("photourl", goodsMap.get("photourl") == null ? "" : goodsMap.get("photourl"));
														newMap.put("price", goodsMap.get("price") == null ? 0 : goodsMap.get("price"));
														newMap.put("point", goodsMap.get("exchangepoint") == null ? 0 : goodsMap.get("exchangepoint"));
														newMap.put("kind", goodsMap.get("kind") == null ? 1 : goodsMap.get("kind"));
														subList.add(newMap);
													}
												}
											}
										}
										// 商品
										else if ("9".equals(kind)) {
											MGoods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(first));
											if (goods != null && goods.getId() > 0) {
												goodsname = goods.getName() == null ? "" : goods.getName();
												price = goods.getPrice() == null ? BigDecimal.ZERO : goods.getPrice();
												point = goods.getExchangepoint() == null ? 0 : goods.getExchangepoint();
												type = goods.getKind() == null ? 1 : goods.getKind().intValue();
												setDtlList.get(j).put("photourl", goods.getPhotourl() == null ? "" : goods.getPhotourl());
											}
										}
										// 优惠券
										else if ("10".equals(kind)) {
											MCoupon coupon = couponMapper.selectByPrimaryKey(Integer.valueOf(first));
											if (coupon != null && coupon.getId() > 0) {
												grouping = coupon.getName() == null ? "" : coupon.getName();
											}
										} else {
											
											// 0无 1微页面 2分组 3分类 4小程序页面
											if ("1".equals(type.toString())) {
												MMicropage micropage = micropageMapper.selectByPrimaryKey(Integer.valueOf(first.trim()));
												if (micropage != null && micropage.getId() > 0) {
													grouping = micropage.getName();
												}
											} else if ("2".equals(type.toString())) {
												MGoodsgrouping goodsgrouping = goodsgroupingMapper.selectByPrimaryKey(Integer.valueOf(first));
												if (goodsgrouping != null && goodsgrouping.getId() > 0) {
													grouping = goodsgrouping.getName();
												}
											}
										}
									}
									setDtlList.get(j).put("grouping", grouping);
									setDtlList.get(j).put("category", category);
									setDtlList.get(j).put("goodsname", goodsname);
									setDtlList.get(j).put("price", price);
									setDtlList.get(j).put("point", point);
									setDtlList.get(j).put("type", type);
									setDtlList.get(j).put("list", subList);
									setDtlList.get(j).put("categoryone", categoryone);
									setDtlList.get(j).put("categorytwo", categorytwo);
									setDtlList.get(j).put("categorythree", categorythree);
									dtlList.add(setDtlList.get(j));
								}
							}
						}
						setMap.put("detail", dtlList);
						
						int choose = 0;
						if (i == 0) {
							choose = 1;
						}
						setMap.put("choose", choose);
					}
				} else {
					setList = new ArrayList<>();
				}
				dataMap.put("setdata", setList);
				
				result.setData(dataMap);
				result.setErrcode(Integer.valueOf(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageServiceImpl.queryPageData报错：", e);
		}

		return result;
	}

}
