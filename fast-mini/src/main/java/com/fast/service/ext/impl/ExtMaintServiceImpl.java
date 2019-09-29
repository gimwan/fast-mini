package com.fast.service.ext.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.dao.DataMapper;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.dao.MConfigMapper;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MDepartmentMapper;
import com.fast.base.data.dao.MEmployeeMapper;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.dao.MOrderMapper;
import com.fast.base.data.dao.MOrderdtlMapper;
import com.fast.base.data.dao.MPatternMapper;
import com.fast.base.data.dao.MSizeMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipcouponMapper;
import com.fast.base.data.dao.MVipdepositrecordMapper;
import com.fast.base.data.dao.MVippointrecordMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;
import com.fast.base.data.entity.MConfig;
import com.fast.base.data.entity.MConfigExample;
import com.fast.base.data.entity.MCoupon;
import com.fast.base.data.entity.MCouponExample;
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MDepartmentExample;
import com.fast.base.data.entity.MEmployee;
import com.fast.base.data.entity.MEmployeeExample;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.base.data.entity.MGoodssku;
import com.fast.base.data.entity.MGoodsskuExample;
import com.fast.base.data.entity.MOrder;
import com.fast.base.data.entity.MPattern;
import com.fast.base.data.entity.MPatternExample;
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MSizeExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipcoupon;
import com.fast.base.data.entity.MVipcouponExample;
import com.fast.base.data.entity.MVipdepositrecord;
import com.fast.base.data.entity.MVipdepositrecordExample;
import com.fast.base.data.entity.MVippointrecord;
import com.fast.base.data.entity.MVippointrecordExample;
import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import com.fast.service.IConfigService;
import com.fast.service.IDepartmentService;
import com.fast.service.IOrderMaintService;
import com.fast.service.IVipService;
import com.fast.service.ext.IExtMaintService;
import com.fast.service.ext.IExtService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

/**
 * 外部接口
 * @author J
 *
 */
@Service
public class ExtMaintServiceImpl implements IExtMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	IExtService iExtService;
	
	@Autowired
	MColorMapper colorMapper;
	
	@Autowired
	MSizeMapper sizeMapper;
	
	@Autowired
	MBrandMapper brandMapper;
	
	@Autowired
	MDepartmentMapper departmentMapper;
	
	@Autowired
	MEmployeeMapper employeeMapper;
	
	@Autowired
	MViptypeMapper viptypeMapper;
	
	@Autowired
	MCouponMapper couponMapper;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	IDepartmentService iDepartmentService;
	
	@Autowired
	MGoodsMapper goodsMapper;
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	@Autowired
	MGoodsskuMapper goodsskuMapper;
	
	@Autowired
	MVipMapper vipMapper;
	
	@Autowired
	MVipaccountMapper vipaccountMapper;
	
	@Autowired
	IVipService iVipService;
	
	@Autowired
	MVipcouponMapper vipcouponMapper;
	
	@Autowired
	MOrderMapper orderMapper;
	
	@Autowired
	MOrderdtlMapper orderdtlMapper;
	
	@Autowired
	DataMapper dataMapper;
	
	@Autowired
	IConfigService iConfigService;
	
	@Autowired
	IOrderMaintService iOrderMaintService;
	
	@Autowired
	MPatternMapper patternMapper;
	
	@Autowired
	MVippointrecordMapper vippointrecordMapper;
	
	@Autowired
	MVipdepositrecordMapper vipdepositrecordMapper;
	
	@Autowired
	MConfigMapper configMapper;
	
	// 推送任务锁
	private boolean pushVipTaskLock = false;
	// 订单自动取消任务锁
	private boolean cancelOrderTaskLock = false;
	// 推送订单任务锁
	private boolean pushOrderTaskLock = false;
	// 推送订单任务锁
	private boolean changeOrderStatusTaskLock = false;
	// 更新积分记录锁
	private boolean updateVipPointRecordLock = false;
	// 更新储值记录锁
	private boolean updateVipDepositRecordLock = false;

	/**
	 * 同步数据
	 */
	@Override
	public Result synchronize(String type) {
		Result result = new Result();

		try {
			Result r = iExtService.synchronizeQuery(type);
			if (Common.isActive(r)) {
				JSONArray array = (JSONArray) r.getData();
				// 颜色
				if ("colorlist".equalsIgnoreCase(type)) {
					result = saveColor(array);
				}
				// 尺码
				else if ("sizelist".equalsIgnoreCase(type)) {
					result = saveSize(array);
				}
				// 品牌
				else if ("brandlist".equalsIgnoreCase(type)) {
					result = saveBrand(array);
				}
				// 大类
				else if ("categorylist".equalsIgnoreCase(type)) {
					result = saveCategory(array);
				}
				// 中类
				else if ("midcategorylist".equalsIgnoreCase(type)) {
					result = saveMidCategory(array);
				}
				// 门店
				else if ("departmentlist".equalsIgnoreCase(type)) {
					result = saveDepartment(array);
				}
				// 员工
				else if ("employeelist".equalsIgnoreCase(type)) {
					result = saveEmployee(array);
				}
				// 会员等级
				else if ("viptypelist".equalsIgnoreCase(type)) {
					result = saveViptype(array);
				}
				// 优惠券
				else if ("couponlist".equalsIgnoreCase(type)) {
					result = saveCoupon(array);
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.synchronize报错：", e);
		}

		return result;
	}
	
	/**
	 * 颜色
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveColor(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				
				MColor color = new MColor();
				MColorExample example = new MColorExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MColor> colorList = colorMapper.selectByExample(example);
				if (colorList != null && colorList.size() > 0) {
					color = colorList.get(0);
					color.setModifytime(now);
					color.setModifier("system");
				} else {
					color.setCreatetime(now);
					color.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					color.setCode(code);
				} else {
					color.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					color.setName(name);
				}
				color.setExtid(extid);
				color.setUpdatedtime(now);
				if (color.getId() != null) {
					colorMapper.updateByPrimaryKeySelective(color);
				} else {
					colorMapper.insertSelective(color);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 尺码
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveSize(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				
				MSize size = new MSize();
				MSizeExample example = new MSizeExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MSize> sizeList = sizeMapper.selectByExample(example);
				if (sizeList != null && sizeList.size() > 0) {
					size = sizeList.get(0);
					size.setModifytime(now);
					size.setModifier("system");
				} else {
					size.setCreatetime(now);
					size.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					size.setCode(code);
				} else {
					size.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					size.setName(name);
				}
				size.setExtid(extid);
				size.setUpdatedtime(now);
				if (size.getId() != null) {
					sizeMapper.updateByPrimaryKeySelective(size);
				} else {
					sizeMapper.insertSelective(size);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 品牌
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveBrand(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				
				MBrand brand = new MBrand();
				MBrandExample example = new MBrandExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MBrand> brandList = brandMapper.selectByExample(example);
				if (brandList != null && brandList.size() > 0) {
					brand = brandList.get(0);
					brand.setModifytime(now);
					brand.setModifier("system");
				} else {
					brand.setCreatetime(now);
					brand.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					brand.setCode(code);
				} else {
					brand.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					brand.setName(name);
				}
				brand.setExtid(extid);
				brand.setUpdatedtime(now);
				if (brand.getId() != null) {
					brandMapper.updateByPrimaryKeySelective(brand);
				} else {
					brandMapper.insertSelective(brand);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 大类
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveCategory(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				
				MGoodscategory category = new MGoodscategory();
				MGoodscategoryExample example = new MGoodscategoryExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MGoodscategory> categoryList = goodscategoryMapper.selectByExample(example);
				if (categoryList != null && categoryList.size() > 0) {
					category = categoryList.get(0);
					category.setModifytime(now);
					category.setModifier("system");
				} else {
					category.setCreatetime(now);
					category.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					category.setCode(code);
				} else {
					category.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					category.setName(name);
				}
				category.setExtid(extid);
				category.setGrade(Byte.valueOf("1"));
				category.setUpdatedtime(now);
				if (category.getId() != null) {
					goodscategoryMapper.updateByPrimaryKeySelective(category);
				} else {
					goodscategoryMapper.insertSelective(category);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 大类
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveMidCategory(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				String parentid = json.get("parentid") == null ? "" : json.getString("parentid").trim();
				
				MGoodscategory category = new MGoodscategory();
				MGoodscategoryExample example = new MGoodscategoryExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MGoodscategory> categoryList = goodscategoryMapper.selectByExample(example);
				if (categoryList != null && categoryList.size() > 0) {
					category = categoryList.get(0);
					category.setModifytime(now);
					category.setModifier("system");
				} else {
					category.setCreatetime(now);
					category.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					category.setCode(code);
				} else {
					category.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					category.setName(name);
				}
				category.setExtid(extid);
				category.setGrade(Byte.valueOf("2"));
				category.setUpdatedtime(now);
				
				if (!Common.isEmpty(parentid)) {
					example = new MGoodscategoryExample();
					example.createCriteria().andGradeEqualTo(Byte.valueOf("1")).andExtidEqualTo(parentid.trim());
					example.setOrderByClause("useflag desc,id desc");
					categoryList = goodscategoryMapper.selectByExample(example);
					if (categoryList != null && categoryList.size() > 0) {
						MGoodscategory parentCategory = categoryList.get(0);
						category.setParentid(parentCategory.getId());
					}
				}
				
				if (category.getId() != null) {
					goodscategoryMapper.updateByPrimaryKeySelective(category);
				} else {
					goodscategoryMapper.insertSelective(category);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 门店
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveDepartment(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				String province = json.get("province") == null ? "" : json.getString("province").trim();
				String city = json.get("city") == null ? "" : json.getString("city").trim();
				String county = json.get("county") == null ? "" : json.getString("county").trim();
				String address = json.get("address") == null ? "" : json.getString("address").trim();
				String contacts = json.get("contacts") == null ? "" : json.getString("contacts").trim();
				String phone = json.get("phone") == null ? "" : json.getString("phone").trim();
				
				MDepartment department = new MDepartment();
				MDepartmentExample example = new MDepartmentExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MDepartment> departmentList = departmentMapper.selectByExample(example);
				if (departmentList != null && departmentList.size() > 0) {
					department = departmentList.get(0);
					department.setModifytime(now);
					department.setModifier("system");
				} else {
					department.setCreatetime(now);
					department.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					department.setCode(code);
				} else {
					department.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					department.setName(name);
				}
				if (!Common.isEmpty(province)) {
					department.setProvince(province);
				}
				if (!Common.isEmpty(city)) {
					department.setCity(city);
				}
				if (!Common.isEmpty(county)) {
					department.setCounty(county);
				}
				if (!Common.isEmpty(address)) {
					department.setAddress(address);
				}
				if (!Common.isEmpty(contacts)) {
					department.setContacts(contacts);
				}
				if (!Common.isEmpty(phone)) {
					department.setPhone(phone);
				}
				department.setExtid(extid);
				department.setUpdatedtime(now);
				department.setTypeid(Integer.valueOf(1));
				
				department = iDepartmentService.resetDepartmentRegion(department);
				
				if (department.getId() != null) {
					departmentMapper.updateByPrimaryKeySelective(department);
				} else {
					departmentMapper.insertSelective(department);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 员工
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveEmployee(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				String sex = json.get("sex") == null ? "0" : json.getString("sex").trim();
				String departmentcode = json.get("departmentcode") == null ? "" : json.getString("departmentcode").trim();
				String mobilephone = json.get("mobilephone") == null ? "" : json.getString("mobilephone").trim();
				
				MEmployee employee = new MEmployee();
				MEmployeeExample example = new MEmployeeExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MEmployee> employeeList = employeeMapper.selectByExample(example);
				if (employeeList != null && employeeList.size() > 0) {
					employee = employeeList.get(0);
					employee.setModifytime(now);
					employee.setModifier("system");
				} else {
					employee.setCreatetime(now);
					employee.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					employee.setCode(code);
				} else {
					employee.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					employee.setName(name);
				}
				if (!Common.isEmpty(mobilephone)) {
					employee.setMobilephone(mobilephone);
				}
				employee.setSex(Byte.valueOf(sex));
				employee.setExtid(extid);
				employee.setUpdatedtime(now);
				
				if (!Common.isEmpty(departmentcode)) {
					MDepartmentExample departmentExample = new MDepartmentExample();
					departmentExample.createCriteria().andCodeEqualTo(departmentcode);
					departmentExample.setOrderByClause(" useflag desc,id desc");
					List<MDepartment> departmentsList = departmentMapper.selectByExample(departmentExample);
					if (departmentsList != null && departmentsList.size() > 0) {
						MDepartment department = departmentsList.get(0);
						employee.setDepartmentid(department.getId());
					}
				}
				
				if (employee.getId() != null) {
					employeeMapper.updateByPrimaryKeySelective(employee);
				} else {
					employeeMapper.insertSelective(employee);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 会员等级
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveViptype(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				String grade = json.get("grade") == null ? "1" : json.getString("grade").trim();
				String discount = json.get("discount") == null ? "1" : json.getString("discount").trim();
				String birthdaydiscount = json.get("birthdaydiscount") == null ? "1" : json.getString("birthdaydiscount").trim();
				String pointrate = json.get("pointrate") == null ? "0" : json.getString("pointrate").trim();
				
				MViptype viptype = new MViptype();
				MViptypeExample example = new MViptypeExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MViptype> viptypeList = viptypeMapper.selectByExample(example);
				if (viptypeList != null && viptypeList.size() > 0) {
					viptype = viptypeList.get(0);
					viptype.setModifytime(now);
					viptype.setModifier("system");
				} else {
					viptype.setCreatetime(now);
					viptype.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					viptype.setCode(code);
				} else {
					viptype.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					viptype.setName(name);
				}
				
				viptype.setGrade(Integer.valueOf(grade));
				viptype.setDiscount(new BigDecimal(discount).divide(BigDecimal.TEN));
				viptype.setBirthdaydiscount(new BigDecimal(birthdaydiscount).divide(BigDecimal.TEN));
				viptype.setPointrate(Integer.valueOf(pointrate));
				viptype.setExtid(extid);
				viptype.setUpdatedtime(now);
				if (viptype.getId() != null) {
					viptypeMapper.updateByPrimaryKeySelective(viptype);
				} else {
					viptypeMapper.insertSelective(viptype);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	/**
	 * 优惠券
	 * @param list
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveCoupon(JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String code = json.get("code") == null ? "" : json.getString("code").trim();
				String name = json.get("name") == null ? "" : json.getString("name").trim();
				String amount = json.get("amount") == null ? "0" : json.getString("amount").trim();
				String enableamount = json.get("enableamount") == null ? "0" : json.getString("enableamount").trim();
				String limitquantity = json.get("limitquantity") == null ? "0" : json.getString("limitquantity").trim();
				String totalquantity = json.get("totalquantity") == null ? "0" : json.getString("totalquantity").trim();
				String begintime = json.get("begintime") == null ? "" : json.getString("begintime").trim();
				String edtime = json.get("edtime") == null ? "" : json.getString("edtime").trim();
				
				MCoupon coupon = new MCoupon();
				MCouponExample example = new MCouponExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MCoupon> couponList = couponMapper.selectByExample(example);
				if (couponList != null && couponList.size() > 0) {
					coupon = couponList.get(0);
					coupon.setModifytime(now);
					coupon.setModifier("system");
				} else {
					coupon.setCreatetime(now);
					coupon.setCreator("system");
				}
				if (!Common.isEmpty(code)) {
					coupon.setCode(code);
				} else {
					coupon.setCode(extid);
				}
				if (!Common.isEmpty(name)) {
					coupon.setName(name);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (!Common.isEmpty(begintime)) {
					try {
						coupon.setBegintime(sdf.parse(begintime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				if (!Common.isEmpty(edtime)) {
					try {
						coupon.setEndtime(sdf.parse(edtime));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				
				coupon.setAmount(new BigDecimal(amount));
				coupon.setEnableamount(new BigDecimal(enableamount));
				coupon.setLimitquantity(Integer.valueOf(limitquantity));
				coupon.setTotalquantity(Integer.valueOf(totalquantity));
				coupon.setExtid(extid);
				coupon.setUpdatedtime(now);
				if (coupon.getId() != null) {
					couponMapper.updateByPrimaryKeySelective(coupon);
				} else {
					couponMapper.insertSelective(coupon);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}

	@Override
	public Result syncGoods(Integer id) {
		Result result = new Result();

		try {
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem != null) {
				MGoods goods = goodsMapper.selectByPrimaryKey(id);
				if (!Common.isEmpty(goods.getExtid())) {
					Result r = iExtService.stockOne(extsystem, goods.getExtid());
					if (Common.isActive(r)) {
						JSONArray array = (JSONArray) r.getData();
						result = saveStock(goods, array);
					}
				}
			}			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.syncGoods报错：", e);
		}

		return result;
	}
	
	/**
	 * 同步库存
	 * @param goods
	 * @param array
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public Result saveStock(MGoods goods, JSONArray array) {
		Result result = new Result();
		if (array != null && array.size() > 0) {
			Integer patternid = 0;
			MPatternExample patternExample = new MPatternExample();
			patternExample.createCriteria().andUseflagEqualTo(Byte.valueOf("1"));
			patternExample.setOrderByClause(" id asc");
			List<MPattern> patterns = patternMapper.selectByExample(patternExample);
			if (patterns != null && patterns.size() > 0) {
				patternid = patterns.get(0).getId();
			}
			// 库存比例
			BigDecimal skuRate = BigDecimal.ONE;
			MConfigExample configExample = new MConfigExample();
			configExample.createCriteria().andCodeEqualTo("7002");
			List<MConfig> configs = configMapper.selectByExample(configExample);
			if (configs != null && configs.size() > 0) {
				MConfig config = configs.get(0);
				if (config != null && !Common.isEmpty(config.getValue())) {
					skuRate = new BigDecimal(config.getValue().trim());
				}
			}
			if (skuRate.compareTo(BigDecimal.ZERO) < 0) {
				skuRate = BigDecimal.ONE;
			}
			skuRate = skuRate.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			
			List<Integer> skuidList = new ArrayList<>();
			for (int i = 0; i < array.size(); i++) {
				try {
					Date now = new Date();
					JSONObject json = array.getJSONObject(i);
					String extid = json.get("id") == null ? "" : json.getString("id").trim();
					String sizeid = json.get("sizeid") == null ? "" : json.getString("sizeid").trim();
					String colorid = json.get("colorid") == null ? "" : json.getString("colorid").trim();
					String barcode = json.get("barcode") == null ? "0" : json.getString("barcode").trim();
					String quantity = json.get("quantity") == null ? "0" : json.getString("quantity").trim();
					String ianumber = json.get("ianumber") == null ? "" : json.getString("ianumber").trim();
					String extbarcode = json.get("extbarcode") == null ? "" : json.getString("extbarcode").trim();
					
					if (Common.isEmpty(quantity)) {
						continue;
					} else {
						Integer qty = skuQty(skuRate, Integer.valueOf(quantity));
						if (qty.intValue() > 0) {
							quantity = qty.toString();
						} else {
							continue;
						}
					}					
					
					MGoodssku sku = new MGoodssku();
					if (!Common.isEmpty(extid)) {
						MGoodsskuExample example = new MGoodsskuExample();
						example.createCriteria().andExtidEqualTo(extid);
						example.setOrderByClause(" id asc");
						List<MGoodssku> skuList = goodsskuMapper.selectByExample(example);
						if (skuList != null && skuList.size() > 0) {
							sku = skuList.get(0);							
						}
					}
					
					if (sku != null && sku.getId() != null) {
						sku.setModifytime(now);
						sku.setModifier("system");
					} else {
						MSizeExample sizeExample = new MSizeExample();
						sizeExample.createCriteria().andExtidEqualTo(sizeid.trim());
						sizeExample.setOrderByClause(" id asc");
						List<MSize> sizes = sizeMapper.selectByExample(sizeExample);
						MSize size = sizes.get(0);
						
						MColorExample colorExample = new MColorExample();
						colorExample.createCriteria().andExtidEqualTo(colorid.trim());
						colorExample.setOrderByClause(" id asc");
						List<MColor> colors = colorMapper.selectByExample(colorExample);
						MColor color = colors.get(0);
						
						MGoodsskuExample example = new MGoodsskuExample();
						example.createCriteria().andGoodsidEqualTo(goods.getId()).andColoridEqualTo(color.getId()).andSizeidEqualTo(size.getId()).andBarcodeEqualTo(barcode);
						example.setOrderByClause(" id asc");
						List<MGoodssku> skuList = goodsskuMapper.selectByExample(example);
						if (skuList != null && skuList.size() > 0) {
							sku = skuList.get(0);
							sku.setModifytime(now);
							sku.setModifier("system");
						} else {
							sku.setCreatetime(now);
							sku.setCreator("system");
						}
						
						sku.setGoodsid(goods.getId());
						sku.setColorid(color.getId());
						sku.setSizeid(size.getId());
						sku.setPatternid(patternid);
						sku.setExtid(extid);
					}
					
					if (!Common.isEmpty(extbarcode)) {
						sku.setExtbarcode(extbarcode);
					}
					if (!Common.isEmpty(quantity)) {
						sku.setQuantity(Long.valueOf(quantity));
					}
					if (!Common.isEmpty(barcode)) {
						sku.setBarcode(barcode);
					}
					if (!Common.isEmpty(ianumber)) {
						sku.setIanumber(ianumber);
					}
					if (!Common.isEmpty(extbarcode)) {
						sku.setExtbarcode(extbarcode);
					}
					
					sku.setUpdatedtime(now);
					if (sku.getId() != null) {
						goodsskuMapper.updateByPrimaryKeySelective(sku);
					} else {
						goodsskuMapper.insertSelective(sku);
					}
					skuidList.add(sku.getId());
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}				
			}
			if (skuidList != null && skuidList.size() > 0) {
				MGoodsskuExample example = new MGoodsskuExample();
				example.createCriteria().andGoodsidEqualTo(goods.getId()).andIdNotIn(skuidList);
				goodsskuMapper.deleteByExample(example);
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	private Integer skuQty(BigDecimal rate, Integer qty) {
		Integer quantity = 0;		
		quantity = new BigDecimal(qty).multiply(rate).setScale(0, BigDecimal.ROUND_DOWN).intValue();		
		return quantity;
	}
	
	@Override
	public Result syncGoodsSKu(Integer id) {
		Result result = new Result();

		try {
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem != null) {
				MGoods goods = goodsMapper.selectByPrimaryKey(id);
				if (!Common.isEmpty(goods.getExtid())) {
					Result r = iExtService.stockOne(extsystem, goods.getExtid());
					if (Common.isActive(r)) {
						JSONArray array = (JSONArray) r.getData();
						result = saveStock(goods, array);
					}
				}
			}			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.syncGoods报错：", e);
		}

		return result;
	}

	@Override
	public Result syncVip(Integer id) {
		Result result = new Result();

		try {
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem != null) {
				MVip vip = vipMapper.selectByPrimaryKey(id);
				if (!Common.isEmpty(vip.getMobilephone())) {
					Result r = iExtService.vipOne(extsystem, vip.getMobilephone());
					if (Common.isActive(r)) {
						JSONObject object = JSONObject.parseObject(r.getData().toString());
						result = saveVip(vip, object);
					}
				}
			}			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.syncVip报错：", e);
		}

		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Result saveVip(MVip vip, JSONObject json) {
		Result result = new Result();
		if (json != null && !json.isEmpty()) {
			Date now = new Date();
			String extid = json.get("id") == null ? "" : json.getString("id").trim();
			String code = json.get("code") == null ? "" : json.getString("code").trim();
			String name = json.get("name") == null ? "" : json.getString("name").trim();
			String sex = json.get("sex") == null ? "0" : json.getString("sex").trim();
			String viptypeid = json.get("viptypeid") == null ? "0" : json.getString("viptypeid").trim();
			String nickname = json.get("nickname") == null ? "" : json.getString("nickname").trim();
			String birthday = json.get("birthday") == null ? "" : json.getString("birthday").trim();
			String province = json.get("province") == null ? "" : json.getString("province").trim();
			String city = json.get("city") == null ? "" : json.getString("city").trim();
			String county = json.get("county") == null ? "" : json.getString("county").trim();
			String departmentid = json.get("departmentid") == null ? "" : json.getString("departmentid").trim();
			String registtime = json.get("registtime") == null ? "" : json.getString("registtime").trim();
			String recommendercode = json.get("recommendercode") == null ? "" : json.getString("recommendercode").trim();
			String point = json.get("point") == null ? "0" : json.getString("point").trim();			
			String deposit = json.get("deposit") == null ? "0" : json.getString("deposit").trim();
			
			vip.setExtid(extid);
			vip.setCode(code);
			if (!Common.isEmpty(sex)) {
				vip.setSex(Byte.valueOf(sex));
			}
			if (!Common.isEmpty(name)) {
				vip.setName(name);
			}
			if (!Common.isEmpty(nickname)) {
				vip.setNickname(nickname);
			}
			if (!Common.isEmpty(viptypeid)) {
				MViptypeExample viptypeExample = new MViptypeExample();
				viptypeExample.createCriteria().andExtidEqualTo(extid);
				viptypeExample.setOrderByClause(" useflag desc");
				List<MViptype> viptypesList = viptypeMapper.selectByExample(viptypeExample);
				if (viptypesList != null && viptypesList.size() > 0) {
					vip.setTypeid(viptypesList.get(0).getId());
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (!Common.isEmpty(birthday)) {
				try {
					vip.setBirthday(sdf.parse(birthday));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (!Common.isEmpty(province)) {
				vip.setProvince(province);
			}
			if (!Common.isEmpty(city)) {
				vip.setCity(city);
			}
			if (!Common.isEmpty(county)) {
				vip.setCounty(county);
			}
			if (!Common.isEmpty(departmentid)) {
				MDepartmentExample departmentExample = new MDepartmentExample();
				departmentExample.createCriteria().andExtidEqualTo(departmentid);
				departmentExample.setOrderByClause(" useflag desc");
				List<MDepartment> departmentsList = departmentMapper.selectByExample(departmentExample);
				if (departmentsList != null && departmentsList.size() > 0) {
					vip.setDepartmentid(departmentsList.get(0).getId());
				}
			}
			if (!Common.isEmpty(registtime)) {
				try {
					vip.setRegisttime(sdf.parse(registtime));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (!Common.isEmpty(recommendercode)) {
				MVipExample vipExample = new MVipExample();
				vipExample.createCriteria().andCodeEqualTo(recommendercode);
				vipExample.setOrderByClause(" useflag desc");
				List<MVip> vipsList = vipMapper.selectByExample(vipExample);
				if (vipsList != null && vipsList.size() > 0) {
					vip.setRecommenderid(vipsList.get(0).getId());
				}
			}
			
			vip = iVipService.resetVipRegion(vip);
			if (vip.getId() != null) {
				vipMapper.updateByPrimaryKeySelective(vip);
			} else {
				vipMapper.insertSelective(vip);
			}
			// 更新账户信息
			MVipaccount vipaccount = vipaccountMapper.selectByPrimaryKey(vip.getId());
			if (vipaccount != null && vipaccount.getId() != null) {
				vipaccount.setPoint(Integer.valueOf(point));
				vipaccount.setDeposit(new BigDecimal(deposit));
				vipaccount.setModifier("system");
				vipaccount.setModifytime(now);
				vipaccount.setUpdatedtime(now);
				vipaccountMapper.updateByPrimaryKeySelective(vipaccount);
			} else {
				vipaccount = new MVipaccount();
				vipaccount.setId(vip.getId());
				vipaccount.setPoint(Integer.valueOf(point));
				vipaccount.setDeposit(new BigDecimal(deposit));
				vipaccount.setCreatetime(now);
				vipaccount.setCreator("system");
				vipaccount.setUpdatedtime(now);
				vipaccountMapper.insertSelective(vipaccount);
			}
			// 同步优惠券
			saveVipcoupon(vip);
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Result saveVipcoupon(MVip vip) {
		Result result = new Result();
		MExtsystem extsystem = null;
		MExtsystemExample example = new MExtsystemExample();
		example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
		List<MExtsystem> list = extsystemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			extsystem = list.get(0);
		}
		if (extsystem != null) {
			Result r = iExtService.vipcouponOne(extsystem, vip.getMobilephone());
			if (Common.isActive(r)) {
				JSONArray array = (JSONArray) r.getData();
				if (array != null && !array.isEmpty() && array.size() > 0) {
					for (int i = 0; i < array.size(); i++) {
						Date now = new Date();
						JSONObject json = array.getJSONObject(i);
						String extid = json.get("id") == null ? "" : json.getString("id").trim();
						String code = json.get("code") == null ? "" : json.getString("code").trim();
						String couponid = json.get("couponid") == null ? "" : json.getString("couponid").trim();
						String begintime = json.get("begintime") == null ? "" : json.getString("begintime").trim();
						String endtime = json.get("endtime") == null ? "" : json.getString("endtime").trim();
						String gettime = json.get("gettime") == null ? "" : json.getString("gettime").trim();
						String usetime = json.get("usetime") == null ? "" : json.getString("usetime").trim();
						String useflag = json.get("useflag") == null ? "0" : json.getString("useflag").trim();
						
						MVipcoupon vipcoupon = new MVipcoupon();
						MVipcouponExample vipcouponExample = new MVipcouponExample();
						vipcouponExample.createCriteria().andExtidEqualTo(extid);
						List<MVipcoupon> vipcouponsList = vipcouponMapper.selectByExample(vipcouponExample);
						if (vipcouponsList != null && vipcouponsList.size() > 0) {
							vipcoupon = vipcouponsList.get(0);
							vipcoupon.setModifier("system");
							vipcoupon.setModifytime(now);
						} else {
							vipcoupon.setCreatetime(now);
							vipcoupon.setCreator("system");
						}
						if (!Common.isEmpty(code)) {
							vipcoupon.setCode(code);
						}
						if (!Common.isEmpty(couponid)) {
							MCouponExample couponExample = new MCouponExample();
							couponExample.createCriteria().andExtidEqualTo(couponid);
							couponExample.setOrderByClause(" useflag desc");
							List<MCoupon> couponsList = couponMapper.selectByExample(couponExample);
							if (couponsList != null && couponsList.size() > 0) {
								vipcoupon.setCouponid(couponsList.get(0).getId());
							}
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if (!Common.isEmpty(begintime)) {
							try {
								vipcoupon.setBegintime(sdf.parse(begintime));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						if (!Common.isEmpty(endtime)) {
							try {
								vipcoupon.setEndtime(sdf.parse(endtime));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						if (!Common.isEmpty(gettime)) {
							try {
								vipcoupon.setGettime(sdf.parse(gettime));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						if (vipcoupon.getGettime() == null) {
							vipcoupon.setGettime(now);
						}
						if (!Common.isEmpty(usetime)) {
							try {
								vipcoupon.setUsetime(sdf.parse(usetime));
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}
						if (!Common.isEmpty(useflag)) {
							vipcoupon.setUseflag(Byte.valueOf(useflag));
						} else {
							vipcoupon.setUseflag(Byte.valueOf("0"));
						}
						
						vipcoupon.setUpdatedtime(now);
						if (vipcoupon.getId() != null) {
							vipcouponMapper.updateByPrimaryKeySelective(vipcoupon);
						} else {
							vipcouponMapper.insertSelective(vipcoupon);
						}
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public Result putOrder(MExtsystem extsystem, Integer id) {
		Result result = new Result();

		try {
			MOrder order = orderMapper.selectByPrimaryKey(id);
			if (order != null && order.getId() != null) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("no", order.getNo());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String createtime = "";
				try {
					createtime = sdf.format(order.getCreatetime());
				} catch (Exception e) {
					e.printStackTrace();
				}
				jsonObject.put("date", createtime);
				
				MVip vip = vipMapper.selectByPrimaryKey(order.getVipid());
				jsonObject.put("vipCode", vip.getCode());
				
				MDepartment department = departmentMapper.selectByPrimaryKey(order.getDelivererdepartmentid());
				jsonObject.put("departmentID", department.getExtid());
				
				jsonObject.put("employeeID", "");
				jsonObject.put("cardType", "微信");
				jsonObject.put("quantitySum", order.getQuantity());
				jsonObject.put("amountSum", order.getAmount());
				jsonObject.put("type", "销售单");
				jsonObject.put("editor", "");
				jsonObject.put("auditFlag", (order.getDeliverytype().intValue() == 2 ? 0 : 1));
				
				String sql = "select a.id,b.extid as goodsID,c.extid as colorID,d.id as patternID,e.extid as sizeID,a.quantity,a.amount "
						+ "from m_orderdtl a "
						+ "inner join m_goods b on a.goodsid=b.id "
						+ "inner join m_color c on a.colorid=c.id "
						+ "inner join m_pattern d on a.patternid=d.id "
						+ "inner join m_size e on a.sizeid=e.id "
						+ "where a.orderid=" + order.getId();
				List<LinkedHashMap<String, Object>> dtlList = dataMapper.pageList(sql);				
				jsonObject.put("goodsData", dtlList);
				
				String url = extsystem.getServeraddress() + "/api/posSales/create";
				net.sf.json.JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
				if (object != null) {
					result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
					if (Common.isActive(result)) {
						String extid = result.getId() == null ? "" : result.getId().toString();
						if (Common.isEmpty(extid)) {
							order.setExtid(extid);
							order.setUpdatedtime(new Date());
							orderMapper.updateByPrimaryKeySelective(order);
							result.setErrcode(Integer.valueOf(0));
							result.setId(order.getId());
						}
						/*Result r = iExtService.queryOrderStatus(extsystem, order.getNo());
						if (Common.isActive(r)) {
							JSONObject jObject = JSONObject.parseObject(r.getData().toString());
							if (jObject != null && !jObject.isEmpty()) {
								String extid = jObject.get("id") == null ? "" : jObject.getString("id");
								if (!Common.isEmpty(extid)) {
									order.setExtid(extid);
									order.setUpdatedtime(new Date());
									orderMapper.updateByPrimaryKeySelective(order);
								}
							}
						}*/
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.putOrder报错：", e);
		}

		return result;
	}

	@Override
	public Result putVip(MExtsystem extsystem, Integer id) {
		Result result = new Result();

		try {
			MVip vip = vipMapper.selectByPrimaryKey(id);
			if (vip != null) {
				result = iExtService.vipOne(extsystem, vip.getMobilephone());
				if (Common.isActive(result)) {
					com.alibaba.fastjson.JSONObject jObject = com.alibaba.fastjson.JSONObject.parseObject(result.getData().toString());
					if (jObject != null && !jObject.isEmpty()) {
						String extid = jObject.get("id") == null ? "" : jObject.getString("id");
						if (!Common.isEmpty(extid)) {
							vip.setExtid(extid.trim());
							vipMapper.updateByPrimaryKeySelective(vip);
							result.setErrcode(Integer.valueOf(0));
							result.setId(vip.getId());
							return result;
						}
					}
				}
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("code", vip.getCode());
				jsonObject.put("vip", vip.getName());
				jsonObject.put("mobilePhone", vip.getMobilephone());
				
				MDepartment department = departmentMapper.selectByPrimaryKey(vip.getDepartmentid());
				jsonObject.put("departmentID", department.getExtid());
				
				jsonObject.put("sex", (vip.getSex().intValue() == 1 ? "男" : (vip.getSex().intValue() == 2 ? "女" : "")));
				
				MViptype viptype = viptypeMapper.selectByPrimaryKey(vip.getTypeid());
				jsonObject.put("vipTypeID", viptype.getExtid());
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (vip.getBirthday() != null) {					
					String birthday = "";
					try {
						birthday = sdf.format(vip.getBirthday());
					} catch (Exception e) {
						e.printStackTrace();
					}
					jsonObject.put("birthDay", birthday);
				}
				String registtime = "";
				try {
					registtime = sdf.format(vip.getRegisttime());
				} catch (Exception e) {
					e.printStackTrace();
				}
				jsonObject.put("enrollmentDate", registtime);
				
				jsonObject.put("province", vip.getProvince());
				jsonObject.put("city", vip.getCity());
				jsonObject.put("county", vip.getCounty());
				
				String url = extsystem.getServeraddress() + "/api/vip/create";
				net.sf.json.JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
				if (object != null) {
					result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
					if (Common.isActive(result)) {
						String extid = result.getId() == null ? "" : result.getId().toString();
						if (Common.isEmpty(extid)) {
							vip.setExtid(extid.trim());
							vip.setUpdatedtime(new Date());
							vipMapper.updateByPrimaryKeySelective(vip);
							
							result.setErrcode(Integer.valueOf(0));
							result.setId(vip.getId());
						}
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.putVip报错：", e);
		}

		return result;
	}

	@Override
	public Result changeVipInfo(Integer id) {
		Result result = new Result();

		try {
			changeVipInfoThread thread = new changeVipInfoThread();
            thread.setVipid(id);
            Thread t = new Thread(thread);
            t.start();
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.changeVipInfo报错：", e);
		}

		return result;
	}
	
	public class changeVipInfoThread implements Runnable {
		private Integer vipid;
		
		public Integer getVipid() {
			return vipid;
		}

		public void setVipid(Integer vipid) {
			this.vipid = vipid;
		}

		@Override
		public void run() {
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem != null) {
				MVip vip = vipMapper.selectByPrimaryKey(vipid);
				if (vip != null) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("code", vip.getCode());
					jsonObject.put("vip", vip.getName());
					
					jsonObject.put("sex", (vip.getSex().intValue() == 1 ? "男" : (vip.getSex().intValue() == 2 ? "女" : "")));
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (vip.getBirthday() != null) {					
						String birthday = "";
						try {
							birthday = sdf.format(vip.getBirthday());
						} catch (Exception e) {
							e.printStackTrace();
						}
						jsonObject.put("birthDay", birthday);
					}
					
					jsonObject.put("province", vip.getProvince());
					jsonObject.put("city", vip.getCity());
					jsonObject.put("county", vip.getCounty());
					
					String url = extsystem.getServeraddress() + "/api/vip/update";
					net.sf.json.JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
					if (object != null) {
						
					}
				}
			}			
		}
	}

	@Override
	public Result changeVipPoint(MExtsystem extsystem, Integer vipid, Integer point, String reason) {
		Result result = new Result();

		try {
			MVip vip = vipMapper.selectByPrimaryKey(vipid);
			if (vip != null) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("vipID", vip.getExtid());
				jsonObject.put("deductSalesPoint", point);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonObject.put("deductDate", sdf.format(new Date()));
				jsonObject.put("style", (point.intValue()>0?2:1));
				jsonObject.put("deductReason", reason);
				
				String url = extsystem.getServeraddress() + "/api/vip/pointChange";
				net.sf.json.JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
				if (object != null) {
					result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
					/*if (Common.isActive(result)) {
						String extid = result.getId() == null ? "" : result.getId().toString();
						if (Common.isEmpty(extid)) {
							vip.setExtid(extid.trim());
							vip.setUpdatedtime(new Date());
							vipMapper.updateByPrimaryKeySelective(vip);
						}
					}*/
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.changeVipPoint报错：", e);
		}

		return result;
	}

	@Override
	public Result changeVipDeposit(MExtsystem extsystem, Integer vipid, BigDecimal deposit, String reason) {
		Result result = new Result();

		try {
			MVip vip = vipMapper.selectByPrimaryKey(vipid);
			if (vip != null) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("vipID", vip.getExtid());
				jsonObject.put("depositAmount", deposit);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jsonObject.put("date", sdf.format(new Date()));
				jsonObject.put("memo", reason);
				
				String url = extsystem.getServeraddress() + "/api/vip/storeValueChange";
				net.sf.json.JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
				if (object != null) {
					result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
					/*if (Common.isActive(result)) {
						String extid = result.getId() == null ? "" : result.getId().toString();
						if (Common.isEmpty(extid)) {
							vip.setExtid(extid.trim());
							vip.setUpdatedtime(new Date());
							vipMapper.updateByPrimaryKeySelective(vip);
						}
					}*/
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtMaintServiceImpl.changeVipDeposit报错：", e);
		}

		return result;
	}
	
	@Override
	public Result pushVipTask() {
		System.out.println("推送会员开始...");
		Result result = new Result();

		try {
			if (pushVipTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			
			pushVipTaskLock = true;
			
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			String sql = "select * from m_vip where useflag=1 and (extid is null or extid='') order by id asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						Result r = putVip(extsystem, Integer.valueOf(list.get(i).get("id").toString()));
						if (Common.isActive(r)) {
							
						} else {
							System.out.println("推送会员失败：vipid="+list.get(i).get("id").toString());
						}
					} catch (Exception e) {
						System.out.println("推送会员失败：vipid="+list.get(i).get("id").toString());
						FastLog.error("调用VipMaintServiceImpl.pushVipTask推送会员报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用VipMaintServiceImpl.pushVipTask报错：", e);
		} finally {
			pushVipTaskLock = false;
		}
		
		System.out.println("推送会员结束...");
		return result;
	}
	
	@Override
	public Result cancelOrderTask() {
		System.out.println("订单自动取消开始...");
		Result result = new Result();

		try {
			if (cancelOrderTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			// 订单自动取消时间（分）
			Result r = iConfigService.queryConfigValueByCode("4001");
			String min = "";
			if (Common.isActive(r)) {
				min = (String) r.getData();
			}
			if (Common.isEmpty(min)) {
				result.setMessage("获取订单自动取消时间参数错误");
				return result;
			}
			
			cancelOrderTaskLock = true;
			
			String sql = "select * from m_order where status=1 and datediff(minute, createtime, getdate())>=" + min + " order by createtime asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						MOrder order = orderMapper.selectByPrimaryKey(Integer.valueOf(list.get(i).get("id").toString()));
						iOrderMaintService.rollbackOrder(order);
					} catch (Exception e) {
						System.out.println("订单自动取消失败：orderid="+list.get(i).get("id").toString());
						FastLog.error("调用OrderMaintServiceImpl.cancelOrderTask修改订单状态报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.cancelOrderTask报错：", e);
		} finally {
			cancelOrderTaskLock = false;
		}
		
		System.out.println("订单自动取消结束...");
		return result;
	}
	
	@Override
	public Result pushOrderTask() {
		System.out.println("推送订单开始...");
		Result result = new Result();

		try {
			if (pushOrderTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			
			pushOrderTaskLock = true;
			
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			String sql = "select * from m_order where status>=3 and (extid is null or extid='') order by deliverertime asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						Result r = putOrder(extsystem, Integer.valueOf(list.get(i).get("id").toString()));
						if (Common.isActive(r)) {
							
						} else {
							System.out.println("推送订单失败：orderid="+list.get(i).get("id").toString());
						}
					} catch (Exception e) {
						System.out.println("推送订单失败：orderid="+list.get(i).get("id").toString());
						FastLog.error("调用OrderMaintServiceImpl.pushOrderTask推送订单报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.pushOrderTask报错：", e);
		} finally {
			pushOrderTaskLock = false;
		}
		
		System.out.println("推送订单结束...");
		return result;
	}
	
	@Override
	public Result changeOrderStatusTask() {
		System.out.println("更新订单状态开始...");
		Result result = new Result();

		try {
			if (changeOrderStatusTaskLock) {
				result.setMessage("任务进行中");
				return result;
			}
			
			changeOrderStatusTaskLock = true;
			
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(example);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			String sql = "select * from m_order where status=2 and distributionflag=1 and (extid is null or extid='') order by distributiontime asc,createtime asc";
			List<LinkedHashMap<String, Object>> list = dataMapper.pageList(sql);
			if (list != null && list.size() > 0) {
				list = CommonUtil.transformUpperCase(list);
				for (int i = 0; i < list.size(); i++) {
					try {
						Result r = iExtService.queryOrderStatus(extsystem, list.get(i).get("no").toString());
						if (Common.isActive(r)) {
							com.alibaba.fastjson.JSONObject jObject = com.alibaba.fastjson.JSONObject.parseObject(r.getData().toString());
							if (jObject != null && !jObject.isEmpty()) {
								String state = jObject.get("state") == null ? "" : jObject.getString("state");
								if (!Common.isEmpty(state) && "已完成".equals(state)) {
									MOrder order = orderMapper.selectByPrimaryKey(Integer.valueOf(list.get(i).get("id").toString()));
									order.setStatus(Byte.valueOf("3"));
									order.setUpdatedtime(new Date());
									orderMapper.updateByPrimaryKeySelective(order);
								}
							}
						} else {
							System.out.println("查询订单状态失败：orderno="+list.get(i).get("no").toString());
						}
					} catch (Exception e) {
						System.out.println("更新订单状态失败：orderid="+list.get(i).get("id").toString());
						FastLog.error("调用OrderMaintServiceImpl.changeOrderStatusTask更新订单状态报错：", e);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.changeOrderStatusTask报错：", e);
		} finally {
			changeOrderStatusTaskLock = false;
		}
		
		System.out.println("更新订单状态结束...");
		return result;
	}

	@Override
	public Result updateVipPointRecord(Integer vipid) {
		Result result = new Result();

		try {
			if (updateVipPointRecordLock) {
				return result;
			}		
			
			MExtsystem extsystem = null;
			MExtsystemExample extsystemExample = new MExtsystemExample();
			extsystemExample.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(extsystemExample);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			
			// 上锁
			updateVipPointRecordLock = true;
			
			MVip vip = vipMapper.selectByPrimaryKey(vipid);
			if (vip != null) {				
				Date date = null;
				MVippointrecordExample example = new MVippointrecordExample();
				example.createCriteria().andVipidEqualTo(vip.getId()).andExtidIsNotNull();
				example.setOrderByClause(" updatedtime desc");
				List<MVippointrecord> list = vippointrecordMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					date = list.get(0).getUpdatedtime();
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (date == null) {
					// 2000年至今的记录
					updatePointRecord(extsystem, vip.getId(), vip.getCode(), "2000-01-01 00:00:00", sdf.format(new Date()));
				} else {
					// 最后一条记录至今的记录
					updatePointRecord(extsystem, vip.getId(), vip.getCode(), sdf.format(date), sdf.format(new Date()));
					
					// 第一条记录以前的记录
					example = new MVippointrecordExample();
					example.createCriteria().andVipidEqualTo(vip.getId()).andExtidIsNotNull();
					example.setOrderByClause(" updatedtime asc");
					list = vippointrecordMapper.selectByExample(example);
					if (list != null && list.size() > 0) {
						updatePointRecord(extsystem, vip.getId(), vip.getCode(), "2000-01-01 00:00:00", sdf.format(list.get(0).getUpdatedtime()));
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.updateVipPointRecord报错：", e);
		} finally {
			// 解锁
			updateVipPointRecordLock = false;
		}
		
		return result;
	}
	
	private void updatePointRecord(MExtsystem extsystem, Integer vipid, String vipcode, String begintime, String endtime) {
		Result r = iExtService.queryVipPointRecord(extsystem, vipcode, begintime, endtime);
		if (Common.isActive(r)) {
			JSONArray array = (JSONArray) r.getData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < array.size(); i++) {
				try {
					JSONObject object = array.getJSONObject(i);
					MVippointrecord vippointrecord = new MVippointrecord();
					vippointrecord.setVipid(vipid);
					vippointrecord.setType(Byte.valueOf("0"));
					vippointrecord.setRefid(0);
					vippointrecord.setUpdatedtime(sdf.parse(object.getString("date")));
					vippointrecord.setPoint(Integer.valueOf(object.getString("point")));
					vippointrecord.setNewpoint(0);
					vippointrecord.setMemo(object.getString("reason"));
					vippointrecord.setExtid(object.getString("id"));
					vippointrecordMapper.insertSelective(vippointrecord);
				} catch (Exception e) {
					FastLog.error("调用OrderMaintServiceImpl.updatePointRecord报错：", e);
				}							
			}
		}
	}

	@Override
	public Result updateVipDepositRecord(Integer vipid) {
		Result result = new Result();

		try {
			if (updateVipDepositRecordLock) {
				return result;
			}
			MExtsystem extsystem = null;
			MExtsystemExample extsystemExample = new MExtsystemExample();
			extsystemExample.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> extList = extsystemMapper.selectByExample(extsystemExample);
			if (extList != null && extList.size() > 0) {
				extsystem = extList.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			
			// 上锁
			updateVipDepositRecordLock = true;
			
			MVip vip = vipMapper.selectByPrimaryKey(vipid);
			if (vip != null) {				
				Date date = null;
				MVipdepositrecordExample example = new MVipdepositrecordExample();
				example.createCriteria().andVipidEqualTo(vip.getId()).andExtidIsNotNull();
				example.setOrderByClause(" updatedtime desc");
				List<MVipdepositrecord> list = vipdepositrecordMapper.selectByExample(example);
				if (list != null && list.size() > 0) {
					date = list.get(0).getUpdatedtime();
				}
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (date == null) {
					// 2000年至今的记录
					updateDepositRecord(extsystem, vip.getId(), vip.getCode(), "2000-01-01 00:00:00", sdf.format(new Date()));
				} else {
					// 最后一条记录至今的记录
					updateDepositRecord(extsystem, vip.getId(), vip.getCode(), sdf.format(date), sdf.format(new Date()));
					
					// 第一条记录以前的记录
					example = new MVipdepositrecordExample();
					example.createCriteria().andVipidEqualTo(vip.getId()).andExtidIsNotNull();
					example.setOrderByClause(" updatedtime asc");
					list = vipdepositrecordMapper.selectByExample(example);
					if (list != null && list.size() > 0) {
						updateDepositRecord(extsystem, vip.getId(), vip.getCode(), "2000-01-01 00:00:00", sdf.format(list.get(0).getUpdatedtime()));
					}
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用OrderMaintServiceImpl.updateVipDepositRecord报错：", e);
		} finally {
			// 解锁
			updateVipDepositRecordLock = false;
		}
		
		return result;
	}
	
	private void updateDepositRecord(MExtsystem extsystem, Integer vipid, String vipcode, String begintime, String endtime) {
		Result r = iExtService.queryVipDepositRecord(extsystem, vipcode, begintime, endtime);
		if (Common.isActive(r)) {
			JSONArray array = (JSONArray) r.getData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < array.size(); i++) {
				try {
					JSONObject object = array.getJSONObject(i);
					MVipdepositrecord vipdepositrecord = new MVipdepositrecord();
					vipdepositrecord.setVipid(vipid);
					vipdepositrecord.setType(Byte.valueOf("0"));
					vipdepositrecord.setRefid(0);
					vipdepositrecord.setUpdatedtime(sdf.parse(object.getString("date")));
					vipdepositrecord.setDeposit(new BigDecimal(object.getString("amount")));
					vipdepositrecord.setNewdeposit(BigDecimal.ZERO);
					vipdepositrecord.setMemo(object.getString("reason"));
					vipdepositrecord.setExtid(object.getString("id"));
					vipdepositrecordMapper.insertSelective(vipdepositrecord);
				} catch (Exception e) {
					FastLog.error("调用OrderMaintServiceImpl.updateDepositRecord报错：", e);
				}							
			}
		}
	}

}
