package com.fast.service;

import java.math.BigDecimal;

import com.fast.base.Result;

import net.sf.json.JSONObject;

public interface IWechatPayService {
	
	/**
	 * 订单支付
	 * @param appid
	 * @param openid
	 * @param domain
	 * @param id
	 * @param spbill_create_ip
	 * @return
	 */
	public Result orderpay(String appid, String openid, String domain, Integer id, String spbill_create_ip);
	
	/**
	 * 微信支付发起参数
	 * @param appid  小程序appid
	 * @param openid  会员openid
	 * @param partner  微信支付商户号
	 * @param key  微信支付商户密钥
	 * @param out_trade_no  支付单号
	 * @param total_fee  总支付金额
	 * @param spbill_create_ip  发起ip地址
	 * @param body  商品描述
	 * @param notifyUrl  支付回调地址
	 * @return
	 */
	public JSONObject payment(String appid, String openid, String partner, String key, String out_trade_no,
			String total_fee, String spbill_create_ip, String body, String notifyUrl);
	
	/**
	 * 订单退款
	 * @param orderid
	 * @param refundMoney
	 * @return
	 */
	public Result orderRefund(Integer orderid, BigDecimal refundMoney);

}
