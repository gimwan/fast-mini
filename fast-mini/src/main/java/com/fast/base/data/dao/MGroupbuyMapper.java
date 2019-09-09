package com.fast.base.data.dao;

import com.fast.base.data.entity.MGroupbuy;
import com.fast.base.data.entity.MGroupbuyExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGroupbuyMapper {
    int countByExample(MGroupbuyExample example);

    int deleteByExample(MGroupbuyExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGroupbuy record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGroupbuy record);

    List<MGroupbuy> selectByExample(MGroupbuyExample example);

    MGroupbuy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGroupbuy record);

    int updateByPrimaryKey(MGroupbuy record);
}