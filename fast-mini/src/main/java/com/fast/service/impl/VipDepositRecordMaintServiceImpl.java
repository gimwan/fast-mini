package com.fast.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MVipdepositrecordMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.base.data.entity.MVipdepositrecord;
import com.fast.service.IVipDepositRecordMaintService;
import com.fast.service.ext.IExtMaintService;
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
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	@Autowired
	IExtMaintService iExtMaintService;

	@Override
	public Result markdownVipDepositRecord(Integer vipid, BigDecimal deposit, BigDecimal surplusDeposit, Integer refid, Byte type, String reason) {
		Result result = new Result();

		try {
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				MExtsystem extsystem = extList.get(0);
				return iExtMaintService.changeVipDeposit(extsystem, vipid, deposit, reason);
			}
			
			MVipdepositrecord vipdepositrecord = new MVipdepositrecord();
			vipdepositrecord.setVipid(vipid);
			vipdepositrecord.setType(type);
			vipdepositrecord.setRefid(refid);
			vipdepositrecord.setUpdatedtime(new Date());
			vipdepositrecord.setDeposit(deposit);
			vipdepositrecord.setNewdeposit(surplusDeposit);
			vipdepositrecord.setMemo(reason);
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
