package com.fast.service;

import com.fast.base.Result;

/**
 * 积分流水
 * @author J
 *
 */
public interface IVipPointRecordMaintService {
	
	/**
	 * 记录积分流水
	 * @param vipid
	 * @param point
	 * @param surplusPoint
	 * @param refid
	 * @param type
	 * @param reason
	 * @return
	 */
	public Result markdownVipPointRecord(Integer vipid, Integer point, Integer surplusPoint, Integer refid, Byte type, String reason);

}
