package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropagesetdraft;
import com.fast.base.data.entity.MMicropagesetdraftExample;
import java.util.List;

public interface MMicropagesetdraftMapper {
    int countByExample(MMicropagesetdraftExample example);

    int deleteByExample(MMicropagesetdraftExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MMicropagesetdraft record);

    int insertSelective(MMicropagesetdraft record);

    List<MMicropagesetdraft> selectByExample(MMicropagesetdraftExample example);

    MMicropagesetdraft selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropagesetdraft record);

    int updateByPrimaryKey(MMicropagesetdraft record);
}