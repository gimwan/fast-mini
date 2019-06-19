package com.fast.controller.open;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.fast.service.IWechatEventDealService;
import com.fast.service.IWechatService;
import com.fast.system.RedisCache;
import com.fast.util.WXBizMsgCrypt;
import com.fast.util.WechatUtils;
/**
 * 微信开放平台
 * @author J
 *
 */
@Controller
@RequestMapping(value="/open", produces = "application/json; charset=utf-8")
public class Open {
	
	@Autowired
	IWechatService iWechatService;
	
	@Autowired
	IWechatEventDealService iWechatEventDealService;
	
	/**
	 * 接收component_verify_ticket和推送取消授权通知
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/event/authorize")
	public void eventAuthorize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resp = "";
		try {
			// 接收URL中的参数
			// 消息体签名，用于验证消息体的正确性
			String msgSignature = request.getParameter("msg_signature");
			// 时间戳
			String timeStamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			
			// 获取post过来的xml
			StringBuffer sb = new StringBuffer() ; 
			InputStream ism = request.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(ism);
			BufferedReader br = new BufferedReader(isr);
			String s = "" ;
			while((s=br.readLine()) != null) {
				sb.append(s) ; 
			} 
			String postData = sb.toString();
			System.out.println("postData:"+postData);
			
			// 检验消息的真实性，并且获取解密后的明文
			HashMap<String, String> map = iWechatService.componentParameter();
			WXBizMsgCrypt pc = new WXBizMsgCrypt(map.get("ComponentAccessToken"), map.get("ComponentEncodingaeskey"), map.get("ComponentAppid"));
			String xmltext = pc.decryptMsg(msgSignature, timeStamp, nonce, postData);
			
			// 获取解密后明文中的参数值
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			
			// 推送消息类型
			String InfoType = root.getElementsByTagName("InfoType").item(0).getTextContent();
			
			if (InfoType != null && !InfoType.equals("")) {
				
				// 每隔10分钟定时推送component_verify_ticket
				if (InfoType.equals("component_verify_ticket")) {
					String ComponentVerifyTicket = root.getElementsByTagName("ComponentVerifyTicket").item(0).getTextContent();
					//iCacheService.set("ComponentVerifyTicket", ComponentVerifyTicket);
					//request.getSession().setAttribute("ComponentVerifyTicket", ComponentVerifyTicket);
					RedisCache.set("ComponentVerifyTicket", ComponentVerifyTicket);
					resp = "success";
				}
				
				// 取消授权
				if (InfoType.equals("unauthorized")) {
					// 取消授权的公众号
					String AuthorizerAppid = root.getElementsByTagName("AuthorizerAppid").item(0).getTextContent();
					
					resp = "success";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(resp);
		out.close();
	}
	
	/**
	 * 消息与事件接收
	 * 确认请求来自微信服务器
	 * @param request
	 * @param response
	 * @param appid
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/{appid}/callback",method = RequestMethod.GET)
	public void getReceive(HttpServletRequest request, HttpServletResponse response, @PathVariable String appid) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		HashMap<String, String> map = iWechatService.componentParameter();
		if (WechatUtils.checkSignature(signature, timestamp, nonce, map.get("ComponentAccessToken"))) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	
	/**
	 * 消息与事件接收
	 * 处理微信服务器发来的消息
	 * @param request
	 * @param response
	 * @param appid
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/{appid}/callback",method = RequestMethod.POST)
	public void postReceive(HttpServletRequest request, HttpServletResponse response, @PathVariable String appid) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		// 消息体签名，用于验证消息体的正确性
		String msgSignature = request.getParameter("msg_signature");
		// 时间戳
		String timeStamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		
		InputStream ism = request.getInputStream();
		String respMessage = iWechatEventDealService.processRequest(msgSignature, timeStamp, nonce, ism, appid);
		
		// 响应消息
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
	
	/**
	 * 一键授权
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/authorization")
	public void authorization(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String openidid = request.getParameter("id");
		
		HashMap<String, String> map = iWechatService.componentParameter();
		String componentAppid = map.get("ComponentAppid");
		String componentAccessToken = iWechatService.ComponentAccessToken();
		String preauthCode = iWechatService.preAuthCode(componentAppid, componentAccessToken);
		String domain = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String redirectUri = domain + "/open/" + openidid + "/authorizedfinish";
		
		String authorization = "https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=COMPONENTAPPID&pre_auth_code=PREAUTHCODE&redirect_uri=REDIRECTURI&auth_type=AUTHTYPE";
		authorization = authorization.replace("COMPONENTAPPID", componentAppid).replace("PREAUTHCODE", preauthCode).replace("REDIRECTURI", redirectUri).replace("AUTHTYPE", "3");
		
		response.sendRedirect(authorization);
	}
	
	/**
	 * 授权完成回调
	 * @param request
	 * @param response
	 * @param openidid
	 * @throws IOException 
	 */
	@RequestMapping(value = "/{openidid}/authorizedfinish")
	public void authorizedfinish(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer openidid) throws IOException {
		//String authorizationCode = request.getParameter("auth_code");
		
		String finishPage = "/authorizedfinish.jsp";
		response.sendRedirect(finishPage);
	
		//String failPage = "/authorizedfail.jsp";
		//response.sendRedirect(failPage);
	}
	
}