package com.fast.service;

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
	 * @param appid
	 * @param pageid
	 * @param openid
	 * @param ip
	 * @return
	 */
	public Result micropage(String appid, Integer pageid, String openid, String ip);
	
	/**
	 * 获取微页面数据
	 * @param pageID
	 * @param isDraft
	 * @return
	 */
	public Result queryPageData(Integer pageID, boolean isDraft);

}