package com.fast.service.ext;

import com.fast.base.Result;
import com.fast.base.data.entity.MExtsystem;

/**
 * 外部接口
 * @author J
 *
 */
public interface IExtMaintService {
	
	/**
	 * 同步数据
	 * @param type
	 * @return
	 */
	public Result synchronize(String type);
	
	/**
	 * 同步商品数据
	 * @param id
	 * @return
	 */
	public Result syncGoods(Integer id);
	
	/**
	 * 同步会员数据
	 * @param id
	 * @return
	 */
	public Result syncVip(Integer id);
	
	/**
	 * 推送订单
	 * @param extsystem
	 * @param id
	 * @return
	 */
	public Result putOrder(MExtsystem extsystem, Integer id);
	
	/**
	 * 推送会员
	 * @param extsystem
	 * @param id
	 * @return
	 */
	public Result putVip(MExtsystem extsystem, Integer id);
	
	/**
	 * 修改会员信息
	 * @param extsystem
	 * @param id
	 * @return
	 */
	public Result changeVipInfo(MExtsystem extsystem, Integer id);

}
