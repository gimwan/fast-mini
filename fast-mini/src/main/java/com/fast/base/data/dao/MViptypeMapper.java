package com.fast.base.data.dao;

import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MViptypeMapper {
    long countByExample(MViptypeExample example);

    int deleteByExample(MViptypeExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MViptype record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MViptype record);

    List<MViptype> selectByExample(MViptypeExample example);

    MViptype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MViptype record);

    int updateByPrimaryKey(MViptype record);
}