package com.fast.base.data.dao;

import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import java.util.List;

public interface MViptypeMapper {
    int countByExample(MViptypeExample example);

    int deleteByExample(MViptypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MViptype record);

    int insertSelective(MViptype record);

    List<MViptype> selectByExample(MViptypeExample example);

    MViptype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MViptype record);

    int updateByPrimaryKey(MViptype record);
}