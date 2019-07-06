package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MMiniprogram;
import com.fast.base.data.entity.MUser;

/**
 * 小程序
 * @author J
 *
 */
public interface IMiniProgramMaintService {
	
	public Result changeMiniProgram(MMiniprogram miniprogram, MUser user);

}
