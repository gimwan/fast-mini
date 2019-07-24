package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.data.dao.MConfigMapper;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MConfigExample;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MMiniprogramExample;
import com.fast.service.IWechatService;
import com.fast.system.RedisCache;
import com.fast.system.log.FastLog;
import com.fast.util.AESDecodeUtils;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

@Service
public class WechatServiceImpl implements IWechatService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MConfigMapper mConfigMapper;
	
	@Autowired
	MMiniprogramMapper mMiniprogramMapper;

	@Override
	public HashMap<String, String> componentParameter() {
		HashMap<String, String> map = new HashMap<>();
		String ComponentAppid = "";
		String ComponentAppsecret = "";
		String ComponentAccessToken = "";
		String ComponentEncodingaeskey = "";
		
		try {
			List<String> codeList = new ArrayList<>();
			codeList.add("5001");
			codeList.add("5002");
			codeList.add("5003");
			codeList.add("5004");
			MConfigExample example = new MConfigExample();
			example.createCriteria().andCodeIn(codeList);
			List<MConfig> list = mConfigMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (codeList.get(0).equals(list.get(i).getCode())) {
						ComponentAppid = list.get(i).getValue();
					}
					else if (codeList.get(1).equals(list.get(i).getCode())) {
						ComponentAppsecret = list.get(i).getValue();
					}
					else if (codeList.get(2).equals(list.get(i).getCode())) {
						 ComponentAccessToken = list.get(i).getValue();
					}
					else if (codeList.get(3).equals(list.get(i).getCode())) {
						ComponentEncodingaeskey = list.get(i).getValue();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		map.put("ComponentAppid", ComponentAppid);
		map.put("ComponentAppsecret", ComponentAppsecret);
		map.put("ComponentAccessToken", ComponentAccessToken);
		map.put("ComponentEncodingaeskey", ComponentEncodingaeskey);
		
		return map;
	}
	
	@Override
	public JSONObject authorizationInfo(String componentAppid, String authorizationCode, String componentAccessToken) {
		String authUrl = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=COMPONENTACCESSTOKEN";
		authUrl = authUrl.replace("COMPONENTACCESSTOKEN", componentAccessToken);
		JSONObject object = new JSONObject();
		object.put("component_appid", componentAppid);
		object.put("authorization_code", authorizationCode);
		JSONObject jsonObject = CommonUtil.httpsRequest(authUrl, "POST", object.toString());
		return jsonObject;
	}
	
	@Override
	public String ComponentVerifyTicket() {
		String ComponentVerifyTicket = "";
		String verifyTicket = (String) RedisCache.get("ComponentVerifyTicket");
		if (verifyTicket != null && !"".equals(verifyTicket.trim())) {
			ComponentVerifyTicket = verifyTicket;
		}
		return ComponentVerifyTicket;
	}
	
	@Override
	public String ComponentAccessToken() {
		String ComponentAccessToken = (String) RedisCache.get("ComponentAccessToken");
		if (ComponentAccessToken == null || "".equals(ComponentAccessToken.trim())) {
			String ComponentVerifyTicket = ComponentVerifyTicket();
			String ComponentAppid = "";
			String ComponentAppsecret = "";
			List<String> codeList = new ArrayList<>();
			codeList.add("8001");
			codeList.add("8002");
			MConfigExample example = new MConfigExample();
			example.createCriteria().andCodeIn(codeList);
			List<MConfig> list = mConfigMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (codeList.get(0).equals(list.get(i).getCode())) {
						ComponentAppid = list.get(i).getValue();
					}
					else if (codeList.get(1).equals(list.get(i).getCode())) {
						ComponentAppsecret = list.get(i).getValue();
					}
				}
			}
			
			JSONObject jObject = new JSONObject();
			jObject.put("component_appid", ComponentAppid);
			jObject.put("component_appsecret", ComponentAppsecret);
			jObject.put("component_verify_ticket", ComponentVerifyTicket);
			String requestUrl = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
			JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jObject.toString());
			if (jsonObject != null && !jsonObject.isEmpty() && jsonObject.has("component_access_token")) {
				ComponentAccessToken = jsonObject.getString("component_access_token");
				RedisCache.set("ComponentAccessToken", ComponentAccessToken, Long.valueOf("7000"));
			}
		}
		return ComponentAccessToken;
	}
	
	@Override
	public String preAuthCode(String componentAppid, String componentAccessToken) {
		String preAuthCode = "";
		String preAuthCodeUri = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token=COMPONENTACCESSTOKEN";
		preAuthCodeUri = preAuthCodeUri.replace("COMPONENTACCESSTOKEN", componentAccessToken);
		JSONObject object = new JSONObject();
		object.put("component_appid", componentAppid);
		JSONObject jsonObject = CommonUtil.httpsRequest(preAuthCodeUri, "POST", object.toString());
		if (jsonObject != null && !jsonObject.isEmpty() && jsonObject.has("pre_auth_code")) {
			preAuthCode = jsonObject.getString("pre_auth_code");
		}
		return preAuthCode;
	}
	
	public JSONObject jscode2session(String appid, String appsecret, String code) {
		JSONObject jsonObject = new JSONObject();

		try {
			String requestUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
			requestUrl = requestUrl.replace("APPID", appid).replace("SECRET", appsecret).replace("JSCODE", code);
			jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

			// 把openid、session_key、unionid存入缓存
			String openid = "";
			if (jsonObject != null && jsonObject.has("openid")) {
				openid = jsonObject.getString("openid");
			}

			String session_key = "";
			if (jsonObject != null && jsonObject.has("session_key")) {
				session_key = jsonObject.getString("session_key");
			}

			String unionid = "";
			if (jsonObject != null && jsonObject.has("unionid")) {
				unionid = jsonObject.getString("unionid");
			}
			
			/*if (openid != null && !"".equals(openid.trim())) {
				JSONObject jObject = new JSONObject();
				
				Object object = RedisCache.get(openid);
				if (object != null) {
					jObject = JSONObject.fromObject(object.toString());
				}
				
				jObject.put("openid", openid);
				jObject.put("session_key", session_key);
				jObject.put("unionid", unionid);

				RedisCache.set(openid, jObject, Long.valueOf("600"));
			}*/
		} catch (Exception e) {
			FastLog.error("调用WeChatServiceImpl.jscode2session报错：", e);
		}

		return jsonObject;
	}
	
	public JSONObject decryptPhoneNumber(String sessionKey, String ivData, String encrypData) {
		JSONObject jsonObject = new JSONObject();

		try {
			byte[] encryp_Data = Base64.decodeBase64(encrypData);
			byte[] iv_Data = Base64.decodeBase64(ivData);
			byte[] session_Key = Base64.decodeBase64(sessionKey);
			String result = AESDecodeUtils.decrypt(session_Key, iv_Data, encryp_Data);
			if (result != null && !"".equals(result)) {
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			FastLog.error("调用WeChatServiceImpl.queryPhoneNumber报错：", e);
		}

		return jsonObject;
	}
	
	@Override
	public String queryAppSecret(String appid) {
		String appsecret = "";

		try {
			MMiniprogramExample example = new MMiniprogramExample();
			example.createCriteria().andAppidEqualTo(appid.trim()).andUseflagEqualTo(Byte.valueOf("1"));
			List<MMiniprogram> list = mMiniprogramMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				appsecret = list.get(0).getAppsecret() == null ? "" : list.get(0).getAppsecret().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
			FastLog.error("调用WeChatServiceImpl.queryAppSecret报错：", e);
		}

		return appsecret;
	}

}
