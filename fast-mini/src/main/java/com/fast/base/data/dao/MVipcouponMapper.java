package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipcouponExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MVipcouponMapper {
    int countByExample(MVipcouponExample example);

    int deleteByExample(MVipcouponExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MVipcoupon record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MVipcoupon record);

    List<MVipcoupon> selectByExample(MVipcouponExample example);

    MVipcoupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVipcoupon record);

    int updateByPrimaryKey(MVipcoupon record);
}