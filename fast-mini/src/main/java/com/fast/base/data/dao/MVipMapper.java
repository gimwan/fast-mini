package com.fast.base.data.dao;

import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import java.util.List;

public interface MVipMapper {
    int countByExample(MVipExample example);

    int deleteByExample(MVipExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MVip record);

    int insertSelective(MVip record);

    List<MVip> selectByExample(MVipExample example);

    MVip selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MVip record);

    int updateByPrimaryKey(MVip record);
}