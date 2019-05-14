package com.fast.service;

import java.util.HashMap;

import net.sf.json.JSONObject;

/**
 * 微信
 * 
 * @author J
 *
 */
public interface IWechatService {

	/**
	 * 获取第三方平台参数
	 * @return
	 */
	public HashMap<String, String> componentParameter();

	/**
	 * 获取授权公众号信息
	 * @param componentAppid
	 * @param authorizationCode
	 * @param componentAccessToken
	 * @return
	 */
	public JSONObject authorizationInfo(String componentAppid, String authorizationCode, String componentAccessToken);
	
	/**
	 * 微信服务器每隔10分钟推送一次的component_verify_ticket
	 * @return
	 */
	public String ComponentVerifyTicket();
	
	/**
	 * 第三方平台accesstoken
	 * @return
	 */
	public String ComponentAccessToken();
	
	/**
	 * 获取预授权码pre_auth_code
	 * @param componentAppid   第三方平台appid
	 * @param componentAccessToken  第三方平台component_access_token
	 * @return
	 */
	public String preAuthCode(String componentAppid, String componentAccessToken);
}
