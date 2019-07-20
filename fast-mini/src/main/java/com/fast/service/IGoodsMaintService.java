package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MUser;

/**
 * 商品
 * @author J
 *
 */
public interface IGoodsMaintService {
	
	/**
	 * 修改商品
	 * @param goods
	 * @param user
	 * @return
	 */
	public Result changeGoods(MGoods goods, MUser user);
	
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	public Result deleteGoods(Integer id);

}
