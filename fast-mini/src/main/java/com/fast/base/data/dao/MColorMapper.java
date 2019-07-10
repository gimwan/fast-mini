package com.fast.base.data.dao;

import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MColorMapper {
    int countByExample(MColorExample example);

    int deleteByExample(MColorExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MColor record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MColor record);

    List<MColor> selectByExample(MColorExample example);

    MColor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MColor record);

    int updateByPrimaryKey(MColor record);
    
}