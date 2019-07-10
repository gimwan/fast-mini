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
	
	/**
	 * 修改公众号
	 * @param publicplatform
	 * @param user
	 * @return
	 */
	public Result changePublicplatform(MPublicplatform publicplatform, MUser user);
	
	/**
	 * 删除公众号
	 * @param id
	 * @return
	 */
	public Result deletePublicplatform(Integer id);

}
