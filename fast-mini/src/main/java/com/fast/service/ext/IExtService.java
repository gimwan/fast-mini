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
	 * 所有尺码
	 * @param extsystem
	 * @return
	 */
	public Result sizeList(MExtsystem extsystem);
	
	/**
	 * 单个尺码
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result sizeOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有品牌
	 * @param extsystem
	 * @return
	 */
	public Result brandList(MExtsystem extsystem);
	
	/**
	 * 单个品牌
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result brandOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有大类
	 * @param extsystem
	 * @return
	 */
	public Result categoryList(MExtsystem extsystem);
	
	/**
	 * 单个大类
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result categoryOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有中类
	 * @param extsystem
	 * @return
	 */
	public Result midcategoryList(MExtsystem extsystem);
	
	/**
	 * 单个中类
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result midcategoryOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有门店
	 * @param extsystem
	 * @return
	 */
	public Result departmentList(MExtsystem extsystem);
	
	/**
	 * 单个门店
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result departmentOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有员工
	 * @param extsystem
	 * @return
	 */
	public Result employeeList(MExtsystem extsystem);
	
	/**
	 * 单个员工
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result employeeOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有会员等级
	 * @param extsystem
	 * @return
	 */
	public Result viptypeList(MExtsystem extsystem);
	
	/**
	 * 单个会员等级
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result viptypeOne(MExtsystem extsystem, String extid);
	
	/**
	 * 所有优惠券
	 * @param extsystem
	 * @return
	 */
	public Result couponList(MExtsystem extsystem);
	
	/**
	 * 单个优惠券
	 * @param extsystem
	 * @param extid
	 * @return
	 */
	public Result couponOne(MExtsystem extsystem, String extid);
	
	/**
	 * 查询数据
	 * @param type
	 * @return
	 */
	public Result synchronizeQuery(String type);

}
