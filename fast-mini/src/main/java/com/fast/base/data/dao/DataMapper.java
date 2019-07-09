package com.fast.base.data.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fast.base.page.PagingView;

public interface DataMapper {
	
	List<LinkedHashMap<String, Object>> pageList(@Param("sql") String sql, @Param("page") PagingView page);
	
	List<LinkedHashMap<String, Object>> pageList(@Param("sql") String sql);

}
