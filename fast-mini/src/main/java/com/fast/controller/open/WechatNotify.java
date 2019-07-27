package com.fast.controller.open;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.service.IOrderMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.CommonUtil;

/**
 * 微信支付回调
 * @author J
 *
 */
@Controller
public class WechatNotify extends MiniMaster {
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
	/**
	 * 订单支付回调
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/notify/orderpay")
	@ResponseBody
	public void orderpay(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收xml消息体
		String inputLine = null;
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader in = null;
		PrintWriter out = response.getWriter();

		try {
			in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			while ((inputLine = in.readLine()) != null) {
				stringBuffer.append(inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 业务结果
		String result_code = null;
		// 商户订单号
		String out_trade_no = null;
		// 微信支付订单号
		String transaction_id = null;
		// appid
		String appid = null;
		// openid
		String openid = null;

		Document doc = null;
		try {
			doc = CommonUtil.xxeRepair(stringBuffer.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (doc != null) {
			for (Iterator iterator = doc.getRootElement().elementIterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();

				if (element.getName().equals("result_code")) {
					result_code = element.getStringValue();
				}
				if (element.getName().equals("out_trade_no")) {
					out_trade_no = element.getStringValue();
				}
				if (element.getName().equals("transaction_id")) {
					transaction_id = element.getStringValue();
				}
				if (element.getName().equals("appid")) {
					appid = element.getStringValue();
				}
				if (element.getName().equals("openid")) {
					openid = element.getStringValue();
				}
			}
		}

		if ("SUCCESS".equals(result_code)) {
			try {
				// 更新订单状态
				Result result = iOrderMaintService.afterPay(out_trade_no, transaction_id);

				if (result != null && result.getErrcode() != null && result.getErrcode().intValue() == 0) {
					out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				} else {
					try {
						JSONObject jsonObject = (JSONObject) JSONObject.toJSON(result);
						FastLog.error(jsonObject.toString());
					} catch (Exception e) {
						e.printStackTrace();
						FastLog.error("调用WechatNotify.orderpay报错：", e);
					}
					out.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[业务错误]]></return_msg></xml>");
				}

				
			} catch (Exception e) {
				e.printStackTrace();
				FastLog.error("调用WechatNotify.orderpay报错：", e);
				out.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[订单处理错误]]></return_msg></xml>");
			}

		} else {
			out.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[业务错误]]></return_msg></xml>");
		}

		out.close();
		out = null;
	}

}
