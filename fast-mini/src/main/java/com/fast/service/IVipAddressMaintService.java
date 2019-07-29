package com.fast.service;

import com.fast.base.Result;

/**
 * 会员收货地址
 * @author J
 *
 */
public interface IVipAddressMaintService {
	
	/**
	 * 新增/修改收货地址
	 * @param appid  小程序appid
	 * @param openid  会员openid
	 * @param id  地址id,id>0为修改，否则新增
	 * @param receiver  收货人
	 * @param phone  电话
	 * @param provinceid  省份id
	 * @param cityid  城市id
	 * @param countyid  区县id
	 * @param address  详细地址
	 * @param isDefault  是否默认
	 * @return
	 */
	public Result saveVipAddress(String appid, String openid, Integer id, String receiver, String phone,
			Integer provinceid, Integer cityid, Integer countyid, String address, Integer isDefault);
	
	/**
	 * 删除收货地址
	 * @param appid
	 * @param openid
	 * @param id
	 * @return
	 */
	public Result deleteVipAddress(String appid, String openid, Integer id);

}
