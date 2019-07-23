package com.fast.base.data.dao;

import com.fast.base.data.entity.MOrderdtl;
import com.fast.base.data.entity.MOrderdtlExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MOrderdtlMapper {
    long countByExample(MOrderdtlExample example);

    int deleteByExample(MOrderdtlExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MOrderdtl record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MOrderdtl record);

    List<MOrderdtl> selectByExample(MOrderdtlExample example);

    MOrderdtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MOrderdtl record);

    int updateByPrimaryKey(MOrderdtl record);
}