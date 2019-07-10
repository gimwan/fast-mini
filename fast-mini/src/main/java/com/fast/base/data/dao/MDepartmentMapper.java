package com.fast.base.data.dao;

import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MDepartmentExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MDepartmentMapper {
    int countByExample(MDepartmentExample example);

    int deleteByExample(MDepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MDepartment record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MDepartment record);

    List<MDepartment> selectByExample(MDepartmentExample example);

    MDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MDepartment record);

    int updateByPrimaryKey(MDepartment record);
    
}