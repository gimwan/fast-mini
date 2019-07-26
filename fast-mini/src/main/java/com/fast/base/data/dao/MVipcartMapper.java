package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipcart;
import com.fast.base.data.entity.MVipcartExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVipcartMapper {
    int countByExample(MVipcartExample example);

    int deleteByExample(MVipcartExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVipcart record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVipcart record);

    List<MVipcart> selectByExample(MVipcartExample example);

    MVipcart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipcart record);

    int updateByPrimaryKey(MVipcart record);
}