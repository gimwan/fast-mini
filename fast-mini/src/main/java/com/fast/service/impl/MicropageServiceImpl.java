package com.fast.service.impl;

import java.io.Serializable;
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
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodscategory;
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
			page.setRecords(list);
			result.setErrcode(0);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用DataServiceImpl.pageList报错：", e);
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
			String sql = "select * from m_micropage where id=" + pageID;
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
				
				sql = "select * from m_micropageset where micropageid=" + pageID + " order by showindex";
				if (isDraft) {
					sql = "select * from m_micropagesetdraft where micropageid=" + pageID + " order by showindex";
				}
				List<LinkedHashMap<String, Object>> setList = dataMapper.pageList(sql);
				if (setList != null && setList.size() > 0) {
					setList = CommonUtil.transformUpperCase(setList);
					List<String> setIDList = new ArrayList<>();
					
					for (int i = 0; i < setList.size(); i++) {
						setIDList.add(setList.get(i).get("id").toString().trim());
					}
					sql = "select * from m_micropagesetdtl where micropagesetid in (" + StringUtils.join(setIDList.toArray(), ",") + ") order by showindex";
					if (isDraft) {
						sql = "select * from m_micropagesetdtldraft where micropagesetid in (" + StringUtils.join(setIDList.toArray(), ",") + ") order by showindex";
					}
					List<LinkedHashMap<String, Object>> setDtlList = dataMapper.pageList(sql);
					/*
					// 分组
					List<Integer> groupIDList = new ArrayList<>();
					// 分类
					List<Integer> categoryIDList = new ArrayList<>();
					if (setDtlList != null && setDtlList.size() > 0) {
						setDtlList = CommonUtil.transformUpperCase(setDtlList);
						for (int i = 0; i < setDtlList.size(); i++) {
							String first = setDtlList.get(i).get("first") == null ? "" : setDtlList.get(i).get("first").toString().trim();
							String second = setDtlList.get(i).get("second") == null ? "" : setDtlList.get(i).get("second").toString().trim();
							String third = setDtlList.get(i).get("third") == null ? "" : setDtlList.get(i).get("third").toString().trim();
							if (!Common.isEmpty(first)) {
								groupIDList.add(Integer.valueOf(first));
								categoryIDList.add(Integer.valueOf(first));
							}
							if (!Common.isEmpty(second)) {
								categoryIDList.add(Integer.valueOf(second));
							}
							if (!Common.isEmpty(third)) {
								categoryIDList.add(Integer.valueOf(third));
							}
						}
					}
					List<MGoodsgrouping> goodsgroupings = new ArrayList<>();
					if (groupIDList.size() > 0) {
						MGoodsgroupingExample example = new MGoodsgroupingExample();
						example.createCriteria().andIdIn(groupIDList);
						goodsgroupings = goodsgroupingMapper.selectByExample(example);
					}
					List<MGoodscategory> goodscategories = new ArrayList<>();
					if (categoryIDList.size() > 0) {
						MGoodscategoryExample example = new MGoodscategoryExample();
						example.createCriteria().andIdIn(categoryIDList);
						goodscategories = goodscategoryMapper.selectByExample(example);
					}
					List<MGoodscategory> bigCategories = new ArrayList<>();
					List<MGoodscategory> middleCategories = new ArrayList<>();
					List<MGoodscategory> smallCategories = new ArrayList<>();
					for (int i = 0; i < goodscategories.size(); i++) {
						if (goodscategories.get(i).getGrade().intValue() == 1) {
							bigCategories.add(goodscategories.get(i));
						} else if (goodscategories.get(i).getGrade().intValue() == 2) {
							middleCategories.add(goodscategories.get(i));
						} else if (goodscategories.get(i).getGrade().intValue() == 3) {
							smallCategories.add(goodscategories.get(i));
						}
					}
					
					List<String> goodsidList = new ArrayList<>();
					for (int i = 0; i < setList.size(); i++) {
						String kind = setList.get(i).get("kind") == null ? "" : setList.get(i).get("kind").toString().trim();
						if ("9".equals(kind)) {
							String goodsid = setList.get(i).get("first") == null ? "" : setList.get(i).get("kind").toString().trim();
							if (!Common.isEmpty(goodsid)) {
								goodsidList.add(goodsid);
							}
						}
					}
					List<LinkedHashMap<String, Object>> goodsList = new ArrayList<>();
					if (goodsidList.size() > 0) {
						sql = "select * from m_goods where id in (" + StringUtils.join(goodsidList.toArray(), ",") + ") order by id";
						goodsList = dataMapper.pageList(sql);
						goodsList = CommonUtil.transformUpperCase(goodsList);
					}*/
					
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
									String grouping = "";
									String goodsname = "";
									long price = 0;
									Integer point = 0;
									Byte type = 1;
									String category = "";
									if (!Common.isEmpty(first)) {
										// 分组
										if ("7".equals(kind)) {
											MGoodsgrouping goodsgrouping = goodsgroupingMapper.selectByPrimaryKey(Integer.valueOf(first));
											if (goodsgrouping != null && goodsgrouping.getId() > 0) {
												grouping = goodsgrouping.getName();
											}
											/*for (int k = 0; k < goodsgroupings.size(); k++) {
												if (first.equals(goodsgroupings.get(k).getId().toString())) {	
													grouping = goodsgroupings.get(k).getName();
												}
											}*/
										}
										// 分类
										else if ("8".equals(kind)) {
											if (!Common.isEmpty(third)) {
												MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(third));
												if (goodscategory != null && goodscategory.getId() > 0) {
													category = goodscategory.getName();
												}
												/*for (int k = 0; k < smallCategories.size(); k++) {
													if (third.equals(smallCategories.get(k).getId().toString())) {
														category = smallCategories.get(k).getName();
													}
												}*/
											}
											if (!Common.isEmpty(second) && Common.isEmpty(category)) {
												MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(second));
												if (goodscategory != null && goodscategory.getId() > 0) {
													category = goodscategory.getName();
												}
												/*for (int k = 0; k < middleCategories.size(); k++) {
													if (second.equals(middleCategories.get(k).getId().toString())) {
														category = middleCategories.get(k).getName();
													}
												}*/
											}
											if (!Common.isEmpty(first) && Common.isEmpty(category)) {
												MGoodscategory goodscategory = goodscategoryMapper.selectByPrimaryKey(Integer.valueOf(first));
												if (goodscategory != null && goodscategory.getId() > 0) {
													category = goodscategory.getName();
												}
												/*for (int k = 0; k < bigCategories.size(); k++) {
													if (first.equals(bigCategories.get(k).getId().toString())) {
														category = bigCategories.get(k).getName();
													}
												}*/
											}
										}
										// 商品
										else if ("9".equals(kind)) {
											MGoods goods = goodsMapper.selectByPrimaryKey(Integer.valueOf(first));
											if (goods != null && goods.getId() > 0) {
												goodsname = goods.getName() == null ? "" : goods.getName();
												price = goods.getPrice() == null ? 0 : goods.getPrice();
												point = goods.getExchangepoint() == null ? 0 : goods.getExchangepoint();
												type = goods.getKind() == null ? 1 : goods.getKind();
												setDtlList.get(j).put("photourl", goods.getPhotourl() == null ? "" : goods.getPhotourl());
											}
											/*for (int k = 0; k < goodsList.size(); k++) {
												if (first.equals(goodsList.get(k).get("id").toString())) {
													goodsname = goodsList.get(k).get("name") == null ? "" : goodsList.get(k).get("name").toString();
													price = goodsList.get(k).get("price") == null ? "0" : goodsList.get(k).get("price").toString();
													point = goodsList.get(k).get("point") == null ? "0" : goodsList.get(k).get("point").toString();
													type = goodsList.get(k).get("kind") == null ? "1" : goodsList.get(k).get("kind").toString();
												}
											}*/
										}
									}
									setDtlList.get(j).put("grouping", grouping);
									setDtlList.get(j).put("category", category);
									setDtlList.get(j).put("goodsname", goodsname);
									setDtlList.get(j).put("price", price);
									setDtlList.get(j).put("point", point);
									setDtlList.get(j).put("type", type);
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
