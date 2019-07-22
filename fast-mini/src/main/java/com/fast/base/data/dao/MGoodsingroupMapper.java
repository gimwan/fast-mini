package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoodsingroup;
import com.fast.base.data.entity.MGoodsingroupExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MGoodsingroupMapper {
    int countByExample(MGoodsingroupExample example);

    int deleteByExample(MGoodsingroupExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MGoodsingroup record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MGoodsingroup record);

    List<MGoodsingroup> selectByExample(MGoodsingroupExample example);

    MGoodsingroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoodsingroup record);

    int updateByPrimaryKey(MGoodsingroup record);
}