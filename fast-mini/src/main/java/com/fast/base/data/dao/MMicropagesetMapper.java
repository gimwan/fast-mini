package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropageset;
import com.fast.base.data.entity.MMicropagesetExample;
import java.util.List;

public interface MMicropagesetMapper {
    int countByExample(MMicropagesetExample example);

    int deleteByExample(MMicropagesetExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MMicropageset record);

    int insertSelective(MMicropageset record);

    List<MMicropageset> selectByExample(MMicropagesetExample example);

    MMicropageset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropageset record);

    int updateByPrimaryKey(MMicropageset record);
}