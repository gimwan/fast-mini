package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVipminiMapper {
    long countByExample(MVipminiExample example);

    int deleteByExample(MVipminiExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVipmini record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVipmini record);

    List<MVipmini> selectByExample(MVipminiExample example);

    MVipmini selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipmini record);

    int updateByPrimaryKey(MVipmini record);
}