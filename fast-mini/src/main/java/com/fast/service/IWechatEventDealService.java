package com.fast.service;

import java.io.InputStream;

/**
 * 微信事件响应
 * @author J
 *
 */
public interface IWechatEventDealService {

	/**
	 * 微信事件处理
	 * @param msgSignature  消息体签名，用于验证消息体的正确性
	 * @param timeStamp  时间戳
	 * @param nonce  随机数
	 * @param ism  post过来的数据
	 * @param appId  公众号appid
	 * @return
	 */
	public String processRequest(String msgSignature, String timeStamp, String nonce, InputStream ism, String appId);

}
