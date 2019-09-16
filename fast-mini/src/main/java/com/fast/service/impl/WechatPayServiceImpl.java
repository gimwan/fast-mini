package com.fast.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MOrderMapper;
import com.fast.base.data.dao.MPublicplatformMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MOrder;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipService;
import com.fast.service.IWechatPayService;
import com.fast.service.IWechatService;
import com.fast.system.log.FastLog;
import com.fast.util.ClientCustomSSL;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * 微信支付
 * @author J
 *
 */
@Service
public class WechatPayServiceImpl implements IWechatPayService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MOrderMapper orderMapper;
	
	@Autowired
	IWechatService iWechatService;
	
	@Autowired
	IMiniProgramService iMiniProgramService;
	
	@Autowired
	IVipService iVipService;
	
	@Autowired
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	MVipminiMapper vipminiMapper;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MPublicplatformMapper publicplatformMapper;

	@Override
	public Result orderpay(String appid, String openid, String domain, Integer id, String spbill_create_ip) {
		Result result = new Result();

		try {
			MMiniprogram miniprogram = new MMiniprogram();
			Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
			if (Common.isActive(r)) {
				miniprogram = (MMiniprogram) r.getData();
			} else {
				result.setMessage("查询不到小程序");
				return result;
			}
			MPublicplatform publicplatform = publicplatformMapper.selectByPrimaryKey(miniprogram.getPublicplatformid());
			if (publicplatform == null || publicplatform.getId() == null) {
				result.setMessage("查询不到公众号");
				return result;
			}
			MVip vip = new MVip();
			MVipminiExample example = new MVipminiExample();
			example.createCriteria().andOpenidEqualTo(openid).andMiniprogramidEqualTo(miniprogram.getId());
			List<MVipmini> list = vipminiMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				Integer vipid = list.get(0).getVipid();
				if (vipid != null) {
					vip = vipMapper.selectByPrimaryKey(vipid);
					if (vip == null || vip.getId() == null) {
						result.setMessage("查询不到会员");
						return result;
					}
				} else {
					result.setMessage("查询不到会员");
					return result;
				}
			} else {
				result.setMessage("查询不到会员");
				return result;
			}
			
			MOrder order = orderMapper.selectByPrimaryKey(id);
			if (order == null || order.getId() == null) {
				result.setMessage("订单记录不存在");
				return result;
			}
			
			if (order.getStatus().intValue() == 0) {
				result.setMessage("订单已取消");
				return result;
			}
			
			if (order.getStatus().intValue() != 1) {
				result.setMessage("订单非待付款状态");
				return result;
			}
			
			String body = publicplatform.getBody();
			if (Common.isEmpty(body)) {
				result.setMessage("微信支付参数未设置");
				return result;
			}
			String partner = publicplatform.getMchid();
			if (Common.isEmpty(partner)) {
				result.setMessage("微信支付参数未设置");
				return result;
			}
			String key = publicplatform.getMchkey();
			if (Common.isEmpty(key)) {
				result.setMessage("微信支付参数未设置");
				return result;
			}
			
			// 支付回调地址
            String notifyUrl = domain + "/mini/notify/orderpay";            
            String total_fee = String.valueOf(order.getPaymoney().multiply(new BigDecimal("100")).intValue());        	
        	String out_trade_no = order.getNo();
        	
        	JSONObject jsonObject = payment(appid, openid, partner, key, out_trade_no, total_fee, spbill_create_ip, body, notifyUrl);
        	
        	Date now = new Date();
			order.setPaystatus(Byte.valueOf("1"));
			order.setUpdatedtime(now);
			orderMapper.updateByPrimaryKey(order);
        	
        	result.setData(jsonObject);
        	result.setErrcode(Integer.valueOf(0));
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用WechatPayServiceImpl.orderpay报错：", e);
		}

		return result;
	}

	@Override
	public JSONObject payment(String appid, String openid, String partner, String key, String out_trade_no, String total_fee,
			String spbill_create_ip, String body, String notifyUrl) {
		JSONObject jsonObject = new JSONObject();

		try {
			Date date = new Date();

			String timeStamp = String.valueOf(date.getTime());
			String nonceStr = String.valueOf(System.currentTimeMillis() / 1000);
			String prepay_id = prepay(appid, partner, key, out_trade_no, total_fee, spbill_create_ip, nonceStr,
					notifyUrl, openid, body);
			String packages = "prepay_id=" + prepay_id;
			String signType = "MD5";

			// 支付签名
			SortedMap<String, String> signMap = new TreeMap<String, String>();
			signMap.put("appId", appid);
			signMap.put("timeStamp", timeStamp);
			signMap.put("nonceStr", nonceStr);
			signMap.put("package", packages);
			signMap.put("signType", signType);
			String paySign = CommonUtil.createSign(signMap, key);

			jsonObject.put("appId", appid);
			jsonObject.put("timeStamp", timeStamp);
			jsonObject.put("nonceStr", nonceStr);
			jsonObject.put("package", packages);
			jsonObject.put("signType", signType);
			jsonObject.put("paySign", paySign);
		} catch (Exception e) {
			FastLog.error("调用WechatPayServiceImpl.payment报错：", e);
		}

		return jsonObject;
	}
	
	public String prepay(String appid, String partner, String key, String out_trade_no, String total_fee,
			String spbill_create_ip, String nonceStr, String notifyUrl, String openid, String body) {
		// 预支付订单号
		String prepay_id = "";
		String urlStr = "https://api.mch.weixin.qq.com/pay/unifiedorder";

		try {
			SortedMap<String, String> params = new TreeMap<String, String>();
			params.put("appid", appid);
			params.put("mch_id", partner);
			params.put("body", body);
			params.put("out_trade_no", out_trade_no);
			params.put("total_fee", total_fee);
			params.put("spbill_create_ip", spbill_create_ip);
			params.put("trade_type", "JSAPI");
			params.put("nonce_str", nonceStr);
			params.put("notify_url", notifyUrl);
			params.put("openid", openid);

			String sign = CommonUtil.createSign(params, key);
			params.put("sign", sign);

			// 获取预支付单号要post的xml
			String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>" + partner + "</mch_id>" + "<nonce_str>"
					+ nonceStr + "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>" + "<body><![CDATA[" + body
					+ "]]></body>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<total_fee>" + total_fee
					+ "</total_fee>" + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" + "<notify_url>"
					+ notifyUrl + "</notify_url>" + "<trade_type>JSAPI</trade_type>" + "<openid>" + openid + "</openid>"
					+ "</xml>";

			System.out.println("预支付发起xml：");
			System.out.println(xml);

			URL url = new URL(urlStr);
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/xml");

			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(new String(xml.getBytes("UTF-8")));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			StringBuffer buffer = new StringBuffer();
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				buffer.append(line);
			}
			Document doc = DocumentHelper.parseText(buffer.toString());
			System.out.println("预支付结果：");
			for (Iterator iterator = doc.getRootElement().elementIterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				System.out.println(element.getName() + ":" + element.getStringValue());
				if (element.getName().equals("prepay_id")) {
					prepay_id = element.getStringValue();
				}
			}
		} catch (Exception e) {
			FastLog.error("调用WechatPayServiceImpl.prepay报错：", e);
		}

		return prepay_id;
	}
	
	@Override
	public Result orderRefund(Integer orderid, BigDecimal refundMoney) {
		Result result = new Result();
		HashMap<String, Object> map = new HashMap<>();
		try {
			MOrder order = orderMapper.selectByPrimaryKey(orderid);
			order.setRetuenpaystatus(Byte.valueOf("1"));
			orderMapper.updateByPrimaryKeySelective(order);
			MPublicplatform publicplatform = publicplatformMapper.selectByPrimaryKey(order.getPublicplatformid());
			
			// 公众账号 ID
			String appid = publicplatform.getAppid();
			// 商户号
			String mch_id = publicplatform.getMchid();
			// 商户密钥
			String key = publicplatform.getMchkey();
			// 随机字符串
			String nonce_str = String.valueOf(System.currentTimeMillis() / 1000);			
			// 商户订单号
			String out_trade_no = order.getNo();			
			// 商户退款单号
			String out_refund_no = String.valueOf(System.currentTimeMillis());			
			// 订单总金额，单位为分
			BigDecimal total_fee = order.getPaymoney().multiply(new BigDecimal(100));
			total_fee = total_fee.setScale(0, BigDecimal.ROUND_UP);			
			// 退款总金额，单位为分
			BigDecimal refund_fee = refundMoney.multiply(new BigDecimal(100));
			refund_fee = refund_fee.setScale(0, BigDecimal.ROUND_DOWN);			
			// 操作员帐号, 默认为商户号
			String op_user_id = mch_id;
			
			// 退款资金来源
			// REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
			// REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
			String refund_account = "REFUND_SOURCE_RECHARGE_FUNDS";
			
			// 签名
			SortedMap<String, String> sortedMap = new TreeMap<String, String>();
			sortedMap.put("appid", appid);
			sortedMap.put("mch_id", mch_id);
			sortedMap.put("out_trade_no", out_trade_no);
			sortedMap.put("out_refund_no", out_refund_no);
			sortedMap.put("total_fee", total_fee.toString());
			sortedMap.put("refund_account", refund_account);
			sortedMap.put("refund_fee", refund_fee.toString());
			sortedMap.put("op_user_id", op_user_id);
			sortedMap.put("nonce_str", nonce_str);
			String sign = CommonUtil.createSign(sortedMap, key);
			
			String xml = "<xml>" 
					+ "<appid>" + appid + "</appid>" 
					+ "<mch_id>" + mch_id + "</mch_id>"
					+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
					+ "<out_refund_no>" + out_refund_no + "</out_refund_no>"
					+ "<total_fee>" + total_fee + "</total_fee>"
					+ "<refund_account>" + refund_account + "</refund_account>"
					+ "<refund_fee>" + refund_fee + "</refund_fee>"
					+ "<op_user_id>" + op_user_id + "</op_user_id>"
					+ "<nonce_str>" + nonce_str + "</nonce_str>"
					+ "<sign><![CDATA[" + sign + "]]></sign>" 
					+ "</xml>";
			
			String URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
			try {
				String xmlString = ClientCustomSSL.doRefund(URL, xml,mch_id,publicplatform.getCertpath());				
				Document doc = DocumentHelper.parseText(xmlString);				
				for (Iterator iterator = doc.getRootElement().elementIterator(); iterator.hasNext();) {
	            	Element element = (Element) iterator.next();
	            	System.out.println(element.getName()+":"+element.getStringValue());
	            	map.put(element.getName(), element.getText());
	            }
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			result.setData(map);
			result.setErrcode(Integer.valueOf(0));
			FastLog.debug(com.alibaba.fastjson.JSONObject.toJSONString(map));
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用WechatPayServiceImpl.orderRefund报错：", e);
		}
		
		return result;
	}

}
