package com.fast.base.data.dao;

import com.fast.base.data.entity.MRegion;
import com.fast.base.data.entity.MRegionExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MRegionMapper {
    int countByExample(MRegionExample example);

    int deleteByExample(MRegionExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MRegion record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MRegion record);

    List<MRegion> selectByExample(MRegionExample example);

    MRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MRegion record);

    int updateByPrimaryKey(MRegion record);
}