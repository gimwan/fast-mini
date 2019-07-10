package com.fast.base.data.dao;

import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVipMapper {
    int countByExample(MVipExample example);

    int deleteByExample(MVipExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVip record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVip record);

    List<MVip> selectByExample(MVipExample example);

    MVip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVip record);

    int updateByPrimaryKey(MVip record);
    
}