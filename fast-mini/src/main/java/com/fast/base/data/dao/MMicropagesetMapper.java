package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropageset;
import com.fast.base.data.entity.MMicropagesetExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MMicropagesetMapper {
    int countByExample(MMicropagesetExample example);

    int deleteByExample(MMicropagesetExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MMicropageset record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MMicropageset record);

    List<MMicropageset> selectByExample(MMicropagesetExample example);

    MMicropageset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropageset record);

    int updateByPrimaryKey(MMicropageset record);
}