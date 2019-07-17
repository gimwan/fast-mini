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
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MGoodsgroupingMapper;
import com.fast.base.data.dao.MMicropageMapper;
import com.fast.base.data.dao.MMicropagesetMapper;
import com.fast.base.data.dao.MMicropagesetdtlMapper;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MGoodsgroupingExample;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MMicropageExample;
import com.fast.base.data.entity.MMicropageset;
import com.fast.base.data.entity.MMicropagesetExample;
import com.fast.base.data.entity.MMicropagesetdtl;
import com.fast.base.data.entity.MMicropagesetdtlExample;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MPublicplatform;
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
			
			result = queryPageData(micropage.getId());
			/*List<HashMap<String, Object>> microData = new ArrayList<>();
			MMicropagesetExample example = new MMicropagesetExample();
			example.createCriteria().andMicropageidEqualTo(micropage.getId());
			example.setOrderByClause("showindex asc");
			List<MMicropageset> list = micropagesetMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				List<Integer> idList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					idList.add(list.get(i).getId());
				}
				MMicropagesetdtlExample dtlExample = new MMicropagesetdtlExample();
				dtlExample.createCriteria().andMicropagesetidIn(idList);
				dtlExample.setOrderByClause("showindex asc");
				List<MMicropagesetdtl> dtlList = micropagesetdtlMapper.selectByExample(dtlExample);
				
				for (int i = 0; i < list.size(); i++) {
					MMicropageset micropageset = list.get(i);
					HashMap<String, Object> map = new HashMap<>();
					map.put("id", micropageset.getId());
					map.put("kind", micropageset.getKind());
					map.put("index", micropageset.getShowindex());
					map.put("showname", micropageset.getShowname());
					map.put("showprice", micropageset.getShowprice());
					map.put("imagestyle", micropageset.getImagestyle());
					map.put("orderby", micropageset.getOrderby());
					
					List<HashMap<String, Object>> childsList = new ArrayList<>();
					for (int j = 0; j < dtlList.size(); j++) {
						MMicropagesetdtl micropagesetdtl = dtlList.get(j);
						if (micropagesetdtl.getMicropagesetid().intValue() == micropageset.getId().intValue()) {
							HashMap<String, Object> childMap = new HashMap<String, Object>();
							childMap.put("id", micropagesetdtl.getId());
							childMap.put("index", micropagesetdtl.getShowindex());
							childMap.put("first", micropagesetdtl.getFirst());
							childMap.put("second", micropagesetdtl.getSecond());
							childMap.put("third", micropagesetdtl.getThird());
							childMap.put("text", micropagesetdtl.getText());
							childMap.put("photourl", micropagesetdtl.getPhotourl());
							childMap.put("targetpath", micropagesetdtl.getTargetpath());
							childMap.put("type", micropagesetdtl.getType());
							childsList.add(childMap);
						}
					}
					
					map.put("childs", childsList);
					
					microData.add(map);
				}
			}*/
			
			//result.setErrcode(Integer.valueOf(0));
			//result.setData(microData);
			result.setId(micropage.getId());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageServiceImpl.micropage报错：", e);
		}

		return result;
	}

	@Override
	public Result queryPageData(Integer pageID) {
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
				
				sql = "select * from m_micropagesetdraft where micropageid=" + pageID + " order by showindex";
				List<LinkedHashMap<String, Object>> setList = dataMapper.pageList(sql);
				if (setList != null && setList.size() > 0) {
					setList = CommonUtil.transformUpperCase(setList);
					List<String> setIDList = new ArrayList<>();
					
					for (int i = 0; i < setList.size(); i++) {
						setIDList.add(setList.get(i).get("id").toString().trim());
					}
					sql = "select * from m_micropagesetdtldraft where micropagesetid in (" + StringUtils.join(setIDList.toArray(), ",") + ") order by showindex";
					List<LinkedHashMap<String, Object>> setDtlList = dataMapper.pageList(sql);					
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
					
					for (int i = 0; i < setList.size(); i++) {
						LinkedHashMap<String, Object> setMap = setList.get(i);
						// 1广告 2搜索 3导航 4公告 5栏目标题 6辅助空白 7商品分组 8商品分类 9商品列表
						String kind = setMap.get("kind") == null ? "" : setMap.get("kind").toString().trim();
						List<LinkedHashMap<String, Object>> dtlList = new ArrayList<>();
						if (setDtlList != null && setDtlList.size() > 0) {
							for (int j = 0; j < setDtlList.size(); j++) {
								if (setMap.get("id").toString().trim().equals(setDtlList.get(j).get("micropagesetid").toString().trim())) {
									String first = setDtlList.get(j).get("first") == null ? "" : setDtlList.get(j).get("first").toString().trim();
									String second = setDtlList.get(j).get("second") == null ? "" : setDtlList.get(j).get("second").toString().trim();
									String third = setDtlList.get(j).get("third") == null ? "" : setDtlList.get(j).get("third").toString().trim();
									String grouping = "";
									String category = "";
									if (!Common.isEmpty(first)) {
										// 分组
										if ("7".equals(kind)) {
											for (int k = 0; k < goodsgroupings.size(); k++) {
												if (first.equals(goodsgroupings.get(k).getId().toString())) {
													grouping = goodsgroupings.get(k).getName();
												}
											}
										}
										// 分类
										else if ("8".equals(kind)) {
											if (!Common.isEmpty(third)) {
												for (int k = 0; k < goodscategories.size(); k++) {
													if (third.equals(goodscategories.get(k).getId().toString())) {
														category = goodscategories.get(k).getName();
													}
												}
											}
											if (!Common.isEmpty(second) && Common.isEmpty(category)) {
												for (int k = 0; k < goodscategories.size(); k++) {
													if (second.equals(goodscategories.get(k).getId().toString())) {
														category = goodscategories.get(k).getName();
													}
												}
											}
											if (!Common.isEmpty(first) && Common.isEmpty(category)) {
												for (int k = 0; k < goodscategories.size(); k++) {
													if (first.equals(goodscategories.get(k).getId().toString())) {
														category = goodscategories.get(k).getName();
													}
												}
											}
										}
									}
									setDtlList.get(j).put("grouping", grouping);
									setDtlList.get(j).put("category", category);
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
