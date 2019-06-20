package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.fast.base.data.entity.MMicropageset;
import com.fast.base.data.entity.MMicropagesetExample;
import com.fast.base.data.entity.MMicropagesetdtl;
import com.fast.base.data.entity.MMicropagesetdtlExample;
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
			
			List<HashMap<String, Object>> microData = new ArrayList<>();
			MMicropagesetExample example = new MMicropagesetExample();
			example.createCriteria().andMicropageidEqualTo(micropage.getId());
			example.setOrderByClause("index asc");
			List<MMicropageset> list = micropagesetMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				List<Integer> idList = new ArrayList<>();
				for (int i = 0; i < list.size(); i++) {
					idList.add(list.get(i).getId());
				}
				MMicropagesetdtlExample dtlExample = new MMicropagesetdtlExample();
				dtlExample.createCriteria().andMicropagesetidIn(idList);
				dtlExample.setOrderByClause("index asc");
				List<MMicropagesetdtl> dtlList = micropagesetdtlMapper.selectByExample(dtlExample);
				
				for (int i = 0; i < list.size(); i++) {
					MMicropageset micropageset = list.get(i);
					HashMap<String, Object> map = new HashMap<>();
					map.put("id", micropageset.getId());
					map.put("kind", micropageset.getKind());
					map.put("index", micropageset.getIndex());
					map.put("showname", micropageset.getShowname());
					map.put("showprice", micropageset.getShowprice());
					map.put("imagestyle", micropageset.getImagestyle());
					map.put("orderby", micropageset.getOrderby());
					
					List<HashMap<String, Object>> childsList = new ArrayList<>();
					for (int j = 0; j < dtlList.size(); j++) {
						MMicropagesetdtl micropagesetdtl = dtlList.get(j);
						if (micropagesetdtl.getMicropagesetid().intValue() == micropageset.getId().intValue()) {
							HashMap<String, Object> childMap = new HashMap<String, Object>();
							childMap.put("id", micropagesetdtl.getId());
							childMap.put("index", micropagesetdtl.getIndex());
							childMap.put("first", micropagesetdtl.getFirst());
							childMap.put("second", micropagesetdtl.getSecond());
							childMap.put("third", micropagesetdtl.getThird());
							childMap.put("text", micropagesetdtl.getText());
							childMap.put("photourl", micropagesetdtl.getPhotourl());
							childMap.put("targetpath", micropagesetdtl.getTargetpath());
							childMap.put("type", micropagesetdtl.getType());
							childsList.add(childMap);
						}
					}
					
					map.put("childs", childsList);
				}
			}
			
			result.setErrcode(Integer.valueOf(0));
			result.setData(microData);
			result.setId(micropage.getId());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MicropageServiceImpl.micropage报错：", e);
		}

		return result;
	}

}
