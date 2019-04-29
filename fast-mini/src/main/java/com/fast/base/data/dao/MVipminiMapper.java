package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import java.util.List;

public interface MVipminiMapper {
    long countByExample(MVipminiExample example);

    int deleteByExample(MVipminiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MVipmini record);

    int insertSelective(MVipmini record);

    List<MVipmini> selectByExample(MVipminiExample example);

    MVipmini selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipmini record);

    int updateByPrimaryKey(MVipmini record);
}