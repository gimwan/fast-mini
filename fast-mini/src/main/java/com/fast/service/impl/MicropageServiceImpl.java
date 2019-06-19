package com.fast.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMicropageMapper;
import com.fast.base.data.dao.MMicropagesetMapper;
import com.fast.base.data.dao.MMicropagesetdtlMapper;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MMicropageExample;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.service.IMicropageService;
import com.fast.service.IMiniProgramService;
import com.fast.service.IWechatService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

import net.sf.json.JSONObject;

/**
 * 微页面
 * @author J
 *
 */
@Service
public class MicropageServiceImpl implements IMicropageService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMicropageMapper micropageMapper;
	
	@Autowired
	MMicropagesetMapper micropagesetMapper;
	
	@Autowired
	MMicropagesetdtlMapper micropagesetdtlMapper;
	
	@Autowired
	IWechatService iWechatService;
	
	@Autowired
	IMiniProgramService iMiniProgramService;

	@Override
	public Result micropage(String appid, Integer pageid, String openid, String ip) {
		Result result = new Result();

		try {
			MMicropage micropage = null;
			// 指定id的微页面
			if (pageid != null) {
				if (pageid.intValue() > 0) {
					micropage = micropageMapper.selectByPrimaryKey(pageid);
				}
			}
			// 首页
			else {
				Integer publicplatformid = null;
				Result r = iMiniProgramService.queryMiniprogramByAppid(appid);
				if (Common.isActive(r)) {
					MMiniprogram miniprogram = (MMiniprogram) r.getData();
					if (miniprogram != null && miniprogram.getId() != null) {
						publicplatformid = miniprogram.getPublicplatformid();
					}
				}
				
				MMicropageExample example = new MMicropageExample();
				example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andHomeflagEqualTo(Byte.valueOf("1")).andPublicplatformidEqualTo(publicplatformid);
				List<MMicropage> list = micropageMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					micropage = list.get(0);
				}
			}

			if (micropage == null || micropage.getId() == null) {
				result.setMessage("无微页面");
				return result;
			}

			/*Result r = iOmMicroformService.getShowsetByPageId(omMicroform.getId());
			if (r != null && r.getErrcode().intValue() == 0) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) r.getData();

				Integer openidid = iWeChatService.queryOpenidid(appid);
				JSONObject micropageObject = appendMicroPageData(list, omMicroform, openidid);

				result.setErrcode(Integer.valueOf("0"));
				result.setData(micropageObject);
			}
			
			// 纪录访客数、浏览量
			try {
				refreshVisitRecordThread thread = new refreshVisitRecordThread();
				thread.setPageid(omMicroform.getId());
				thread.setIp(ip);
				thread.setOpenid(openid);
				Thread t = new Thread(thread);
	            t.start();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageServiceImpl.micropage报错：", e);
		}

		return result;
	}

}
