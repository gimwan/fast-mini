package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropagesetdtldraft;
import com.fast.base.data.entity.MMicropagesetdtldraftExample;
import java.util.List;

public interface MMicropagesetdtldraftMapper {
    int countByExample(MMicropagesetdtldraftExample example);

    int deleteByExample(MMicropagesetdtldraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MMicropagesetdtldraft record);

    int insertSelective(MMicropagesetdtldraft record);

    List<MMicropagesetdtldraft> selectByExample(MMicropagesetdtldraftExample example);

    MMicropagesetdtldraft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropagesetdtldraft record);

    int updateByPrimaryKey(MMicropagesetdtldraft record);
}