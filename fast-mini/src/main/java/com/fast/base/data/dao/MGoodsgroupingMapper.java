package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoodsgrouping;
import com.fast.base.data.entity.MGoodsgroupingExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGoodsgroupingMapper {
    int countByExample(MGoodsgroupingExample example);

    int deleteByExample(MGoodsgroupingExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGoodsgrouping record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGoodsgrouping record);

    List<MGoodsgrouping> selectByExample(MGoodsgroupingExample example);

    MGoodsgrouping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoodsgrouping record);

    int updateByPrimaryKey(MGoodsgrouping record);
}