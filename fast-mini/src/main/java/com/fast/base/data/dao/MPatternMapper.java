package com.fast.base.data.dao;

import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MPatternExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MPatternMapper {
    int countByExample(MPatternExample example);

    int deleteByExample(MPatternExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MPattern record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MPattern record);

    List<MPattern> selectByExample(MPatternExample example);

    MPattern selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MPattern record);

    int updateByPrimaryKey(MPattern record);
}