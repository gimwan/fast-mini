package com.fast.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MVipminiExample;
import com.fast.service.IMiniProgramService;
import com.fast.service.IVipService;
import com.fast.service.IWechatPayService;
import com.fast.service.IWechatService;
import com.fast.system.log.FastLog;
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
			//MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(vip.getId());
			MOrder order = orderMapper.selectByPrimaryKey(id);
			if (order == null || order.getId() == null) {
				result.setMessage("订单记录不存在");
				return result;
			}
			/*if (order.getCouponid() != null && order.getCouponid().intValue() > 0) {
				MVipcoupon vipcoupon = vipcouponMapper.selectByPrimaryKey(id);
				if (vipcoupon.getUseflag().intValue() == 1) {
					result.setMessage("优惠券已使用");
					return result;
				}
			}
			if (vipaccount.getPoint() < order.getPoint()) {
				result.setMessage("积分不足");
				return result;
			}
			if (order.getDeposit().compareTo(vipaccount.getDeposit()) > 0) {
				result.setMessage("储值不足");
				return result;
			}*/
			
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

}
