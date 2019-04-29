package com.fast.base.data.dao;

import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MUserExample;
import java.util.List;

public interface MUserMapper {
    long countByExample(MUserExample example);

    int deleteByExample(MUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MUser record);

    int insertSelective(MUser record);

    List<MUser> selectByExample(MUserExample example);

    MUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MUser record);

    int updateByPrimaryKey(MUser record);
}