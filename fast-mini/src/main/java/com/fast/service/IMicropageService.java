package com.fast.service;

import java.math.BigDecimal;

import com.fast.base.Result;
import com.fast.base.page.PagingView;

/**
 * 微页面
 * @author J
 *
 */
public interface IMicropageService {
	
	/**
	 * 获取指定公众号的微页面
	 * @param page
	 * @param publicPlatformID  公众号id
	 * @return
	 */
	public Result list(PagingView page, Integer publicPlatformID);
	
	/**
	 * 获取微页面数据
	 * @param appid 小程序appid
	 * @param pageid 微页面id
	 * @param openid 会员openid
	 * @param ip ip地址
	 * @return
	 */
	public Result micropage(String appid, Integer pageid, String openid, String ip);
	
	/**
	 * 获取微页面数据
	 * @param pageID 微页面id
	 * @param discount 会员折扣
	 * @param isDraft 是否草稿
	 * @return
	 */
	public Result queryPageData(Integer pageID, BigDecimal discount, boolean isDraft);

}
