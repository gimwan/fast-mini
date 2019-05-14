package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.data.dao.MConfigMapper;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MConfigExample;
import com.fast.service.IWechatService;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

@Service
public class WechatServiceImpl implements IWechatService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MConfigMapper mConfigMapper;

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
		String verifyTicket = "";//iCacheService.get("ComponentVerifyTicket");
		if (verifyTicket != null && !"".equals(verifyTicket.trim())) {
			ComponentVerifyTicket = verifyTicket;
		}
		return ComponentVerifyTicket;
	}
	
	@Override
	public String ComponentAccessToken() {
		String ComponentAccessToken = "";//iCacheService.get("ComponentAccessToken");
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
				//iCacheService.set("ComponentAccessToken", ComponentAccessToken, 7000);
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

}
