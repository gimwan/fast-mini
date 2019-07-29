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
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.service.IGoodsCategoryService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.CommonUtil;

/**
 * 分类
 * @author J
 *
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result goodsCategory() {
		Result result = new Result();

		try {
			MGoodscategoryExample example = new MGoodscategoryExample();
			example.setOrderByClause("code asc");
			List<MGoodscategory> list = goodscategoryMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsCategoryServiceImpl.goodsCategory报错：", e);
		}

		return result;
	}

	@Override
	public Result list(PagingView page, Integer grade, Integer parentID) {
		Result result = new Result();

		try {
			page.setOrderBy("order by code,name");
			String sql = "select * from m_goodscategory where useflag=1";
			if (grade != null && grade.intValue() > 0) {
				sql = sql + " and grade=" + grade;
			}
			if (parentID != null && parentID.intValue() > 0) {
				sql = sql + " and parentid=" + parentID;
			}
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "goodscategory");
			page.setRecords(list);
			result.setErrcode(0);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsCategoryServiceImpl.list报错：", e);
		}

		return result;
	}

	@Override
	public Result category() {
		Result result = new Result();

		try {
			MGoodscategoryExample example = new MGoodscategoryExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andGradeNotEqualTo(Byte.valueOf("3"));
			List<MGoodscategory> list = goodscategoryMapper.selectByExample(example);
			List<HashMap<String, Object>> dataList = BeanUtil.toMapList(list);
			List<HashMap<String, Object>> big = new ArrayList<HashMap<String,Object>>();
			List<HashMap<String, Object>> middle = new ArrayList<HashMap<String,Object>>();
			for (int i = 0; i < dataList.size(); i++) {
				Integer grade = Integer.valueOf(dataList.get(i).get("grade").toString());
				if (grade.intValue() == 1) {
					big.add(dataList.get(i));
				} else if (grade.intValue() == 2) {
					middle.add(dataList.get(i));
				}
			}
			for (int i = 0; i < big.size(); i++) {
				Integer id = Integer.valueOf(big.get(i).get("id").toString());
				List<HashMap<String, Object>> childs = new ArrayList<HashMap<String,Object>>();
				for (int j = 0; j < middle.size(); j++) {
					Integer parentid = Integer.valueOf(middle.get(j).get("parentid").toString());
					if (id.intValue() == parentid.intValue()) {
						childs.add(middle.get(j));
					}
				}
				big.get(i).put("childs", childs);
				Integer choose = 0;
				if (i == 0) {
					choose = 1;
				}
				big.get(i).put("choose", choose);
			}
			result.setData(big);
			result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用GoodsCategoryServiceImpl.category报错：", e);
		}

		return result;
	}

}
