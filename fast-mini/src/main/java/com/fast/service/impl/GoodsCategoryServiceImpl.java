package com.fast.service.impl;

import java.io.Serializable;
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

}
