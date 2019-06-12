package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MVip;

public interface IVipMaintService {
	
	public Result bind(String appid, String openid, MVip vip);

}
