package com.fast.service.ext.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fast.base.Result;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.dao.MColorMapper;
import com.fast.base.data.dao.MCouponMapper;
import com.fast.base.data.dao.MDepartmentMapper;
import com.fast.base.data.dao.MEmployeeMapper;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.dao.MGoodsskuMapper;
import com.fast.base.data.dao.MSizeMapper;
import com.fast.base.data.dao.MVipMapper;
import com.fast.base.data.dao.MVipaccountMapper;
import com.fast.base.data.dao.MVipminiMapper;
import com.fast.base.data.dao.MViptypeMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.base.data.entity.MColor;
import com.fast.base.data.entity.MColorExample;
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
import com.fast.base.data.entity.MSize;
import com.fast.base.data.entity.MSizeExample;
import com.fast.base.data.entity.MVip;
import com.fast.base.data.entity.MVipExample;
import com.fast.base.data.entity.MVipaccount;
import com.fast.base.data.entity.MVipmini;
import com.fast.base.data.entity.MViptype;
import com.fast.base.data.entity.MViptypeExample;
import com.fast.service.IDepartmentService;
import com.fast.service.IVipService;
import com.fast.service.ext.IExtMaintService;
import com.fast.service.ext.IExtService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

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
			for (int i = 0; i < array.size(); i++) {
				Date now = new Date();
				JSONObject json = array.getJSONObject(i);
				String extid = json.get("id") == null ? "" : json.getString("id").trim();
				String sizeid = json.get("sizeid") == null ? "" : json.getString("sizeid").trim();
				String colorid = json.get("colorid") == null ? "" : json.getString("colorid").trim();
				String barcode = json.get("barcode") == null ? "0" : json.getString("barcode").trim();
				String quantity = json.get("quantity") == null ? "0" : json.getString("quantity").trim();
				String ianumber = json.get("ianumber") == null ? "" : json.getString("ianumber").trim();
				String extbarcode = json.get("extbarcode") == null ? "" : json.getString("extbarcode").trim();
				
				MGoodssku sku = new MGoodssku();
				MGoodsskuExample example = new MGoodsskuExample();
				example.createCriteria().andExtidEqualTo(extid);
				List<MGoodssku> skuList = goodsskuMapper.selectByExample(example);
				if (skuList != null && skuList.size() > 0) {
					sku = skuList.get(0);
					sku.setModifytime(now);
					sku.setModifier("system");
				} else {
					sku.setCreatetime(now);
					sku.setCreator("system");
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
				sku.setGoodsid(goods.getId());
				sku.setColorid(Integer.valueOf(colorid));
				sku.setSizeid(Integer.valueOf(sizeid));
				sku.setPatternid(Integer.valueOf(1));
				sku.setExtid(extid);
				sku.setUpdatedtime(now);
				if (sku.getId() != null) {
					goodsskuMapper.updateByPrimaryKeySelective(sku);
				} else {
					goodsskuMapper.insertSelective(sku);
				}
			}
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
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
			
		}
		result.setErrcode(Integer.valueOf(0));
		result.setMessage("同步成功");
		return result;
	}

}
