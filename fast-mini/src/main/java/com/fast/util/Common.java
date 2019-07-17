package com.fast.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.entity.MUser;
import com.fast.system.RedisCache;

public class Common {
	/**
	 * 判断变量是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s.trim()) || "undefined".equalsIgnoreCase(s.trim())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断接口是否成功返回
	 * @param s
	 * @return
	 */
	public static boolean isActive(Result result) {
		if (result != null && result.getErrcode() != null && result.getErrcode().intValue() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static MUser currentUser(HttpServletRequest request) {
		MUser user = null;
		
		Object object = request.getSession().getAttribute("user");
		/*if (object == null) {
			String sessionid = request.getSession().getId();
			object = RedisCache.retake(sessionid);
		}*/
		if (object != null) {
			user = (MUser) object;
		} 
		
		return user;
	}

	/**
	 * 使用率计算
	 * 
	 * @return
	 */
	public static String fromUsage(long free, long total) {
		Double d = new BigDecimal(free * 100 / total).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return String.valueOf(d);
	}

	/**
	 * 返回当前时间 格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return String
	 */
	public static String fromDateH() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(new Date());
	}

	/**
	 * 返回当前时间 格式：yyyy-MM-dd hh:mm:ss
	 * 
	 * @return String
	 */
	public static String fromDate(Date dd) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format1.format(dd);
	}

	/**
	 * 返回当前时间 格式：yyyy-MM-dd
	 * 
	 * @return String
	 */
	public static String fromDateY() {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(new Date());
	}

	/**
	 * 用来去掉List中空值和相同项的。
	 * 
	 * @param list
	 * @return
	 */
	public static List<String> removeSameItem(List<String> list) {
		List<String> difList = new ArrayList<String>();
		for (String t : list) {
			if (t != null && !difList.contains(t)) {
				difList.add(t);
			}
		}
		return difList;
	}

	/**
	 * 按照"XX-XX-XX-XX-XX-XX"格式，获取本机网卡MAC地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getMacAddress() throws Exception {
		Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();

		while (ni.hasMoreElements()) {
			NetworkInterface netI = ni.nextElement();

			byte[] bytes = netI.getHardwareAddress();
			if (netI.isUp() && netI != null && bytes != null && bytes.length == 6) {
				StringBuffer sb = new StringBuffer();
				for (byte b : bytes) {
					// 与11110000作按位与运算以便读取当前字节高4位
					sb.append(Integer.toHexString((b & 240) >> 4));
					// 与00001111作按位与运算以便读取当前字节低4位
					sb.append(Integer.toHexString(b & 15));
					sb.append("-");
				}
				sb.deleteCharAt(sb.length() - 1);
				return sb.toString().toUpperCase();
			}
		}
		return null;
	}

	/**
	 * 返回用户的公网IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	/**
	 * 返回用户的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String toIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 传入原图名称，，获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            原图名称
	 * @return
	 */
	public static String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	/**
	 * 取得html网页内容 UTF8编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlUTF8(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "UTF-8");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * 取得html网页内容 GBK编码
	 * 
	 * @param urlStr
	 *            网络地址
	 * @return String
	 */
	public static String getInputHtmlGBK(String urlStr) {
		URL url = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection httpsURLConnection = (HttpURLConnection) url.openConnection();

			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setConnectTimeout(5 * 1000);
			httpsURLConnection.connect();
			if (httpsURLConnection.getResponseCode() == 200) {
				// 通过输入流获取网络图片
				InputStream inputStream = httpsURLConnection.getInputStream();
				String data = readHtml(inputStream, "GBK");
				inputStream.close();
				return data;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;

	}

	/**
	 * @param inputStream
	 * @param uncode
	 *            编码 GBK 或 UTF-8
	 * @return
	 * @throws Exception
	 */
	public static String readHtml(InputStream inputStream, String uncode) throws Exception {
		InputStreamReader input = new InputStreamReader(inputStream, uncode);
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		return contentBuf.toString();
	}

	/**
	 * 
	 * @return 返回资源的二进制数据 @
	 */
	public static byte[] readInputStream(InputStream inputStream) {

		// 定义一个输出流向内存输出数据
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		// 定义一个缓冲区
		byte[] buffer = new byte[1024];
		// 读取数据长度
		int len = 0;
		// 当取得完数据后会返回一个-1
		try {
			while ((len = inputStream.read(buffer)) != -1) {
				// 把缓冲区的数据 写到输出流里面
				byteArrayOutputStream.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		// 得到数据后返回
		return byteArrayOutputStream.toByteArray();

	}

	/**
	 * 对应js的unescape编码方法
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 对应js的escape编码方法
	 * 
	 * @param src
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * 编码函数：
	 */
	public static String htmlEncode(String str) {
		String s = "";
		if (str.length() == 0)
			return "";
		s = str.replace("&", "&amp;");
		s = s.replace("<", "&lt;");
		s = s.replace(">", "&gt;");
		s = s.replace("'", "&apos;");
		s = s.replace("\"", "&quot;");
		return s;
	}

	/**
	 * 解码函数:
	 */
	public static String htmlDecode(String str) {
		String s = "";
		if (str.length() == 0)
			return "";
		s = str.replace("&amp;", "&");
		s = s.replace("&lt;", "<");
		s = s.replace("&gt;", ">");
		s = s.replace("&apos;", "'");
		s = s.replace("&quot;", "\"");
		return s;
	}

	/**
	 * 访问接口网址，获取JSon返回值
	 * 
	 * @param url
	 * @return
	 */
	public static JSONObject loadInterface(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
			String errString = e.getMessage();
		} catch (IOException e) {
			String errString = e.getMessage();
		}
		return JSON.parseObject(json.toString());
	}

	/**
	 * 去除小数字符小数位末尾的0
	 * 
	 * @param num
	 * @return
	 */
	public static String removeZeroOfNum(String num) {
		if (num == null || num.equals("") || num.indexOf(".") < 0) {
			return num;
		} else {
			String lstr = num.split("\\.")[0];
			String rstr = num.split("\\.")[1];
			while (1 == 1) {
				if (rstr.endsWith("0")) {
					rstr = rstr.substring(0, rstr.length() - 1);
				} else {
					break;
				}
			}
			if (rstr.equals("") || rstr.equals("0")) {
				return lstr;
			} else {
				return lstr + "." + rstr;
			}
		}
	}

	/**
	 * 获取访问接入链接的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {
		String ipAddress = null;
		// ipAddress = this.getRequest().getRemoteAddr();
		ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.indexOf(":") > 0) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}

		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
															// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	/**
	 * 将request的参数转换为map对象
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getParameterStringMap(HttpServletRequest request) {
		// 参数Map
		Map properties = request.getParameterMap();
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
}
