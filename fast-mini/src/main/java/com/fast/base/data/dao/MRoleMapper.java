package com.fast.base.data.dao;

import com.fast.base.data.entity.MRole;
import com.fast.base.data.entity.MRoleExample;
import java.util.List;

public interface MRoleMapper {
    int countByExample(MRoleExample example);

    int deleteByExample(MRoleExample example);

    int insert(MRole record);

    int insertSelective(MRole record);

    List<MRole> selectByExample(MRoleExample example);
}