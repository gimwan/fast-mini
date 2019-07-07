package com.fast.base.data.dao;

import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MBrandMapper {
    int countByExample(MBrandExample example);

    int deleteByExample(MBrandExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MBrand record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MBrand record);

    List<MBrand> selectByExample(MBrandExample example);

    MBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MBrand record);

    int updateByPrimaryKey(MBrand record);
}