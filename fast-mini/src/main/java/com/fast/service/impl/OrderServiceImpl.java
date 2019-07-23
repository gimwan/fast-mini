package com.fast.service.impl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MOrderMapper;
import com.fast.base.data.entity.MOrder;
import com.fast.base.data.entity.MOrderExample;
import com.fast.base.page.PagingView;
import com.fast.service.IDataService;
import com.fast.service.IOrderService;
import com.fast.system.log.FastLog;
import com.fast.util.CommonUtil;

/**
 * 订单
 * @author J
 *
 */
@Service
public class OrderServiceImpl implements IOrderService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MOrderMapper orderMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IDataService iDataService;

	@Override
	public Result order() {
		Result result = new Result();

		try {
			MOrderExample example = new MOrderExample();
			example.setOrderByClause("createtime desc");
			List<MOrder> list = orderMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderServiceImpl.order报错：", e);
		}

		return result;
	}

	@Override
	public Result list(PagingView page) {
		Result result = new Result();

		try {
			page.setOrderBy("order by createtime desc,no desc");
			String sql = "select * from m_order";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql, page);
			list = CommonUtil.transformUpperCase(list);
			list = iDataService.completeData(list, "order");
			page.setRecords(list);
			result.setErrcode(0);
			result.setData(page);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderServiceImpl.list报错：", e);
		}

		return result;
	}

}
