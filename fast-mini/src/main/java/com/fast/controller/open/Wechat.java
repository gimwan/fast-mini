package com.fast.controller.open;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.service.IWechatService;
import com.fast.system.RedisCache;
import com.fast.system.log.FastLog;

import net.sf.json.JSONObject;

/**
 * 微信
 * @author J
 *
 */
@Controller
public class Wechat extends MiniMaster {
	
	@Autowired
	IWechatService iWechatService;
	
	/**
	 * 登录凭证校验
	 * 临时登录凭证校验接口是一个 HTTPS 接口，开发者服务器使用 临时登录凭证code 获取 session_key 和 openid 等。
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/jscode2session")
	@ResponseBody
	public String jscode2session(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		
		try {
			String appid = request.getParameter("appid");
			String code = request.getParameter("code");
			String appsecret = iWechatService.queryAppSecret(appid);
					
			jsonObject = iWechatService.jscode2session(appid, appsecret, code);
		} catch (Exception e) {
			e.printStackTrace();
			FastLog.error("jscode2session出错：" + e);
		}
		
		return jsonObject.toString();
	}
	
	/**
	 * 手机号解密
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/decryptphone")
	@ResponseBody
	public String decryptphone(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		
		try {
			String openid = request.getParameter("openid");
			Object ob = RedisCache.get(openid);
			JSONObject jObject = new JSONObject();
			if (ob != null) {
				jObject = JSONObject.fromObject(ob.toString());
			}
			String sessionKey = jObject.getString("session_key");
			String ivData = request.getParameter("iv");
			String encrypData = request.getParameter("encrypteddata");
			
			jsonObject = iWechatService.decryptPhoneNumber(sessionKey, ivData, encrypData);
		} catch (Exception e) {
			e.printStackTrace();
			FastLog.error("decryptphone出错：" + e);
		}
		
		return jsonObject.toString();
	}

}
