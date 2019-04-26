package com.fast.base.data.dao;

import com.fast.base.data.entity.MVipminidata;
import com.fast.base.data.entity.MVipminidataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MVipminidataMapper {
    long countByExample(MVipminidataExample example);

    int deleteByExample(MVipminidataExample example);

    int insert(MVipminidata record);

    int insertSelective(MVipminidata record);

    List<MVipminidata> selectByExampleWithBLOBs(MVipminidataExample example);

    List<MVipminidata> selectByExample(MVipminidataExample example);

    int updateByExampleSelective(@Param("record") MVipminidata record, @Param("example") MVipminidataExample example);

    int updateByExampleWithBLOBs(@Param("record") MVipminidata record, @Param("example") MVipminidataExample example);

    int updateByExample(@Param("record") MVipminidata record, @Param("example") MVipminidataExample example);
}