package com.fast.base.data.dao;

import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MSizeExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MSizeMapper {
    int countByExample(MSizeExample example);

    int deleteByExample(MSizeExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MSize record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MSize record);

    List<MSize> selectByExample(MSizeExample example);

    MSize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MSize record);

    int updateByPrimaryKey(MSize record);
    
}