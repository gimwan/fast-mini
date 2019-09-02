package com.fast.base.data.dao;

import com.fast.base.data.entity.MEmployee;
import com.fast.base.data.entity.MEmployeeExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MEmployeeMapper {
    int countByExample(MEmployeeExample example);

    int deleteByExample(MEmployeeExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MEmployee record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MEmployee record);

    List<MEmployee> selectByExample(MEmployeeExample example);

    MEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MEmployee record);

    int updateByPrimaryKey(MEmployee record);
}