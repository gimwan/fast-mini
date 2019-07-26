package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipaddress;
import com.fast.base.data.entity.MVipaddressExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVipaddressMapper {
    long countByExample(MVipaddressExample example);

    int deleteByExample(MVipaddressExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVipaddress record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVipaddress record);

    List<MVipaddress> selectByExample(MVipaddressExample example);

    MVipaddress selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipaddress record);

    int updateByPrimaryKey(MVipaddress record);
}