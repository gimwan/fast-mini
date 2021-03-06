package com.fast.base.data.dao;

import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MPublicplatformExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MPublicplatformMapper {
    int countByExample(MPublicplatformExample example);

    int deleteByExample(MPublicplatformExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MPublicplatform record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MPublicplatform record);

    List<MPublicplatform> selectByExample(MPublicplatformExample example);

    MPublicplatform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MPublicplatform record);

    int updateByPrimaryKey(MPublicplatform record);
    
}