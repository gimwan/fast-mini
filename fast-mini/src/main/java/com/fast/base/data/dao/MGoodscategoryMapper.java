package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGoodscategoryMapper {
    long countByExample(MGoodscategoryExample example);

    int deleteByExample(MGoodscategoryExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGoodscategory record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGoodscategory record);

    List<MGoodscategory> selectByExample(MGoodscategoryExample example);

    MGoodscategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoodscategory record);

    int updateByPrimaryKey(MGoodscategory record);
}