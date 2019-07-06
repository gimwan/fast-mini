package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MPublicplatform;
import com.fast.base.data.entity.MUser;

/**
 * 公众号
 * @author J
 *
 */
public interface IPublicPlatformMaintService {
	
	public Result changePublicplatform(MPublicplatform publicplatform, MUser user);

}
