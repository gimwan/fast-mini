package com.fast.base.data.dao;

import com.fast.base.data.entity.MLogistics;
import com.fast.base.data.entity.MLogisticsExample;
import java.util.List;

public interface MLogisticsMapper {
    int countByExample(MLogisticsExample example);

    int deleteByExample(MLogisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MLogistics record);

    int insertSelective(MLogistics record);

    List<MLogistics> selectByExample(MLogisticsExample example);

    MLogistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MLogistics record);

    int updateByPrimaryKey(MLogistics record);
}