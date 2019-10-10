package com.fast.base.data.dao;

import com.fast.base.data.entity.MCoupondepartment;
import com.fast.base.data.entity.MCoupondepartmentExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MCoupondepartmentMapper {
    long countByExample(MCoupondepartmentExample example);

    int deleteByExample(MCoupondepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MCoupondepartment record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MCoupondepartment record);

    List<MCoupondepartment> selectByExample(MCoupondepartmentExample example);

    MCoupondepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MCoupondepartment record);

    int updateByPrimaryKey(MCoupondepartment record);
}