package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MVippointrecordMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.base.data.entity.MVippointrecord;
import com.fast.service.IVipPointRecordMaintService;
import com.fast.service.ext.IExtMaintService;
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
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	@Autowired
	IExtMaintService iExtMaintService;

	@Override
	public Result markdownVipPointRecord(Integer vipid, Integer point, Integer surplusPoint, Integer refid, Byte type, String reason) {
		Result result = new Result();

		try {
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				MExtsystem extsystem = extList.get(0);
				return iExtMaintService.changeVipPoint(extsystem, vipid, point, reason);
			}
			
			MVippointrecord vippointrecord = new MVippointrecord();
			vippointrecord.setVipid(vipid);
			vippointrecord.setType(type);
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
