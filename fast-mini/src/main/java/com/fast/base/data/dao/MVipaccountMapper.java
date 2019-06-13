package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipaccountExample;
import java.util.List;

public interface MVipaccountMapper {
    int countByExample(MVipaccountExample example);

    int deleteByExample(MVipaccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MVipaccount record);

    int insertSelective(MVipaccount record);

    List<MVipaccount> selectByExample(MVipaccountExample example);

    MVipaccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipaccount record);

    int updateByPrimaryKey(MVipaccount record);
}