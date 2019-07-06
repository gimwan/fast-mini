package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MUser;
import com.fast.base.data.entity.MViptype;

/**
 * 会员等级
 * @author J
 *
 */
public interface IViptypeMaintService {
	
	public Result changeVipType(MViptype viptype, MUser user);

}
