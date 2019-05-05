package com.fast.base.data.dao;

import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MMiniprogramExample;
import java.util.List;

public interface MMiniprogramMapper {
    int countByExample(MMiniprogramExample example);

    int deleteByExample(MMiniprogramExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MMiniprogram record);

    int insertSelective(MMiniprogram record);

    List<MMiniprogram> selectByExample(MMiniprogramExample example);

    MMiniprogram selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMiniprogram record);

    int updateByPrimaryKey(MMiniprogram record);
}