package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MVip;

/**
 * 会员
 * @author J
 *
 */
public interface IVipMaintService {
	
	/**
	 * 绑定会员
	 * @param appid
	 * @param openid
	 * @param vip
	 * @return
	 */
	public Result bind(String appid, String openid, MVip vip);
	
	/**
	 * 更新会员信息
	 * @param appid
	 * @param openid
	 * @param name  名称
	 * @param nickname  昵称
	 * @param birthday  生日
	 * @param province  省份
	 * @param city  城市
	 * @param county  区县
	 * @param avatarurl  头像
	 * @param gender  性别
	 * @return
	 */
	public Result updateVipInfo(String appid, String openid, String name, String nickname, String birthday,
			String province, String city, String county, String avatarurl, String gender);
	
	/**
	 * 定时任务
	 * 推送会员
	 * @return
	 */
	public Result pushVipTask();

}
