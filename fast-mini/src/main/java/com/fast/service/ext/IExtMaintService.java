package com.fast.service.ext;

import com.fast.base.Result;

/**
 * 外部接口
 * @author J
 *
 */
public interface IExtMaintService {
	
	/**
	 * 同步数据
	 * @param type
	 * @return
	 */
	public Result synchronize(String type);

}
