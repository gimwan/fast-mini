package com.fast.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.data.dao.MOrderMapper;
import com.fast.service.IOrderMaintService;

/**
 * 订单
 * @author J
 *
 */
@Service
public class OrderMaintServiceImpl implements IOrderMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MOrderMapper orderMapper;

}
