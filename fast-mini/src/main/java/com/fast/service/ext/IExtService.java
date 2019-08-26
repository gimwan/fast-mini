package com.fast.service.ext;

import com.fast.base.Result;
import com.fast.base.data.entity.MExtsystem;

/**
 * 外部接口
 * @author J
 *
 */
public interface IExtService {
	
	/**
	 * 所有颜色
	 * @param extsystem
	 * @return
	 */
	public Result colorList(MExtsystem extsystem);
	
	/**
	 * 单个颜色
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result colorOne(MExtsystem extsystem, String extid);
	
	/**
	 * 查询数据
	 * @param type
	 * @return
	 */
	public Result synchronizeQuery(String type);

}
