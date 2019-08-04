package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MMicropage;
import com.fast.base.data.entity.MUser;

/**
 * 微页面
 * @author J
 *
 */
public interface IMicropageMaintService {
	
	/**
	 * 修改微页面
	 * @param micropage
	 * @param user
	 * @return
	 */
	public Result changeMicropage(MMicropage micropage, MUser user);
	
	/**
	 * 删除微页面
	 * @param id
	 * @return
	 */
	public Result deleteMicropage(Integer id);
	
	/**
	 * 保存配置数据
	 * @param pageID
	 * @param setdataStr
	 * @param user
	 * @return
	 */
	public Result saveMicropageSetData(Integer pageID, String setdataStr, MUser user);
	
	/**
	 * 发布
	 * @param pageID
	 * @param user
	 * @return
	 */
	public Result releaseMicroPage(Integer pageID, MUser user);

}
