package com.fast.base.data.dao;

import com.fast.base.data.entity.MGroupbuydtl;
import com.fast.base.data.entity.MGroupbuydtlExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGroupbuydtlMapper {
    int countByExample(MGroupbuydtlExample example);

    int deleteByExample(MGroupbuydtlExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGroupbuydtl record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGroupbuydtl record);

    List<MGroupbuydtl> selectByExample(MGroupbuydtlExample example);

    MGroupbuydtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGroupbuydtl record);

    int updateByPrimaryKey(MGroupbuydtl record);
}