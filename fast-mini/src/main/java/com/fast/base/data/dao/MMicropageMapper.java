package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MMicropageExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MMicropageMapper {
    int countByExample(MMicropageExample example);

    int deleteByExample(MMicropageExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MMicropage record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MMicropage record);

    List<MMicropage> selectByExample(MMicropageExample example);

    MMicropage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropage record);

    int updateByPrimaryKey(MMicropage record);
    
}