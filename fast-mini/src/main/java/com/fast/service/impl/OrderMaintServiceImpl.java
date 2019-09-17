package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.dao.MOrderMapper;
import com.fast.base.data.dao.MOrderdtlMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipaddressMapper;
import com.fast.base.data.dao.MVipcartMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.dao.MVipdepositrecordMapper;
import com.fast.base.data.dao.MVippointrecordMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MOrder;
import com.fast.base.data.entity.MOrderExample;
import com.fast.base.data.entity.MOrderdtl;
import com.fast.base.data.entity.MOrderdtlExample;
import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipaddress;
import com.fast.base.data.entity.MVipcartExample;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipdepositrecord;
import com.fast.base.data.entity.MVippointrecord;
import com.fast.service.IConfigService;
import com.fast.service.IMiniProgramService;
import com.fast.service.IOrderMaintService;
import com.fast.service.IOrderService;
import com.fast.service.IWechatPayService;
import com.fast.service.ext.IExtMaintService;
import com.fast.service.ext.IExtService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * 订单
 * @author J
 *
 */
@Service
public class OrderMaintServiceImpl implements IOrderMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MOrderMapper orderMapper;
	
	@Autowired
	IOrderService iOrderService;
	
	@Autowired
	MVipaddressMapper vipaddressMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	MOrderdtlMapper orderdtlMapper;
	
	@Autowired
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	MVipdepositrecordMapper vipdepositrecordMapper;
	
	@Autowired
	MVippointrecordMapper vippointrecordMapper;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	MGoodsskuMapper goodsskuMapper;
	
	@Autowired
	MVipcartMapper vipcartMapper;
	
	@Autowired
	IConfigService iConfigService;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IWechatPayService iWechatPayService;
	
	@Autowired
	IExtService iExtService;
	
	@Autowired
	IExtMaintService iExtMaintService;
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	// 订单自动取消任务锁
	private boolean cancelOrderTaskLock = false;
	// 推送订单任务锁
	private boolean pushOrderTaskLock = false;
	// 推送订单任务锁
	private boolean changeOrderStatusTaskLock = false;
	
	@Override
	public Result createOrder(String appid, Integer vipid, String cartid, Integer addressid, Integer couponid,
			Integer usepoint, Integer usedeposit) {
		Result result = new Result();

		try {
			MVipaddress vipaddress = vipaddressMapper.selectByPrimaryKey(addressid);
			if (vipaddress == null || vipaddress.getId() == null) {
				result.setMessage("收货地址无效");
				return result;
			}
			Integer miniprogramid = 0;
			Integer publicplatformid = 0;
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				MMiniprogram miniprogram = (MMiniprogram) r.getData();
				miniprogramid = miniprogram.getId();
				publicplatformid = miniprogram.getPublicplatformid();
			} else {
				return r;
			}
			
			r = iOrderService.calculation(vipid, cartid, couponid, usepoint, usedeposit);
			if (Common.isActive(r)) {
				HashMap<String, Object> payInfo = (HashMap<String, Object>) r.getData();
				Integer goodsQuantity = Integer.valueOf(payInfo.get("goodsquantity").toString());
				BigDecimal goodsAmount = new BigDecimal(payInfo.get("goodsamount").toString());
				BigDecimal discount = new BigDecimal(payInfo.get("discount").toString());
				BigDecimal discountMoney = new BigDecimal(payInfo.get("discountmoney").toString());
				BigDecimal couponmMoney = new BigDecimal(payInfo.get("couponmoney").toString());
				Integer point = Integer.valueOf(payInfo.get("point").toString());
				Integer pointRate = Integer.valueOf(payInfo.get("point").toString());
				BigDecimal pointMoney = new BigDecimal(payInfo.get("pointmoney").toString());
				BigDecimal deposit = new BigDecimal(payInfo.get("deposit").toString());
				BigDecimal payMoney = new BigDecimal(payInfo.get("paymoney").toString());
				List<LinkedHashMap<String, Object>> goodsList = (List<LinkedHashMap<String, Object>>) payInfo.get("goods");
				BigDecimal baseAmount = BigDecimal.ZERO;
				BigDecimal saleAmount = BigDecimal.ZERO;
				for (int i = 0; i < goodsList.size(); i++) {
					BigDecimal baseamt = new BigDecimal(goodsList.get(i).get("baseamount").toString());
					BigDecimal saleamt = new BigDecimal(goodsList.get(i).get("saleamount").toString());
					baseAmount = baseAmount.add(baseamt);
					saleAmount = saleAmount.add(saleamt);
				}
				Date now = new Date();
				MOrder order = new MOrder();
				order.setNo(String.valueOf(System.currentTimeMillis()));
				order.setKind(Integer.valueOf(1));
				order.setSource(Integer.valueOf(1));
				order.setVipid(vipid);
				order.setQuantity(goodsQuantity);
				order.setAmount(goodsAmount);
				order.setBaseamount(baseAmount);
				order.setSaleamount(saleAmount);
				order.setDiscount(discount);
				order.setDiscountmoney(discountMoney);
				order.setDeposit(deposit);
				order.setPoint(point);
				order.setPointrate(pointRate);
				order.setPointmoney(pointMoney);
				order.setCouponid(couponid);
				order.setCouponmoney(couponmMoney);
				order.setPaymoney(payMoney);
				order.setFreight(BigDecimal.ZERO);
				order.setPaystatus(Byte.valueOf("0"));
				order.setDeliverytype(Byte.valueOf("1"));
				order.setReceiver(vipaddress.getReceiver());
				order.setReceiverphone(vipaddress.getPhone());
				order.setReceiverprovince(vipaddress.getProvince());
				order.setReceivercity(vipaddress.getCity());
				order.setReceivercounty(vipaddress.getCounty());
				order.setReceiveraddress(vipaddress.getAddress());
				order.setCreatetime(now);
				order.setCreator("system");
				order.setUpdatedtime(now);
				order.setUseflag(Byte.valueOf("1"));
				
				if (order.getPaymoney().compareTo(BigDecimal.ZERO) > 0) {
					order.setStatus(Byte.valueOf("1"));
				} else {
					order.setStatus(Byte.valueOf("2"));
					order.setPaystatus(Byte.valueOf("2"));
					order.setPaytime(now);
				}
				
				order.setMiniprogramid(miniprogramid);
				order.setPublicplatformid(publicplatformid);
				order.setRetuenpaystatus(Byte.valueOf("0"));
				
				order = saveOrder(order, goodsList);
				result.setId(order.getId());
				result.setErrcode(Integer.valueOf(0));
			} else {
				result = r;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.createOrder报错：", e);
		}
	
		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public MOrder saveOrder(MOrder order, List<LinkedHashMap<String, Object>> goodsList) {
		orderMapper.insertSelective(order);
		List<Integer> cartidList = new ArrayList<>();
		for (int i = 0; i < goodsList.size(); i++) {
			MOrderdtl orderdtl = new MOrderdtl();
			orderdtl.setOrderid(order.getId());
			orderdtl.setGoodsid(Integer.valueOf(goodsList.get(i).get("goodsid").toString()));
			orderdtl.setColorid(Integer.valueOf(goodsList.get(i).get("colorid").toString()));
			orderdtl.setPatternid(Integer.valueOf(goodsList.get(i).get("patternid").toString()));
			orderdtl.setSizeid(Integer.valueOf(goodsList.get(i).get("sizeid").toString()));
			orderdtl.setQuantity(Integer.valueOf(goodsList.get(i).get("quantity").toString()));
			orderdtl.setPrice(new BigDecimal(goodsList.get(i).get("price").toString()));
			orderdtl.setBaseprice(new BigDecimal(goodsList.get(i).get("baseprice").toString()));
			orderdtl.setAmount(new BigDecimal(goodsList.get(i).get("amount").toString()));
			orderdtl.setSaleprice(new BigDecimal(goodsList.get(i).get("saleprice").toString()));
			orderdtl.setBaseamount(new BigDecimal(goodsList.get(i).get("baseamount").toString()));
			orderdtl.setSaleamount(new BigDecimal(goodsList.get(i).get("saleamount").toString()));
			orderdtl.setCreatetime(order.getCreatetime());
			orderdtl.setCreator(order.getCreator());
			orderdtl.setUpdatedtime(order.getUpdatedtime());
			orderdtl.setUseflag(Byte.valueOf("1"));
			orderdtlMapper.insertSelective(orderdtl);
			// 扣减库存
			MGoodssku goodssku = goodsskuMapper.selectByPrimaryKey(Integer.valueOf(goodsList.get(i).get("skuid").toString()));
			Integer quantity = goodssku.getQuantity().intValue() - orderdtl.getQuantity().intValue();
			goodssku.setQuantity(Long.valueOf(quantity.toString()));
			goodsskuMapper.updateByPrimaryKeySelective(goodssku);
			
			cartidList.add(Integer.valueOf(goodsList.get(i).get("id").toString()));
		}
		// 核销优惠券
		if (order.getCouponid() != null && order.getCouponid().intValue() > 0) {
			MVipcoupon vipcoupon = vipcouponMapper.selectByPrimaryKey(order.getCouponid());
			if (vipcoupon != null && vipcoupon.getId() != null && vipcoupon.getUseflag().intValue() != 1) {
				vipcoupon.setUseflag(Byte.valueOf("1"));
				vipcoupon.setUsetime(order.getUpdatedtime());
				vipcouponMapper.updateByPrimaryKeySelective(vipcoupon);
			}
		}
		// 扣减积分、储值，记录积分、储值流水
		boolean isUpdate = false;
		MVippointrecord vippointrecord = new MVippointrecord();
		vippointrecord.setVipid(order.getVipid());
		vippointrecord.setType(Byte.valueOf("1"));
		vippointrecord.setRefid(order.getId());
		vippointrecord.setUpdatedtime(order.getUpdatedtime());
		MVipdepositrecord vipdepositrecord = new MVipdepositrecord();
		vipdepositrecord.setVipid(order.getVipid());
		vipdepositrecord.setType(Byte.valueOf("1"));
		vipdepositrecord.setRefid(order.getId());
		vipdepositrecord.setUpdatedtime(order.getUpdatedtime());
		MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(order.getVipid());
		if (order.getPoint() != null && order.getPoint().intValue() > 0) {			
			Integer point = vipaccount.getPoint() - order.getPoint();			
			vipaccount.setPoint(point);
			vippointrecord.setPoint(order.getPoint() * -1);
			vippointrecord.setNewpoint(point);
			vippointrecordMapper.insertSelective(vippointrecord);
			isUpdate = true;
		}
		if (order.getDeposit() != null && order.getDeposit().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal deposit = vipaccount.getDeposit().subtract(order.getDeposit());
			vipaccount.setDeposit(deposit);
			vipdepositrecord.setDeposit(order.getDeposit().multiply(new BigDecimal("-1")));
			vipdepositrecord.setNewdeposit(deposit);
			vipdepositrecordMapper.insertSelective(vipdepositrecord);
			isUpdate = true;
		}
		if (isUpdate) {
			vipaccountMapper.updateByPrimaryKeySelective(vipaccount);
		}
		
		MVipcartExample vipcartExample = new MVipcartExample();
		vipcartExample.createCriteria().andVipidEqualTo(order.getVipid()).andIdIn(cartidList);
		vipcartMapper.deleteByExample(vipcartExample);
		
		return order;
	}

	@Override
	public Result afterPay(String orderno, String wechatno) {
		Result result = new Result();

		try {
			MOrderExample example = new MOrderExample();
			example.createCriteria().andNoEqualTo(orderno);
			List<MOrder> list = orderMapper.selectByExample(example);
			if (list == null || list.size() < 1) {
				result.setMessage("订单不存在");
				return result;
			}
			MOrder order = list.get(0);
			if (order.getStatus().intValue() != 1) {
				result.setMessage("当前订单状态为：" + order.getStatus() + "，流程有误");
				return result;
			}
			Date now = new Date();
			order.setWechatpayno(wechatno);
			order.setStatus(Byte.valueOf("2"));
			order.setPaystatus(Byte.valueOf("2"));
			order.setPaytime(now);
			order.setUpdatedtime(now);
			orderMapper.updateByPrimaryKeySelective(order);
			
			result.setErrcode(Integer.valueOf(0));
			result.setId(order.getId());
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.afterPay报错：", e);
		}
	
		return result;
	}
	
	@Override
	public Result distributionOrder(MUser user, Integer orderid, Integer delivererDepartmentID) {
		Result result = new Result();

		try {
			MOrder order = orderMapper.selectByPrimaryKey(orderid);
			if (order != null && order.getId() != null) {
				Date now = new Date();
				// 配货
				if (order.getStatus().intValue() == 2 && (order.getDistributionflag() == null || order.getDistributionflag().intValue() != 1)) {
					order.setDelivererdepartmentid(delivererDepartmentID);
					order.setDistributionflag(Byte.valueOf("1"));
					order.setDistributioner(user.getName());
					order.setDistributiontime(now);
					order.setUpdatedtime(now);
					orderMapper.updateByPrimaryKeySelective(order);
					
					result.setErrcode(Integer.valueOf(0));
					result.setId(order.getId());
					result.setMessage("配货成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.distributionOrder报错：", e);
		}
	
		return result;
	}

	@Override
	public Result deliverOrder(MUser user, Integer orderid, Integer logisticsid, String logisticsno) {
		Result result = new Result();

		try {
			MOrder order = orderMapper.selectByPrimaryKey(orderid);
			if (order != null && order.getId() != null) {
				Date now = new Date();
				// 发货
				if (order.getStatus().intValue() == 2 && order.getDistributionflag().intValue() == 1) {
					order.setLogisticsid(logisticsid);
					order.setLogisticsno(logisticsno.trim());
					order.setStatus(Byte.valueOf("3"));
					order.setUpdatedtime(now);
					order.setDeliverer(user.getName());
					order.setDeliverertime(now);
					order.setDelivererdepartmentid(0);
					orderMapper.updateByPrimaryKeySelective(order);
					
					result.setErrcode(Integer.valueOf(0));
					result.setId(order.getId());
					result.setMessage("发货成功");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.changeOrderStatus报错：", e);
		}
	
		return result;
	}
	
	@Override
	public Result createGroupbuyOrder(String appid, Integer vipid, Integer skuid, Integer quantity, Integer groupbuyid, Integer addressid) {
		Result result = new Result();

		try {
			MVipaddress vipaddress = vipaddressMapper.selectByPrimaryKey(addressid);
			if (vipaddress == null || vipaddress.getId() == null) {
				result.setMessage("收货地址无效");
				return result;
			}
			Integer miniprogramid = 0;
			Integer publicplatformid = 0;
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				MMiniprogram miniprogram = (MMiniprogram) r.getData();
				miniprogramid = miniprogram.getId();
				publicplatformid = miniprogram.getPublicplatformid();
			} else {
				return r;
			}
			
			r = iOrderService.groupbuyCalculation(groupbuyid, skuid, quantity);
			if (Common.isActive(r)) {
				LinkedHashMap<String, Object> payInfo = (LinkedHashMap<String, Object>) r.getData();
				BigDecimal amount = new BigDecimal(payInfo.get("amount").toString());
				BigDecimal payMoney = new BigDecimal(payInfo.get("paymoney").toString());
				BigDecimal baseAmount = new BigDecimal(payInfo.get("baseamount").toString());
				BigDecimal saleAmount = new BigDecimal(payInfo.get("saleamount").toString());
				
				Date now = new Date();
				MOrder order = new MOrder();
				order.setNo(String.valueOf(System.currentTimeMillis()));
				order.setKind(Integer.valueOf(3));
				order.setSource(Integer.valueOf(1));
				order.setVipid(vipid);
				order.setQuantity(quantity);
				order.setAmount(amount);
				order.setBaseamount(baseAmount);
				order.setSaleamount(saleAmount);
				order.setDiscount(BigDecimal.ONE);
				order.setDiscountmoney(BigDecimal.ZERO);
				order.setDeposit(BigDecimal.ZERO);
				order.setPoint(Integer.valueOf(0));
				order.setPointrate(Integer.valueOf(0));
				order.setPointmoney(BigDecimal.ZERO);
				order.setCouponid(Integer.valueOf(0));
				order.setCouponmoney(BigDecimal.ZERO);
				order.setPaymoney(payMoney);
				order.setFreight(BigDecimal.ZERO);
				order.setPaystatus(Byte.valueOf("0"));
				order.setDeliverytype(Byte.valueOf("1"));
				order.setReceiver(vipaddress.getReceiver());
				order.setReceiverphone(vipaddress.getPhone());
				order.setReceiverprovince(vipaddress.getProvince());
				order.setReceivercity(vipaddress.getCity());
				order.setReceivercounty(vipaddress.getCounty());
				order.setReceiveraddress(vipaddress.getAddress());
				order.setCreatetime(now);
				order.setMarketingid(groupbuyid);
				order.setCreator("system");
				order.setUpdatedtime(now);
				order.setUseflag(Byte.valueOf("1"));
				
				if (order.getPaymoney().compareTo(BigDecimal.ZERO) > 0) {
					order.setStatus(Byte.valueOf("1"));
				} else {
					order.setStatus(Byte.valueOf("2"));
					order.setPaystatus(Byte.valueOf("2"));
					order.setPaytime(now);
				}
				
				order.setMiniprogramid(miniprogramid);
				order.setPublicplatformid(publicplatformid);
				order.setRetuenpaystatus(Byte.valueOf("0"));
				
				order = saveGroupbuyOrder(order, payInfo);
				result.setId(order.getId());
				result.setErrcode(Integer.valueOf(0));
			} else {
				result = r;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.createOrder报错：", e);
		}
	
		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public MOrder saveGroupbuyOrder(MOrder order, LinkedHashMap<String, Object> goods) {
		orderMapper.insertSelective(order);
		
		MOrderdtl orderdtl = new MOrderdtl();
		orderdtl.setOrderid(order.getId());
		orderdtl.setGoodsid(Integer.valueOf(goods.get("goodsid").toString()));
		orderdtl.setColorid(Integer.valueOf(goods.get("colorid").toString()));
		orderdtl.setPatternid(Integer.valueOf(goods.get("patternid").toString()));
		orderdtl.setSizeid(Integer.valueOf(goods.get("sizeid").toString()));
		orderdtl.setQuantity(Integer.valueOf(goods.get("quantity").toString()));
		orderdtl.setPrice(new BigDecimal(goods.get("price").toString()));
		orderdtl.setBaseprice(new BigDecimal(goods.get("baseprice").toString()));
		orderdtl.setAmount(new BigDecimal(goods.get("amount").toString()));
		orderdtl.setSaleprice(new BigDecimal(goods.get("saleprice").toString()));
		orderdtl.setBaseamount(new BigDecimal(goods.get("baseamount").toString()));
		orderdtl.setSaleamount(new BigDecimal(goods.get("saleamount").toString()));
		orderdtl.setCreatetime(order.getCreatetime());
		orderdtl.setCreator(order.getCreator());
		orderdtl.setUpdatedtime(order.getUpdatedtime());
		orderdtl.setUseflag(Byte.valueOf("1"));
		orderdtlMapper.insertSelective(orderdtl);
		// 扣减库存
		MGoodssku goodssku = goodsskuMapper.selectByPrimaryKey(Integer.valueOf(goods.get("id").toString()));
		Integer quantity = goodssku.getQuantity().intValue() - orderdtl.getQuantity().intValue();
		goodssku.setQuantity(Long.valueOf(quantity.toString()));
		goodsskuMapper.updateByPrimaryKeySelective(goodssku);
		
		return order;
	}

	@Override
	public Result cancelOrderTask() {
		System.out.println("订单自动取消开始...");
		Result result = new Result();

		try {
			if (cancelOrderTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			// 订单自动取消时间（分）
			Result r = iConfigService.queryConfigValueByCode("4001");
			String min = "";
			if (Common.isActive(r)) {
				min = (String) r.getData();
			}
			if (Common.isEmpty(min)) {
				result.setMessage("获取订单自动取消时间参数错误");
				return result;
			}
			
			cancelOrderTaskLock = true;
			
			String sql = "select * from m_order where status=1 and datediff(minute, createtime, getdate())>=" + min + " order by createtime asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						MOrder order = orderMapper.selectByPrimaryKey(Integer.valueOf(list.get(i).get("id").toString()));
						changeOrder(order);
					} catch (Exception e) {
						System.out.println("订单自动取消失败：orderid="+list.get(i).get("id").toString());
						FastLog.error("调用OrderMaintServiceImpl.cancelOrderTask修改订单状态报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.cancelOrderTask报错：", e);
		} finally {
			cancelOrderTaskLock = false;
		}
		
		System.out.println("订单自动取消结束...");
		return result;
	}

	@Override
	public Result cancelOrder(Integer orderid) {
		Result result = new Result();
		
		try {
			MOrder order = orderMapper.selectByPrimaryKey(orderid);
			if (order == null || order.getId() == null) {
				result.setMessage("订单不存在");
				return result;
			}
			if (order.getStatus().intValue() == 0) {
				result.setMessage("订单已取消");
				return result;
			}
			if (order.getStatus().intValue() >= 3) {
				result.setMessage("订单已发货");
				return result;
			}
			
			changeOrder(order);
			
			result.setId(orderid);
			result.setErrcode(Integer.valueOf(0));
			result.setMessage("取消成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.cancelOrder报错：", e);
		}
		
		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	private void changeOrder(MOrder order) {
		Date now = new Date();
		
		MOrderdtlExample example = new MOrderdtlExample();
		example.createCriteria().andOrderidEqualTo(order.getId());
		List<MOrderdtl> dtlList = orderdtlMapper.selectByExample(example);
		if (dtlList != null && dtlList.size() > 0) {
			for (int j = 0; j < dtlList.size(); j++) {
				MOrderdtl orderdtl = dtlList.get(j);
				
				// 加回库存
				// 秒杀
				if (order.getKind().intValue() == 4) {
					
				}
				// 正常、拼团
				else {
					MGoodsskuExample skuExample = new MGoodsskuExample();
					skuExample.createCriteria().andGoodsidEqualTo(orderdtl.getGoodsid())
							.andColoridEqualTo(orderdtl.getColorid())
							.andPatternidEqualTo(orderdtl.getPatternid())
							.andSizeidEqualTo(orderdtl.getSizeid());
					List<MGoodssku> skuList = goodsskuMapper.selectByExample(skuExample);
					if (skuList != null && skuList.size() > 0) {
						for (int k = 0; k < skuList.size(); k++) {
							MGoodssku goodssku = skuList.get(k);
							Integer quantity = goodssku.getQuantity().intValue() + orderdtl.getQuantity().intValue();
							goodssku.setQuantity(Long.valueOf(quantity.toString()));
							goodsskuMapper.updateByPrimaryKeySelective(goodssku);
						}
					}
				}
			}			
		}
		
		// 返还优惠券
		if (order.getCouponid() != null && order.getCouponid().intValue() > 0) {
			MVipcoupon vipcoupon = vipcouponMapper.selectByPrimaryKey(order.getCouponid());
			if (vipcoupon != null && vipcoupon.getId() != null && vipcoupon.getUseflag().intValue() != 1) {
				vipcoupon.setUseflag(Byte.valueOf("0"));
				vipcouponMapper.updateByPrimaryKeySelective(vipcoupon);
			}
		}
		
		// 加回积分、储值，记录积分、储值流水
		boolean isUpdate = false;
		MVippointrecord vippointrecord = new MVippointrecord();
		vippointrecord.setVipid(order.getVipid());
		vippointrecord.setType(Byte.valueOf("2"));
		vippointrecord.setRefid(order.getId());
		vippointrecord.setUpdatedtime(now);
		MVipdepositrecord vipdepositrecord = new MVipdepositrecord();
		vipdepositrecord.setVipid(order.getVipid());
		vipdepositrecord.setType(Byte.valueOf("2"));
		vipdepositrecord.setRefid(order.getId());
		vipdepositrecord.setUpdatedtime(now);
		MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(order.getVipid());
		if (order.getPoint() != null && order.getPoint().intValue() > 0) {
			Integer point = vipaccount.getPoint() + order.getPoint();
			vipaccount.setPoint(point);
			vippointrecord.setPoint(order.getPoint());
			vippointrecord.setNewpoint(point);
			vippointrecordMapper.insertSelective(vippointrecord);
			isUpdate = true;
		}
		if (order.getDeposit() != null && order.getDeposit().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal deposit = vipaccount.getDeposit().add(order.getDeposit());
			vipaccount.setDeposit(deposit);
			vipdepositrecord.setDeposit(order.getDeposit());
			vipdepositrecord.setNewdeposit(deposit);
			vipdepositrecordMapper.insertSelective(vipdepositrecord);
			isUpdate = true;
		}
		if (isUpdate) {
			vipaccountMapper.updateByPrimaryKeySelective(vipaccount);
		}
		
		// 已付款
		if (order.getStatus().intValue() == 2 && order.getPaymoney().compareTo(BigDecimal.ZERO) > 0 && order.getPaystatus().intValue() == 2) {
			if (!Common.isEmpty(order.getWechatpayno())) {
				// 微信退款
				boolean isRefund = refund(order);
				if (isRefund) {
					order.setRetuenpaystatus(Byte.valueOf("2"));
				}
			}
		}
		
		order.setStatus(Byte.valueOf("0"));
		order.setCanceltime(now);
		order.setCanceler("system");
		order.setUpdatedtime(now);
		orderMapper.updateByPrimaryKeySelective(order);
	}
	
	private Boolean refund(MOrder order) {
		boolean isRefund = false;
		Result result = iWechatPayService.orderRefund(order.getId(), order.getPaymoney());
		if (Common.isActive(result)) {
			HashMap<String, Object> map = (HashMap<String, Object>) result.getData();
			String result_code = map.get("result_code").toString().trim();
			if ("SUCCESS".equals(result_code)) {
				isRefund = true;
			}
		}
		return isRefund;
	}
	
	@Override
	public Result pushOrderTask() {
		System.out.println("推送订单开始...");
		Result result = new Result();

		try {
			if (pushOrderTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			
			pushOrderTaskLock = true;
			
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			String sql = "select * from m_order where status>=3 and (extid is null or extid='') order by deliverertime asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						Result r = iExtMaintService.putOrder(extsystem, Integer.valueOf(list.get(i).get("id").toString()));
						if (Common.isActive(r)) {
							/*com.alibaba.fastjson.JSONObject jObject = com.alibaba.fastjson.JSONObject.parseObject(r.getData().toString());
							if (jObject != null && !jObject.isEmpty()) {
								String extid = jObject.get("id") == null ? "" : jObject.getString("id");
								if (!Common.isEmpty(extid)) {
									JSONObject object = JSONObject.fromObject(list.get(i));
									MOrder order = (MOrder) JSONObject.toBean(object, MOrder.class);
									order.setExtid(extid);
									order.setUpdatedtime(new Date());
									orderMapper.updateByPrimaryKeySelective(order);
								}
							}*/
						} else {
							System.out.println("推送订单失败：orderid="+list.get(i).get("id").toString());
						}
					} catch (Exception e) {
						System.out.println("推送订单失败：orderid="+list.get(i).get("id").toString());
						FastLog.error("调用OrderMaintServiceImpl.pushOrderTask推送订单报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.pushOrderTask报错：", e);
		} finally {
			pushOrderTaskLock = false;
		}
		
		System.out.println("推送订单结束...");
		return result;
	}
	
	@Override
	public Result changeOrderStatusTask() {
		System.out.println("更新订单状态开始...");
		Result result = new Result();

		try {
			if (changeOrderStatusTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			
			changeOrderStatusTaskLock = true;
			
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			String sql = "select * from m_order where status=2 and distributionflag=1 and (extid is null or extid='') order by distributiontime asc,createtime asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						Result r = iExtService.queryOrderStatus(extsystem, list.get(i).get("no").toString());
						if (Common.isActive(r)) {
							com.alibaba.fastjson.JSONObject jObject = com.alibaba.fastjson.JSONObject.parseObject(r.getData().toString());
							if (jObject != null && !jObject.isEmpty()) {
								String state = jObject.get("state") == null ? "" : jObject.getString("state");
								if (!Common.isEmpty(state) && "已完成".equals(state)) {
									MOrder order = orderMapper.selectByPrimaryKey(Integer.valueOf(list.get(i).get("id").toString()));
									order.setStatus(Byte.valueOf("3"));
									order.setUpdatedtime(new Date());
									orderMapper.updateByPrimaryKeySelective(order);
								}
							}
						} else {
							System.out.println("查询订单状态失败：orderno="+list.get(i).get("no").toString());
						}
					} catch (Exception e) {
						System.out.println("更新订单状态失败：orderid="+list.get(i).get("id").toString());
						FastLog.error("调用OrderMaintServiceImpl.changeOrderStatusTask更新订单状态报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.changeOrderStatusTask报错：", e);
		} finally {
			changeOrderStatusTaskLock = false;
		}
		
		System.out.println("更新订单状态结束...");
		return result;
	}

	@Override
	public Result receiptOrder(Integer orderid) {
		Result result = new Result();

		try {
			MOrder order = orderMapper.selectByPrimaryKey(orderid);
			if (order == null || order.getId() == null) {
				result.setMessage("订单不存在");
				return result;
			}
			if (order.getStatus().intValue() == 0) {
				result.setMessage("订单已取消");
				return result;
			}
			if (order.getStatus().intValue() < 3) {
				result.setMessage("订单未发货");
				return result;
			}
			if (order.getStatus().intValue() == 3) {
				Date now = new Date();
				order.setStatus(Byte.valueOf("4"));
				order.setReceivertime(now);
				order.setUpdatedtime(now);
				orderMapper.updateByPrimaryKeySelective(order);
				
				result.setErrcode(Integer.valueOf(0));
				result.setId(orderid);
				result.setMessage("收货成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.receiptOrder报错：", e);
		}
		
		return result;
	}

}
