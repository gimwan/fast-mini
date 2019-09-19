package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MVippointrecordMapper;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVippointrecord;
import com.fast.service.IVipPointRecordMaintService;
import com.fast.system.log.FastLog;

/**
 * 积分流水
 * @author J
 *
 */
@Service
public class VipPointRecordMaintServiceImpl implements IVipPointRecordMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MVippointrecordMapper vippointrecordMapper;

	@Override
	public Result markdownVipPointRecord(Integer vipid, Integer point, Integer surplusPoint, Integer refid, Integer type, String reason) {
		Result result = new Result();

		try {
			MVippointrecord vippointrecord = new MVippointrecord();
			vippointrecord.setVipid(vipid);
			vippointrecord.setType(Byte.valueOf("1"));
			vippointrecord.setRefid(refid);
			vippointrecord.setUpdatedtime(new Date());
			vippointrecord.setPoint(point);
			vippointrecord.setNewpoint(surplusPoint);
			vippointrecord.setMemo(reason);
			vippointrecordMapper.insertSelective(vippointrecord);
			
			result.setErrcode(Integer.valueOf(0));
			result.setId(vippointrecord.getId());
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用VipPointRecordMaintServiceImpl.markdownVipPointRecord报错：", e);
		}

		return result;
	}

}
