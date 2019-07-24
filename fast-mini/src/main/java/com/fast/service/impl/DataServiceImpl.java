package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.dao.MDepartmentMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MPatternMapper;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.dao.MSizeMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MDepartmentExample;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MPatternExample;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MPublicplatformExample;
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MSizeExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipaccountExample;
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
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	MBrandMapper brandMapper;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	MColorMapper colorMapper;
	
	@Autowired
	MPatternMapper patternMapper;
	
	@Autowired
	MSizeMapper sizeMapper;

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
			list = completeEmployee(list);
		}
		else if ("miniprogram".equals(tableName)) {
			list = completeMiniprogram(list);
		}
		else if ("vip".equals(tableName)) {
			list = completeVip(list);
		}
		else if ("goods".equals(tableName)) {
			list = completeGoods(list);
		}
		else if ("goodssku".equals(tableName)) {
			list = completeGoodsSku(list);
		}
		else if ("order".equals(tableName)) {
			list = completeOrder(list);
		}
		else if ("department".endsWith(tableName)) {
			list = completeDepartment(list);
		}
		
		return list;
	}
	
	/**
	 * 补全employee
	 * @param list
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> completeEmployee(List<LinkedHashMap<String, Object>> list) {
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
	
		return list;
	}
	
	/**
	 * 补全miniprogram
	 * @param list
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> completeMiniprogram(List<LinkedHashMap<String, Object>> list) {
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
		
		return list;
	}
	
	/**
	 * 补全vip
	 * @param list
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> completeVip(List<LinkedHashMap<String, Object>> list) {
		List<Integer> idList = new ArrayList<>();
		List<Integer> departmentidList = new ArrayList<>();
		List<Integer> typeidList = new ArrayList<>();
		List<Integer> recommenderidList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("department", "");
			if (!Common.isEmpty(String.valueOf(list.get(i).get("departmentid")))) {
				departmentidList.add(Integer.valueOf(list.get(i).get("departmentid").toString()));
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
			idList.add(Integer.valueOf(list.get(i).get("id").toString()));
			list.get(i).put("deposit", BigDecimal.ZERO);
			list.get(i).put("point", 0);
		}
		if (idList.size() > 0) {
			MVipaccountExample example = new MVipaccountExample();
			example.createCriteria().andIdIn(idList);
			List<MVipaccount> data = vipaccountMapper.selectByExample(example);
			if (data != null && data.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < data.size(); j++) {
						if (list.get(i).get("id").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("deposit", data.get(j).getDeposit()==null?BigDecimal.ZERO:data.get(j).getDeposit());
							list.get(i).put("point", data.get(j).getPoint()==null?0:data.get(j).getPoint());
							break;
						}
					}
				}
			}
		}
		if (departmentidList.size() > 0) {
			MDepartmentExample example = new MDepartmentExample();
			example.createCriteria().andIdIn(departmentidList);
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
		
		return list;
	}
	
	/**
	 * 补全goods
	 * @param list
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> completeGoods(List<LinkedHashMap<String, Object>> list) {
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
						}
						if (data.get(j).getGrade().intValue() == 2 && list.get(i).get("middlecategory").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("middlecategoryname", data.get(j).getName());
						}
						if (data.get(j).getGrade().intValue() == 3 && list.get(i).get("smallcategory").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("smallcategoryname", data.get(j).getName());
						}
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if ("".equals(list.get(i).get("brand").toString())) {
				list.get(i).put("brandid", "");
			}
			if ("".equals(list.get(i).get("bigcategoryname").toString())) {
				list.get(i).put("bigcategory", "");
			}
			if ("".equals(list.get(i).get("middlecategoryname").toString())) {
				list.get(i).put("middlecategory", "");
			}
			if ("".equals(list.get(i).get("smallcategoryname").toString())) {
				list.get(i).put("smallcategory", "");
			}
		}
		return list;
	}
	
	/**
	 * 补全goodssku
	 * @param list
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> completeGoodsSku(List<LinkedHashMap<String, Object>> list) {
		List<Integer> coloridList = new ArrayList<>();
		List<Integer> patternidList = new ArrayList<>();
		List<Integer> sizeidList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("color", "");
			if (!Common.isEmpty(String.valueOf(list.get(i).get("colorid")))) {
				coloridList.add(Integer.valueOf(list.get(i).get("colorid").toString()));
			} else {
				list.get(i).put("colorid", "");
			}
			list.get(i).put("pattern", "");
			if (!Common.isEmpty(String.valueOf(list.get(i).get("patternid")))) {
				patternidList.add(Integer.valueOf(list.get(i).get("patternid").toString()));
			} else {
				list.get(i).put("patternid", "");
			}
			list.get(i).put("size", "");
			if (!Common.isEmpty(String.valueOf(list.get(i).get("sizeid")))) {
				sizeidList.add(Integer.valueOf(list.get(i).get("sizeid").toString()));
			} else {
				list.get(i).put("sizeid", "");
			}
		}
		if (coloridList.size() > 0) {
			MColorExample example = new MColorExample();
			example.createCriteria().andIdIn(coloridList);
			List<MColor> data = colorMapper.selectByExample(example);
			if (data != null && data.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < data.size(); j++) {
						if (list.get(i).get("colorid").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("color", data.get(j).getName());
							break;
						}
					}
				}
			}
		}
		if (patternidList.size() > 0) {
			MPatternExample example = new MPatternExample();
			example.createCriteria().andIdIn(patternidList);
			List<MPattern> data = patternMapper.selectByExample(example);
			if (data != null && data.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < data.size(); j++) {
						if (list.get(i).get("patternid").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("pattern", data.get(j).getName());
							break;
						}
					}
				}
			}
		}
		if (sizeidList.size() > 0) {
			MSizeExample example = new MSizeExample();
			example.createCriteria().andIdIn(sizeidList);
			List<MSize> data = sizeMapper.selectByExample(example);
			if (data != null && data.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < data.size(); j++) {
						if (list.get(i).get("sizeid").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("size", data.get(j).getName());
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if ("".equals(list.get(i).get("color").toString())) {
				list.get(i).put("colorid", "");
			}
			if ("".equals(list.get(i).get("pattern").toString())) {
				list.get(i).put("patternid", "");
			}
			if ("".equals(list.get(i).get("size").toString())) {
				list.get(i).put("sizeid", "");
			}
		}
		return list;
	}
	
	/**
	 * 补全order
	 * @param list
	 * @return
	 */
	public List<LinkedHashMap<String, Object>> completeOrder(List<LinkedHashMap<String, Object>> list) {
		List<Integer> vipidList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).put("vip", "");
			list.get(i).put("vipphone", "");
			if (!Common.isEmpty(String.valueOf(list.get(i).get("vipid")))) {
				vipidList.add(Integer.valueOf(list.get(i).get("vipid").toString()));
			} else {
				list.get(i).put("vip", "");
				list.get(i).put("vipphone", "");
			}
		}
		if (vipidList.size() > 0) {
			MVipExample example = new MVipExample();
			example.createCriteria().andIdIn(vipidList);
			List<MVip> data = vipMapper.selectByExample(example);
			if (data != null && data.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < data.size(); j++) {
						if (list.get(i).get("vipid").toString().equals(data.get(j).getId().toString())) {
							list.get(i).put("vip", data.get(j).getName());
							list.get(i).put("vipphone", data.get(j).getMobilephone());
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if ("".equals(list.get(i).get("vip").toString())) {
				list.get(i).put("vipid", "");
			}
			// 明细
			String detailSql = "select a.*,b.code,b.name,b.photourl,c.name as color,d.name as pattern,e.name as size "
					+ "from m_orderdtl a "
					+ "inner join m_goods b on a.goodsid=b.id "
					+ "left join m_color c on a.colorid=c.id "
					+ "left join m_pattern d on a.patternid=d.id "
					+ "left join m_size e on a.sizeid=e.id "
					+ "where a.orderid="+list.get(i).get("id").toString();
			List<LinkedHashMap<String, Object>> dtlList = dataMapper.pageList(detailSql);
			dtlList = CommonUtil.transformUpperCase(dtlList);
			list.get(i).put("details", dtlList);
		}
		
		
		return list;
	}
	
	public List<LinkedHashMap<String, Object>> completeDepartment(List<LinkedHashMap<String, Object>> list) {
		
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
