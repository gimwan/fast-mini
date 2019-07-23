package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGoodsskuMapper {
    long countByExample(MGoodsskuExample example);

    int deleteByExample(MGoodsskuExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGoodssku record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGoodssku record);

    List<MGoodssku> selectByExample(MGoodsskuExample example);

    MGoodssku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoodssku record);

    int updateByPrimaryKey(MGoodssku record);
}