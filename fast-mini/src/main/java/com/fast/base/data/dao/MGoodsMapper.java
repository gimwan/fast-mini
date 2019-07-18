package com.fast.base.data.dao;

import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import java.util.List;

public interface MGoodsMapper {
    int countByExample(MGoodsExample example);

    int deleteByExample(MGoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MGoods record);

    int insertSelective(MGoods record);

    List<MGoods> selectByExample(MGoodsExample example);

    MGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MGoods record);

    int updateByPrimaryKey(MGoods record);
}