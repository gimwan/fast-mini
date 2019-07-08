package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MMiniprogramMapper;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MMiniprogramExample;
import com.fast.base.page.PagingView;
import com.fast.service.IMiniProgramService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 小程序
 * @author J
 *
 */
@Service
public class MiniProgramServiceImpl implements IMiniProgramService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MMiniprogramMapper miniprogramMapper;
	
	/**
	 * 查询所有小程序
	 */
	@Override
	public Result miniprogram() {
		Result result = new Result();

		try {
			MMiniprogramExample example = new MMiniprogramExample();
			example.setOrderByClause("code asc");
			List<MMiniprogram> list = miniprogramMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramServiceImpl.miniprogram报错：", e);
		}

		return result;
	}
	
	@Override
	public Result queryMiniprogramByAppid(String appid) {
		Result result = new Result();

		try {
			if (Common.isEmpty(appid)) {
				result.setMessage("appid无效");
				return result;
			}
			
			MMiniprogramExample example = new MMiniprogramExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andAppidEqualTo(appid.trim());
			List<MMiniprogram> list = miniprogramMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setData(list.get(0));
				result.setId(list.get(0).getId());
				result.setErrcode(Integer.valueOf(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramServiceImpl.queryMiniprogramByAppid报错：", e);
		}

		return result;
	}

	@Override
	public Result queryMiniprogramIDByAppid(String appid) {
		Result result = new Result();

		try {
			result = queryMiniprogramByAppid(appid);
			if (result != null && result.getErrcode() != null && result.getErrcode().intValue() == 0) {
				MMiniprogram miniprogram = (MMiniprogram) result.getData();
				if (miniprogram != null) {
					result.setData(miniprogram.getId());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用MiniProgramServiceImpl.queryMiniprogramIDByAppid报错：", e);
		}

		return result;
	}

}
