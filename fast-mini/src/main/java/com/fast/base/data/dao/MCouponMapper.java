package com.fast.base.data.dao;

import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MCouponExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MCouponMapper {
    long countByExample(MCouponExample example);

    int deleteByExample(MCouponExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MCoupon record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MCoupon record);

    List<MCoupon> selectByExample(MCouponExample example);

    MCoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MCoupon record);

    int updateByPrimaryKey(MCoupon record);
}