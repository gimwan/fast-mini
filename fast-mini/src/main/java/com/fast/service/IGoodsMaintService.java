package com.fast.service;

import com.fast.base.Result;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MUser;

/**
 * 商品
 * @author J
 *
 */
public interface IGoodsMaintService {
	
	/**
	 * 修改商品
	 * @param goods
	 * @param user
	 * @return
	 */
	public Result changeGoods(MGoods goods, MUser user);
	
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	public Result deleteGoods(Integer id);
	
	/**
	 * 上下架
	 * @param user 操作者
	 * @param id 商品id
	 * @param onsale 上下架标志
	 * @return
	 */
	public Result onsaleGoods(MUser user, Integer id, Integer onsale);
	
	/**
	 * 商品主图/细节图
	 * @param user 操作者
	 * @param goodsid 商品id
	 * @param images 图片集合字符串
	 * @param groups 分组集合字符串
	 * @return
	 */
	public Result goodsImages(MUser user, Integer goodsid, String images, String groups);
	
	/**
	 * 保存商品sku信息
	 * @param user
	 * @param goodsid
	 * @param skus
	 * @return
	 */
	public Result saveGoodsSku(MUser user, Integer goodsid, String skus);

}
