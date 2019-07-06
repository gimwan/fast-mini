package com.fast.base.data.dao;

import com.fast.base.data.entity.MMicropagesetdtl;
import com.fast.base.data.entity.MMicropagesetdtlExample;
import java.util.List;

import org.apache.ibatis.annotations.Options;

public interface MMicropagesetdtlMapper {
    int countByExample(MMicropagesetdtlExample example);

    int deleteByExample(MMicropagesetdtlExample example);

    int deleteByPrimaryKey(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(MMicropagesetdtl record);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSelective(MMicropagesetdtl record);

    List<MMicropagesetdtl> selectByExample(MMicropagesetdtlExample example);

    MMicropagesetdtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MMicropagesetdtl record);

    int updateByPrimaryKey(MMicropagesetdtl record);
}