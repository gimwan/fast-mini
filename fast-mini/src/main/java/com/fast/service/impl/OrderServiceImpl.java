package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.dao.MOrderMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipaddressMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MOrder;
import com.fast.base.data.entity.MOrderExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipaddress;
import com.fast.base.data.entity.MVipaddressExample;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MViptype;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.service.IMiniProgramService;
import com.fast.service.IOrderService;
import com.fast.service.IVipMiniService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

/**
 * 订单
 * @author J
 *
 */
@Service
public class OrderServiceImpl implements IOrderService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MOrderMapper orderMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MVipminiMapper vipminiMapper;
	
	@Autowired
	MMiniprogramMapper miniprogramMapper;
	
	@Autowired
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	MCouponMapper couponMapper;
	
	@Autowired
	MVipaddressMapper vipaddressMapper;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IVipMiniService iVipMiniService;

	@Override
	public Result order() {
		Result result = new Result();

		try {
			MOrderExample example = new MOrderExample();
			example.setOrderByClause("createtime desc");
			List<MOrder> list = orderMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderServiceImpl.order报错：", e);
		}

		return result;
	}

	@Override
	public Result list(PagingView page) {
		Result result = new Result();

		try {
			page.setOrderBy("order by createtime desc,no desc");
			String sql = "select * from m_order";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "order");
			page.setRecords(list);
			result.setErrcode(0);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderServiceImpl.list报错：", e);
		}

		return result;
	}

	@Override
	public Result orderConfirmData(String appid, String openid, String cartid) {
		Result result = new Result();

		try {
			if (Common.isEmpty(openid)) {
				result.setMessage("openid无效");
				return result;
			}
			MMiniprogram miniprogram = new MMiniprogram();
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				miniprogram = (MMiniprogram) r.getData();
			} else {
				return r;
			}
			MVipmini vipmini = new MVipmini();
			r = iVipMiniService.queryVipMiniByOpenid(miniprogram.getId(), openid);
			if (Common.isActive(r)) {
				vipmini = (MVipmini) r.getData();
			} else {
				return r;
			}
			MVipaddress vipaddress = new MVipaddress();
			if (Common.isActive(result)) {
				MVipaddressExample example = new MVipaddressExample();
				example.createCriteria().andVipidEqualTo(vipmini.getVipid());
				example.setOrderByClause(" isdefault desc");
				List<MVipaddress> list = vipaddressMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					vipaddress = list.get(0);
				} else {
					result.setErrcode(Integer.valueOf(2));
					result.setMessage("无收货地址");
					return result;
				}
			}
			
			result = calculation(vipmini.getVipid(), cartid, null, 0, 0);
			if (Common.isActive(result)) {
				HashMap<String, Object> map = (HashMap<String, Object>) result.getData();
				map.put("receiver", vipaddress.getReceiver() == null ? "" : vipaddress.getReceiver());
				map.put("phone", vipaddress.getPhone() == null ? "" : vipaddress.getPhone());
				String province = vipaddress.getProvince() == null ? "" : vipaddress.getProvince();
				String city = vipaddress.getCity() == null ? "" : vipaddress.getCity();
				String county = vipaddress.getCounty() == null ? "" : vipaddress.getCounty();
				String address = vipaddress.getAddress() == null ? "" : vipaddress.getAddress();
				map.put("address", province+city+county+address);
				map.put("addressid", vipaddress.getId());
				result.setData(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderServiceImpl.orderConfirmData报错：", e);
		}

		return result;
	}

	@Override
	public Result calculation(Integer vipid, String cartid, Integer couponid, Integer usepoint, Integer usedeposit) {
		Result result = new Result();

		try {
			MVip vip = new MVip();
			vip = vipMapper.selectByPrimaryKey(vipid);
			if (vip == null || vip.getId() == null) {
				result.setMessage("无此会员");
				return result;
			}			
			MViptype viptype = new MViptype();
			viptype = viptypeMapper.selectByPrimaryKey(vip.getTypeid());
			if (viptype == null || viptype.getId() == null) {
				result.setMessage("会员等级无效");
				return result;
			}
			BigDecimal discount = viptype.getDiscount() == null ? BigDecimal.ONE : viptype.getDiscount();
			Integer pointRate = viptype.getPointrate() == null ? 0 : viptype.getPointrate();
			
			// 积分
			Integer point = 0;
			// 储值
			BigDecimal deposit = BigDecimal.ZERO;
			MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(vip.getId());
			if (vipaccount != null && vipaccount.getId() != null) {
				point = vipaccount.getPoint() == null ? 0 : vipaccount.getPoint();
				deposit = vipaccount.getDeposit() == null ? BigDecimal.ZERO : vipaccount.getDeposit();
			}
			
			String[] cartids = cartid.split(",");
			List<String> cartList = new ArrayList<String>();
			for (int i = 0; i < cartids.length; i++) {
				cartList.add(cartids[i].trim());
			}
			if (cartList == null || cartList.size() < 1) {
				result.setMessage("购物袋数据无效");
				return result;
			}
			
			String sql = "select a.id,a.goodsid,a.colorid,a.patternid,a.sizeid,isnull(a.quantity,0) as quantity,"
					+ "b.code,b.name,b.photourl,isnull(b.price,0) as price,isnull(b.price,0) as saleprice,isnull(b.baseprice,0) as baseprice,"
					+ "isnull(b.price,0)*isnull(a.quantity,0) as amount,isnull(b.price,0)*isnull(a.quantity,0) as saleamount,"
					+ "isnull(b.baseprice,0)*isnull(a.quantity,0) as baseamount,b.exchangepoint,b.kind,b.showcolor,b.showpattern,b.showsize,"
					+ "c.name as color,d.name as pattern,e.name as size "
					+ "from m_vipcart a "
					+ "inner join m_goods b on a.goodsid=b.id "
					+ "left join m_color c on a.colorid=c.id "
					+ "left join m_pattern d on a.patternid=d.id "
					+ "left join m_size e on a.sizeid=e.id "
					+ "where a.id in ("+StringUtils.join(cartList.toArray(), ",")+")";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list == null || list.size() < 1) {
				result.setMessage("商品数据无效");
				return result;
			}
			// 商品总价
			BigDecimal goodsAmount = BigDecimal.ZERO;
			BigDecimal goodsSaleAmount = BigDecimal.ZERO;
			Integer goodsQuantity = 0;
			// 会员折扣
			for (int i = 0; i < list.size(); i++) {
				Integer quantity = list.get(i).get("quantity") == null ? 0 : Integer.valueOf(list.get(i).get("quantity").toString().trim());
				BigDecimal price = list.get(i).get("price") == null ? BigDecimal.ZERO : new BigDecimal(list.get(i).get("price").toString().trim());
				goodsSaleAmount = goodsSaleAmount.add(price.multiply(new BigDecimal(quantity.toString())));
				BigDecimal newPrice = price.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP);
				list.get(i).put("price", newPrice);
				BigDecimal amount = newPrice.multiply(new BigDecimal(quantity.toString()));
				list.get(i).put("amount", amount);
				goodsAmount = goodsAmount.add(amount);
				goodsQuantity = goodsQuantity + quantity;
			}
			// 结算金额
			BigDecimal paymoney = goodsAmount;
			// 券抵扣额
			BigDecimal couponMoney = BigDecimal.ZERO;
			BigDecimal discounMoney = goodsSaleAmount.subtract(goodsAmount);
			
			if (couponid != null && couponid.intValue() > 0) {
				MVipcoupon vipcoupon = vipcouponMapper.selectByPrimaryKey(couponid);
				if (vipcoupon != null && vipcoupon.getId() != null) {
					Date now = new Date();
					if (vipcoupon.getUseflag() == 1) {
						result.setMessage("优惠券已使用");
						return result;
					}
					if (vipcoupon.getBegintime().getTime() > now.getTime()) {
						result.setMessage("优惠券未到启用时间");
						return result;
					}
					if (vipcoupon.getEndtime().getTime() > now.getTime()) {
						result.setMessage("优惠券已过期");
						return result;
					}
					MCoupon coupon = couponMapper.selectByPrimaryKey(vipcoupon.getCouponid());
					if (coupon != null && coupon.getId() != null) {
						BigDecimal Enableamount = coupon.getEnableamount() == null ? BigDecimal.ZERO : coupon.getEnableamount();;
						if (Enableamount.compareTo(paymoney) > 0) {
							result.setMessage("优惠券未达到启用金额");
							return result;
						}
						couponMoney = coupon.getAmount() == null ? BigDecimal.ZERO : coupon.getAmount();
						if (couponMoney.compareTo(paymoney) >= 0) {
							couponMoney = paymoney;
							paymoney = BigDecimal.ZERO;
						} else {
							paymoney = paymoney.subtract(couponMoney);
						}
					} else {
						result.setMessage("优惠券无效");
						return result;
					}
				} else {
					result.setMessage("优惠券无效");
					return result;
				}
			}
			
			// 使用积分
			BigDecimal pointMoney = new BigDecimal(point.toString()).multiply(new BigDecimal(pointRate.toString()));
			if (usepoint != null && usepoint.intValue() == 1) {
				if (pointRate.intValue() > 0) {
					if (pointMoney.compareTo(paymoney) > 0) {
						pointMoney = paymoney;
						paymoney = BigDecimal.ZERO;
					} else {
						paymoney = paymoney.subtract(pointMoney);
					}
					point = pointMoney.divide(new BigDecimal(pointRate.toString())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				} else {
					point = 0;
				}
			} else {
				if (pointMoney.compareTo(paymoney) > 0) {
					pointMoney = paymoney;
					point = pointMoney.divide(new BigDecimal(pointRate.toString())).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
				}
			}
			
			// 使用储值
			if (usedeposit != null && usedeposit.intValue() == 1) {
				if (deposit.compareTo(paymoney) > 0) {
					deposit = paymoney;
					paymoney = BigDecimal.ZERO;
				} else {
					paymoney = paymoney.subtract(deposit);
				}
			}
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("goods", list);
			map.put("goodsamount", goodsAmount);
			map.put("goodsquantity", goodsQuantity);
			map.put("vipid", vip.getId());
			map.put("discount", discount);
			map.put("discounmoney", discounMoney);
			map.put("couponid", couponid);
			map.put("couponmoney", couponMoney);
			map.put("point", point);
			map.put("pointrate", pointRate);
			map.put("pointmoney", pointMoney);
			map.put("deposit", deposit);
			map.put("paymoney", paymoney);
			
			result.setErrcode(Integer.valueOf(0));
			result.setData(map);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderServiceImpl.calculation报错：", e);
		}

		return result;
	}

}
