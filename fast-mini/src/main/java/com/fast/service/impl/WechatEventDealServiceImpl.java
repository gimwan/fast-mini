package com.fast.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.fast.base.data.entity.wechat.TextMessage;
import com.fast.service.IWechatEventDealService;
import com.fast.service.IWechatService;
import com.fast.system.log.FastLog;
import com.fast.util.CommonUtil;
import com.fast.util.WXBizMsgCrypt;
import com.fast.util.WechatUtils;

import net.sf.json.JSONObject;

@Service
public class WechatEventDealServiceImpl implements IWechatEventDealService, Serializable {
	
	private static final long serialVersionUID = 6012533156880739227L;
	
	@Autowired
	IWechatService iWechatService;

	@Override
	public String processRequest(String msgSignature, String timeStamp, String nonce, InputStream ism, String appId) {
		// xml格式的消息数据
		String respXml = "";
		try {
			// 获取post过来的xml
			StringBuffer sb = new StringBuffer() ;
			InputStreamReader isr = new InputStreamReader(ism);
			BufferedReader br = new BufferedReader(isr);
			String s = "" ;
			while((s=br.readLine())!=null){ 
				sb.append(s) ; 
			} 
			String postData = sb.toString();
			System.out.println("postData:"+postData);
			
			// 检验消息的真实性，并且获取解密后的明文
			HashMap<String, String> map = iWechatService.componentParameter();
			WXBizMsgCrypt pc = new WXBizMsgCrypt(map.get("ComponentAccessToken"), map.get("ComponentEncodingaeskey"), map.get("ComponentAppid"));
			String xmltext = pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
			System.out.println("解密后明文:"+xmltext);
			
			// 获取解密后明文中的参数值
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			
			// 发送方帐号
			String fromUserName = root.getElementsByTagName("FromUserName").item(0).getTextContent();
			// 开发者微信号
			String toUserName = root.getElementsByTagName("ToUserName").item(0).getTextContent();
			// 消息类型
			String msgType = root.getElementsByTagName("MsgType").item(0).getTextContent();
			
			// 全网发布检测
			if("gh_3c884a361561".equals(toUserName)){
				respXml = wechatAllNetworkCheck(root, map.get("ComponentAccessToken"), map.get("ComponentEncodingaeskey"), map.get("ComponentAppid"));
			} else {
				// 事件推送
				if (msgType.equals(WechatUtils.REQ_MESSAGE_TYPE_EVENT)) {
					// 事件类型
					String eventType = root.getElementsByTagName("Event").item(0).getTextContent();
					
					// 关注
					if (eventType.equals(WechatUtils.EVENT_TYPE_SUBSCRIBE)) {
						
					}
					// 取消关注
					else if (eventType.equals(WechatUtils.EVENT_TYPE_UNSUBSCRIBE)) {
						
					}
					// 上报地理位置
					else if (eventType.equals(WechatUtils.EVENT_TYPE_LOCATION)) {
						
					}
					// 扫描带参数二维码
					else if (eventType.equals(WechatUtils.EVENT_TYPE_SCAN)) {
						String eventKey = root.getElementsByTagName("EventKey").item(0).getTextContent();
					}
					// 自定义菜单点击事件
					else if (eventType.equals(WechatUtils.EVENT_TYPE_CLICK)) {
						String eventKey = root.getElementsByTagName("EventKey").item(0).getTextContent();
						
					}
				}
				// 文本消息推送
				else if (msgType.equals(WechatUtils.REQ_MESSAGE_TYPE_TEXT)) {
					// 接收到的文字内容
					String content = root.getElementsByTagName("Content").item(0).getTextContent();
				}
			}
			
			// 将公众平台回复用户的消息加密
			if (respXml!=null && !"".equals(respXml)) {
				respXml = pc.encryptMsg(respXml, timeStamp, nonce);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return respXml;
	}
	
	public String wechatAllNetworkCheck(Element root, String componentAccessToken, String componentEncodingaeskey, String componentAppid) {
		FastLog.info("==========全网发布检测==========");
		String respXml = "";
		// 发送方帐号
		String fromUserName = root.getElementsByTagName("FromUserName").item(0).getTextContent();
		// 开发者微信号
		String toUserName = root.getElementsByTagName("ToUserName").item(0).getTextContent();
		// 消息类型
		String msgType = root.getElementsByTagName("MsgType").item(0).getTextContent();
		
		if ("text".equals(msgType)) {
			String content = root.getElementsByTagName("Content").item(0).getTextContent();
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(WechatUtils.RESP_MESSAGE_TYPE_TEXT);
			
			if (StringUtils.startsWithIgnoreCase(content, "QUERY_AUTH_CODE")) {
				// 模拟粉丝发送文本消息给专用测试公众号，第三方平台方需在5秒内返回空串表明暂时不回复，然后再立即使用客服消息接口发送消息回复粉丝
				FastLog.info("==========文本消息调用客服消息接口回复检测==========");
				 //接下来客服API再回复一次消息
                String authorizationCode = content.split(":")[1];
                // 得到微信授权成功的消息后，应该立刻进行处理！！相关信息只会在首次授权的时候推送过来
                JSONObject jsonObject = iWechatService.authorizationInfo(componentAppid, authorizationCode, componentAccessToken);
                if(jsonObject != null && !jsonObject.isEmpty() && jsonObject.has("authorization_info")){
                    JSONObject object = jsonObject.getJSONObject("authorization_info");
                    String authorizerAccessToken = object.getString("authorizer_access_token");
                    //iCacheService.set("authorizerAccessToken_test", authorizerAccessToken);
                    String msg = authorizationCode + "_from_api";
                    
                    jsonObject = new JSONObject();
					jsonObject.put("touser", fromUserName);
					jsonObject.put("msgtype", "text");
					JSONObject miniObject = new JSONObject();
					miniObject.put("content", msg);
					jsonObject.put("text", miniObject);

					FastLog.info("发送客服消息内容：" + jsonObject.toString());

					String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
					requestUrl = requestUrl.replace("ACCESS_TOKEN", authorizerAccessToken);

					object = CommonUtil.httpsRequest(requestUrl, "POST", jsonObject.toString());
					if (object != null && !object.isEmpty()) {
						FastLog.info("发送客服消息结果 ：" + object.toString());
					}
                }
			} else {
				FastLog.info("==========文本消息检测==========");
				textMessage.setContent(content + "_callback");
				respXml = WechatUtils.messageToXml(textMessage);
			}
		}
		else if("event".equals(msgType)) {
			FastLog.info("=========微信事件调用客服消息接口回复检测==========");
			String authorizerAccessToken_test = "";//iCacheService.get("authorizerAccessToken_test");
			String eventType = root.getElementsByTagName("Event").item(0).getTextContent();
			String msg = eventType + "from_callback";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", fromUserName);
			jsonObject.put("msgtype", "text");
			JSONObject miniObject = new JSONObject();
			miniObject.put("content", msg);
			jsonObject.put("text", miniObject);

			FastLog.info("发送客服消息内容：" + jsonObject.toString());

			String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
			requestUrl = requestUrl.replace("ACCESS_TOKEN", authorizerAccessToken_test);

			JSONObject object = CommonUtil.httpsRequest(requestUrl, "POST", jsonObject.toString());
			if (object != null && !object.isEmpty()) {
				FastLog.info("发送客服消息结果 ：" + object.toString());
			}
		}
		
		return respXml;
	}

}
