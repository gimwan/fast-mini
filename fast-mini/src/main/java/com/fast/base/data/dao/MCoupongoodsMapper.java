package com.fast.base.data.dao;

import com.fast.base.data.entity.MCoupongoods;
import com.fast.base.data.entity.MCoupongoodsExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MCoupongoodsMapper {
    long countByExample(MCoupongoodsExample example);

    int deleteByExample(MCoupongoodsExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MCoupongoods record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MCoupongoods record);

    List<MCoupongoods> selectByExample(MCoupongoodsExample example);

    MCoupongoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MCoupongoods record);

    int updateByPrimaryKey(MCoupongoods record);
}