package com.fast.base.data.dao;

import com.fast.base.data.entity.MOrder;
import com.fast.base.data.entity.MOrderExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MOrderMapper {
    int countByExample(MOrderExample example);

    int deleteByExample(MOrderExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MOrder record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MOrder record);

    List<MOrder> selectByExampleWithBLOBs(MOrderExample example);

    List<MOrder> selectByExample(MOrderExample example);

    MOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MOrder record);

    int updateByPrimaryKeyWithBLOBs(MOrder record);

    int updateByPrimaryKey(MOrder record);
}