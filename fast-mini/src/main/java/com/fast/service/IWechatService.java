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
	
	/**
	 * 登录凭证校验使用 临时登录凭证code 获取 session_key 和 openid 等
	 * @param appid
	 * @param appsecret
	 * @param code
	 * @return
	 */
	public JSONObject jscode2session(String appid, String appsecret, String code);
	
	/**
	 * 获取小程序AppSecret
	 * @param appid
	 * @return
	 */
	public String queryAppSecret(String appid);
	
	/**
	 * 解密获取手机号
	 * @param sessionKey
	 * @param ivData
	 * @param encrypData
	 * @return
	 */
	public JSONObject decryptPhoneNumber(String sessionKey, String ivData, String encrypData);
}
