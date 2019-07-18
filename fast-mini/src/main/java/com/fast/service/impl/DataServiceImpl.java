package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.dao.MDepartmentMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MDepartmentExample;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MPublicplatformExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

@Service
public class DataServiceImpl implements IDataService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	MDepartmentMapper departmentMapper;
	
	@Autowired
	MPublicplatformMapper publicplatformMapper;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MBrandMapper brandMapper;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;

	@Override
	public Result pageList(PagingView page, String tableName) {
		Result result = new Result();

		try {
			page.setOrderBy("order by code,name");
			String sql = "select * from " + tableName;
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);
			list = CommonUtil.transformUpperCase(list);
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
	public Result list(PagingView page, String tableName) {
		Result result = new Result();

		try {
			page.setOrderBy("order by code,name");
			String sql = "select * from " + tableName;
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);
			list = CommonUtil.transformUpperCase(list);
			list = completeData(list, tableName.replace("m_", ""));
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
	public List<LinkedHashMap<String, Object>> completeData(List<LinkedHashMap<String, Object>> list, String tableName) {		
		if ("employee".equals(tableName)) {
			List<Integer> idList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("department", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("departmentid")))) {
					idList.add(Integer.valueOf(list.get(i).get("departmentid").toString()));
				} else {
					list.get(i).put("departmentid", "");
				}
			}
			if (idList.size() > 0) {
				MDepartmentExample example = new MDepartmentExample();
				example.createCriteria().andIdIn(idList);
				List<MDepartment> data = departmentMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (list.get(i).get("departmentid").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("department", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if ("".equals(list.get(i).get("department").toString())) {
					list.get(i).put("departmentid", "");
				}
			}
		}
		else if ("miniprogram".equals(tableName)) {
			List<Integer> idList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("publicplatform", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("publicplatformid")))) {
					idList.add(Integer.valueOf(list.get(i).get("publicplatformid").toString()));
				} else {
					list.get(i).put("publicplatformid", "");
				}
			}
			if (idList.size() > 0) {
				MPublicplatformExample example = new MPublicplatformExample();
				example.createCriteria().andIdIn(idList);
				List<MPublicplatform> data = publicplatformMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (list.get(i).get("publicplatformid").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("publicplatform", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if ("".equals(list.get(i).get("publicplatform").toString())) {
					list.get(i).put("publicplatformid", "");
				}
			}
		}
		else if ("vip".equals(tableName)) {
			List<Integer> idList = new ArrayList<>();
			List<Integer> typeidList = new ArrayList<>();
			List<Integer> recommenderidList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("department", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("departmentid")))) {
					idList.add(Integer.valueOf(list.get(i).get("departmentid").toString()));
				} else {
					list.get(i).put("departmentid", "");
				}
				list.get(i).put("type", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("typeid")))) {
					typeidList.add(Integer.valueOf(list.get(i).get("typeid").toString()));
				} else {
					list.get(i).put("typeid", "");
				}
				list.get(i).put("recommender", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("recommenderid")))) {
					recommenderidList.add(Integer.valueOf(list.get(i).get("recommenderid").toString()));
				} else {
					list.get(i).put("recommenderid", "");
				}
				
			}
			if (idList.size() > 0) {
				MDepartmentExample example = new MDepartmentExample();
				example.createCriteria().andIdIn(idList);
				List<MDepartment> data = departmentMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (list.get(i).get("departmentid").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("department", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
			if (typeidList.size() > 0) {
				MViptypeExample example = new MViptypeExample();
				example.createCriteria().andIdIn(typeidList);
				List<MViptype> data = viptypeMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (list.get(i).get("typeid").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("type", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
			if (recommenderidList.size() > 0) {
				MVipExample example = new MVipExample();
				example.createCriteria().andIdIn(recommenderidList);
				List<MVip> data = vipMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (list.get(i).get("recommenderid").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("recommender", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if ("".equals(list.get(i).get("department").toString())) {
					list.get(i).put("departmentid", "");
				}
				if ("".equals(list.get(i).get("type").toString())) {
					list.get(i).put("typeid", "");
				}
				if ("".equals(list.get(i).get("recommender").toString())) {
					list.get(i).put("recommenderid", "");
				}
			}
		}
		else if ("goods".equals(tableName)) {
			List<Integer> brandidList = new ArrayList<>();
			List<Integer> categoryidList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("brand", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("brandid")))) {
					brandidList.add(Integer.valueOf(list.get(i).get("brandid").toString()));
				} else {
					list.get(i).put("brandid", "");
				}
				list.get(i).put("bigcategoryname", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("bigcategory")))) {
					categoryidList.add(Integer.valueOf(list.get(i).get("bigcategory").toString()));
				} else {
					list.get(i).put("bigcategory", "");
				}
				list.get(i).put("middlecategoryname", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("middlecategory")))) {
					categoryidList.add(Integer.valueOf(list.get(i).get("middlecategory").toString()));
				} else {
					list.get(i).put("middlecategory", "");
				}
				list.get(i).put("smallcategoryname", "");
				if (!Common.isEmpty(String.valueOf(list.get(i).get("smallcategory")))) {
					categoryidList.add(Integer.valueOf(list.get(i).get("smallcategory").toString()));
				} else {
					list.get(i).put("smallcategory", "");
				}
			}
			if (brandidList.size() > 0) {
				MBrandExample example = new MBrandExample();
				example.createCriteria().andIdIn(brandidList);
				List<MBrand> data = brandMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (list.get(i).get("brandid").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("brand", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
			if (categoryidList.size() > 0) {
				MGoodscategoryExample example = new MGoodscategoryExample();
				example.createCriteria().andIdIn(categoryidList);
				List<MGoodscategory> data = goodscategoryMapper.selectByExample(example);
				if (data != null && data.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						for (int j = 0; j < data.size(); j++) {
							if (data.get(j).getGrade().intValue() == 1 && list.get(i).get("bigcategory").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("bigcategoryname", data.get(j).getName());
								break;
							} else if (data.get(j).getGrade().intValue() == 2 && list.get(i).get("middlecategory").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("middlecategoryname", data.get(j).getName());
								break;
							} else if (data.get(j).getGrade().intValue() == 3 && list.get(i).get("smallcategory").toString().equals(data.get(j).getId().toString())) {
								list.get(i).put("smallcategoryname", data.get(j).getName());
								break;
							}
						}
					}
				}
			}
		}
		
		return list;
	}
	
	@Override
	public Result one(String tableName, Integer id) {
		Result result = new Result();

		try {
			String sql = "select * from m_" + tableName + " where id=" + id;
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				list = completeData(list, tableName);
				result.setErrcode(0);
				result.setData(list.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用DataServiceImpl.one报错：", e);
		}

		return result;
	}

}
