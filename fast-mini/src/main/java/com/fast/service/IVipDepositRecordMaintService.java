package com.fast.service;

import java.math.BigDecimal;

import com.fast.base.Result;

/**
 * 储值流水
 * @author J
 *
 */
public interface IVipDepositRecordMaintService {
	
	/**
	 * 记录储值流水
	 * @param vipid
	 * @param deposit
	 * @param surplusDeposit
	 * @param refid
	 * @param type
	 * @return
	 */
	public Result markdownVipDepositRecord(Integer vipid, BigDecimal deposit, BigDecimal surplusDeposit, Integer refid, Integer type);

}
