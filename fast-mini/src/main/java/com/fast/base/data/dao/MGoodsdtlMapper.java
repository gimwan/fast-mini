package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoodsdtl;
import com.fast.base.data.entity.MGoodsdtlExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGoodsdtlMapper {
    int countByExample(MGoodsdtlExample example);

    int deleteByExample(MGoodsdtlExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGoodsdtl record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGoodsdtl record);

    List<MGoodsdtl> selectByExample(MGoodsdtlExample example);

    MGoodsdtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoodsdtl record);

    int updateByPrimaryKey(MGoodsdtl record);
}