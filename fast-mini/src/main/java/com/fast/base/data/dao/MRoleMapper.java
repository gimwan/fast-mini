package com.fast.base.data.dao;

import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MRoleExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MRoleMapper {
    int countByExample(MRoleExample example);

    int deleteByExample(MRoleExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true)
    int insert(MRole record);

    @Options(useGeneratedKeys = true)
    int insertSelective(MRole record);

    List<MRole> selectByExample(MRoleExample example);

    MRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MRole record);

    int updateByPrimaryKey(MRole record);
}