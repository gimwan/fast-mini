package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGoodsMapper {
    long countByExample(MGoodsExample example);

    int deleteByExample(MGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGoods record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGoods record);

    List<MGoods> selectByExample(MGoodsExample example);

    MGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoods record);

    int updateByPrimaryKey(MGoods record);
}