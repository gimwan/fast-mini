package com.fast.base.data.dao;

import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MConfigExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MConfigMapper {
    int countByExample(MConfigExample example);

    int deleteByExample(MConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MConfig record);

    int insertSelective(MConfig record);

    List<MConfig> selectByExample(MConfigExample example);

    MConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MConfig record);

    int updateByPrimaryKey(MConfig record);
}