package com.fast.base.data.dao;

import com.fast.base.data.entity.MVippointrecord;
import com.fast.base.data.entity.MVippointrecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVippointrecordMapper {
    int countByExample(MVippointrecordExample example);

    int deleteByExample(MVippointrecordExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVippointrecord record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVippointrecord record);

    List<MVippointrecord> selectByExample(MVippointrecordExample example);

    MVippointrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVippointrecord record);

    int updateByPrimaryKey(MVippointrecord record);
}