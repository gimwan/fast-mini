package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MVipdepositrecordMapper;
import com.fast.base.data.entity.MVipdepositrecord;
import com.fast.service.IVipDepositRecordMaintService;
import com.fast.system.log.FastLog;

/**
 * 储值流水
 * @author J
 *
 */
@Service
public class VipDepositRecordMaintServiceImpl implements IVipDepositRecordMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVipdepositrecordMapper vipdepositrecordMapper;

	@Override
	public Result markdownVipDepositRecord(Integer vipid, BigDecimal deposit, BigDecimal surplusDeposit, Integer refid, Integer type) {
		Result result = new Result();

		try {
			MVipdepositrecord vipdepositrecord = new MVipdepositrecord();
			vipdepositrecord.setVipid(vipid);
			vipdepositrecord.setType(Byte.valueOf("1"));
			vipdepositrecord.setRefid(refid);
			vipdepositrecord.setUpdatedtime(new Date());
			vipdepositrecord.setDeposit(deposit);
			vipdepositrecord.setNewdeposit(surplusDeposit);
			vipdepositrecordMapper.insertSelective(vipdepositrecord);
			
			result.setErrcode(Integer.valueOf(0));
			result.setId(vipdepositrecord.getId());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipDepositRecordMaintServiceImpl.markdownVipDepositRecord报错：", e);
		}

		return result;
	}

}
