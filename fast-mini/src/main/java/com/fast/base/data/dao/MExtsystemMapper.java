package com.fast.base.data.dao;

import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MExtsystemMapper {
    int countByExample(MExtsystemExample example);

    int deleteByExample(MExtsystemExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MExtsystem record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MExtsystem record);

    List<MExtsystem> selectByExample(MExtsystemExample example);

    MExtsystem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MExtsystem record);

    int updateByPrimaryKey(MExtsystem record);
}