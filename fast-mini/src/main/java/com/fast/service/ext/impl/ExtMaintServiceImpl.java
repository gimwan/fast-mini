package com.fast.service.ext.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fast.base.Result;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;
import com.fast.service.ext.IExtMaintService;
import com.fast.service.ext.IExtService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 外部接口
 * @author J
 *
 */
@Service
public class ExtMaintServiceImpl implements IExtMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	IExtService iExtService;
	
	@Autowired
	MColorMapper colorMapper;

	/**
	 * 同步数据
	 */
	@Override
	public Result synchronize(String type) {
		Result result = new Result();

		try {
			Result r = iExtService.synchronizeQuery(type);
			if (Common.isActive(r)) {
				List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) r.getData();
				if ("colorlist".equalsIgnoreCase(type)) {
					result = saveColor(list);
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.synchronize报错：", e);
		}

		return result;
	}
	
	/**
	 * 颜色
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveColor(List<HashMap<String, Object>> list) {
		Result result = new Result();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Date now = new Date();
				HashMap<String, Object> map = list.get(i);
				String extid = map.get("id") == null ? "" : map.get("id").toString().trim();
				String code = map.get("code") == null ? "" : map.get("code").toString().trim();
				String name = map.get("name") == null ? "" : map.get("name").toString().trim();
				
				MColor color = new MColor();
				MColorExample example = new MColorExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MColor> colorList = colorMapper.selectByExample(example);
				if (colorList != null && colorList.size() > 0) {
					color = colorList.get(0);
					color.setModifytime(now);
					color.setModifier("system");
				} else {
					color.setCreatetime(now);
					color.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					color.setCode(code);
				}
				if (!Common.isEmpty(name)) {
					color.setName(name);
				}
				color.setExtid(extid);
				color.setUpdatedtime(now);
				if (color.getId() != null) {
					colorMapper.insertSelective(color);
				} else {
					colorMapper.updateByPrimaryKeySelective(color);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}

}
