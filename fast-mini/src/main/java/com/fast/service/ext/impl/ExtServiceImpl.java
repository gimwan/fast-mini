package com.fast.service.ext.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.service.ext.IExtService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * 外部接口
 * @author J
 *
 */
@Service
public class ExtServiceImpl implements IExtService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MExtsystemMapper extsystemMapper;

	@Override
	public Result colorList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/color/list";
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.colorList报错：", e);
		}

		return result;
	}

	@Override
	public Result colorOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/color/list";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.colorOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result synchronizeQuery(String type) {
		Result result = new Result();

		try {
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			if ("colorlist".equalsIgnoreCase(type)) {
				result = colorList(extsystem);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.synchronize报错：", e);
		}

		return result;
	}

}
