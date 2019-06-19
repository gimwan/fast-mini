package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MMicropageExample;
import java.util.List;

public interface MMicropageMapper {
    int countByExample(MMicropageExample example);

    int deleteByExample(MMicropageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MMicropage record);

    int insertSelective(MMicropage record);

    List<MMicropage> selectByExample(MMicropageExample example);

    MMicropage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropage record);

    int updateByPrimaryKey(MMicropage record);
}