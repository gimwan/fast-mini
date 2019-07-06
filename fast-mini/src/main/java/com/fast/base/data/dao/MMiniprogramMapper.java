package com.fast.base.data.dao;

import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MMiniprogramExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MMiniprogramMapper {
    int countByExample(MMiniprogramExample example);

    int deleteByExample(MMiniprogramExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MMiniprogram record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MMiniprogram record);

    List<MMiniprogram> selectByExample(MMiniprogramExample example);

    MMiniprogram selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMiniprogram record);

    int updateByPrimaryKey(MMiniprogram record);
}