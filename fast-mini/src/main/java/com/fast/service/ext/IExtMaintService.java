package com.fast.service.ext;

import java.math.BigDecimal;
import java.util.List;

import com.fast.base.Result;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MVipcoupon;

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
	 * 同步商品SKU
	 * @param id
	 * @return
	 */
	public Result syncGoodsSKu(Integer id);
	
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
	 * @param id
	 * @return
	 */
	public Result changeVipInfo(Integer id);
	
	/**
	 * 会员积分变动
	 * @param extsystem
	 * @param vipid
	 * @param point
	 * @param reason
	 * @return
	 */
	public Result changeVipPoint(MExtsystem extsystem, Integer vipid, Integer point, String reason);
	
	/**
	 * 会员储值变动
	 * @param extsystem
	 * @param vipid
	 * @param deposit
	 * @param reason
	 * @return
	 */
	public Result changeVipDeposit(MExtsystem extsystem, Integer vipid, BigDecimal deposit, String reason);
	
	/**
	 * 更新会员积分流水
	 * @param extsystem
	 * @param vipid
	 * @return
	 */
	public Result updateVipPointRecord(Integer vipid);
	
	/**
	 * 更新会员储值流水
	 * @param extsystem
	 * @param vipid
	 * @return
	 */
	public Result updateVipDepositRecord(Integer vipid);
	
	/**
	 * 新增优惠券
	 * @param id
	 * @return
	 */
	public Result createCoupon(MExtsystem extsystem, Integer id);
	
	/**
	 * 修改优惠券
	 * @param id
	 * @return
	 */
	public Result updateCoupon(Integer id);
	
	/**
	 * 更新优惠券适用商品
	 * @param extsystem
	 * @param id
	 * @return
	 */
	public Result changeCouponSuitGoods(MExtsystem extsystem, Integer id);
	
	/**
	 * 更新优惠券适用商品
	 * @param extsystem
	 * @param id
	 * @return
	 */
	public Result changeCouponSuitDepartments(MExtsystem extsystem, Integer id);
	
	/**
	 * 推送优惠券给会员
	 * @param vipcouponsList
	 * @return
	 */
	public Result pushVipCoupon(List<MVipcoupon> vipcouponsList);
	
	/**
	 * 更改会员优惠券状态
	 * @param vipcoupon
	 * @return
	 */
	public Result pushVipCouponStatus(MVipcoupon vipcoupon);
	
	/**
	 * 定时任务
	 * 推送会员
	 * @return
	 */
	public Result pushVipTask();
	
	/**
	 * 定时任务
	 * 自动取消订单
	 * @return
	 */
	public Result cancelOrderTask();
	
	/**
	 * 定时任务
	 * 推送订单
	 * @return
	 */
	public Result pushOrderTask();
	
	/**
	 * 定时任务
	 * 更新订单状态
	 * @return
	 */
	public Result changeOrderStatusTask();
	
	/**
	 * 定时任务
	 * 创建优惠券
	 * @return
	 */
	public Result pushCouponTask();
	
	/**
	 * 定时任务
	 * 推送会员优惠券
	 * @return
	 */
	public Result pushVipCouponTask();

}
