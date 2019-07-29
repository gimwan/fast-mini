package com.fast.controller.open;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fast.base.Result;
import com.fast.service.IConfigService;
import com.fast.service.IRegionService;
import com.fast.util.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.sf.json.JSONObject;

/**
 * 基础数据
 * @author J
 *
 */
@Controller
public class Base extends MiniMaster {
	
	@Autowired
	IConfigService iConfigService;
	
	@Autowired
	IRegionService iRegionService;
	
	/**
	 * 基础参数
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/config")
	@ResponseBody
	public String config(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			List<String> codeList = new ArrayList<>();
			codeList.add("1001");
			codeList.add("1002");
			codeList.add("1003");
			codeList.add("1004");
			codeList.add("1005");
			//codeList.add("1006");
			codeList.add("1007");
			codeList.add("2001");
			codeList.add("2002");
			codeList.add("3001");
			codeList.add("3002");
			codeList.add("4001");
			
			Result result = iConfigService.queryConfigByCodeList(codeList);
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 省市区县
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/region")
	@ResponseBody
	public String region(HttpServletRequest request, HttpServletResponse response) {
		String r = "";
		
		try {
			
			Result result = iRegionService.region();
			
			JSONObject jsonObject = JSONObject.fromObject(result);
			r = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}
	
	/**
	 * 二维码
	 * @param request
	 * @param response
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/qrcode")
	@ResponseBody
	public void qrcode(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			
			response.addHeader("Content-Disposition", "attachment;filename=barcode.jpg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			
			int width = 600;
			int height = 600;
			
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "ISO-8859-1");
			hints.put(EncodeHintType.MARGIN, 0);
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.QR_CODE, width, height, hints);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 条形码
	 * @param request
	 * @param response
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/barcode")
	@ResponseBody
	public void barcode(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");

			response.addHeader("Content-Disposition", "attachment;filename=barcode.jpg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			
			int width = 800;
			int height = 200;

			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "ISO-8859-1");
			hints.put(EncodeHintType.MARGIN, 0);
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
			
			MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
			BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.CODE_128, width, height, hints);
			BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
