package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipdepositrecord;
import com.fast.base.data.entity.MVipdepositrecordExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVipdepositrecordMapper {
    int countByExample(MVipdepositrecordExample example);

    int deleteByExample(MVipdepositrecordExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVipdepositrecord record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVipdepositrecord record);

    List<MVipdepositrecord> selectByExample(MVipdepositrecordExample example);

    MVipdepositrecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipdepositrecord record);

    int updateByPrimaryKey(MVipdepositrecord record);
}