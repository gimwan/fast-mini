package com.fast.service.ext.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fast.base.Result;
import com.fast.base.data.dao.MBrandMapper;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.dao.MGoodsMapper;
import com.fast.base.data.dao.MGoodscategoryMapper;
import com.fast.base.data.entity.MBrand;
import com.fast.base.data.entity.MBrandExample;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.base.data.entity.MGoods;
import com.fast.base.data.entity.MGoodsExample;
import com.fast.base.data.entity.MGoodscategory;
import com.fast.base.data.entity.MGoodscategoryExample;
import com.fast.service.ext.IExtService;
import com.fast.service.impl.DataServiceImpl;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;
import com.fast.util.CommonUtil;

import net.sf.json.JSONObject;

/**
 * 外部接口
 * @author J
 *
 */
@Service
public class ExtServiceImpl implements IExtService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MExtsystemMapper extsystemMapper;
	
	@Autowired
	MGoodsMapper goodsMapper;
	
	@Autowired
	MBrandMapper brandMapper;
	
	@Autowired
	MGoodscategoryMapper goodscategoryMapper;
	
	@Autowired
	DataServiceImpl dataServiceImpl;

	@Override
	public Result colorList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/color/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.colorList报错：", e);
		}

		return result;
	}

	@Override
	public Result colorOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/color/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.colorOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result sizeList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/size/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.sizeList报错：", e);
		}

		return result;
	}

	@Override
	public Result sizeOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/size/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.sizeOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result brandList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/brand/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.brandList报错：", e);
		}

		return result;
	}

	@Override
	public Result brandOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/brand/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.brandOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result categoryList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/category/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.categoryList报错：", e);
		}

		return result;
	}

	@Override
	public Result categoryOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/category/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.categoryOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result midcategoryList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/midcategory/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.midcategoryList报错：", e);
		}

		return result;
	}

	@Override
	public Result midcategoryOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/midcategory/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.midcategoryOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result departmentList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/department/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.departmentList报错：", e);
		}

		return result;
	}

	@Override
	public Result departmentOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/department/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.departmentOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result employeeList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/employee/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.employeeList报错：", e);
		}

		return result;
	}

	@Override
	public Result employeeOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/employee/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.employeeOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result viptypeList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/viptype/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.viptypeList报错：", e);
		}

		return result;
	}

	@Override
	public Result viptypeOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/viptype/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.viptypeOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result couponList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/coupon/list";
			JSONObject object = CommonUtil.httpRequest(url, "POST", null);
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.couponList报错：", e);
		}

		return result;
	}

	@Override
	public Result couponOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/coupon/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.couponOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result goodsOne(MExtsystem extsystem, String code) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/goods/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("code", code);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.goodsOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result stockOne(MExtsystem extsystem, String extid) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/stock/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("goodsid", extid);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.stockOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result vipOne(MExtsystem extsystem, String mobilephone) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/vip/one";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("mobilephone", mobilephone);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.vipOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result vipcouponOne(MExtsystem extsystem, String mobilephone) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/vip/couponList";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("mobilephone", mobilephone);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.vipcouponOne报错：", e);
		}

		return result;
	}
	
	@Override
	public Result synchronizeQuery(String type) {
		Result result = new Result();

		try {
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			// 颜色
			if ("colorlist".equalsIgnoreCase(type)) {
				result = colorList(extsystem);
			}
			// 尺码
			else if ("sizelist".equalsIgnoreCase(type)) {
				result = sizeList(extsystem);
			}
			// 品牌
			else if ("brandlist".equalsIgnoreCase(type)) {
				result = brandList(extsystem);
			}
			// 大类
			else if ("categorylist".equalsIgnoreCase(type)) {
				result = categoryList(extsystem);
			}
			// 中类
			else if ("midcategorylist".equalsIgnoreCase(type)) {
				result = midcategoryList(extsystem);
			}
			// 门店
			else if ("departmentlist".equalsIgnoreCase(type)) {
				result = departmentList(extsystem);
			}
			// 员工
			else if ("employeelist".equalsIgnoreCase(type)) {
				result = employeeList(extsystem);
			}
			// 会员等级
			else if ("viptypelist".equalsIgnoreCase(type)) {
				result = viptypeList(extsystem);
			}
			// 优惠券
			else if ("couponlist".equalsIgnoreCase(type)) {
				result = couponList(extsystem);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.synchronize报错：", e);
		}

		return result;
	}

	@Override
	public Result queryGoodsByCode(String code) {
		Result result = new Result();

		try {
			MGoodsExample goodsExample = new MGoodsExample();
			goodsExample.createCriteria().andCodeEqualTo(code.trim());
			List<MGoods> goodsList = goodsMapper.selectByExample(goodsExample);
			if (goodsList != null && goodsList.size() > 0) {
				result.setMessage("商品已存在");
				return result;
			}
			
			MExtsystem extsystem = null;
			MExtsystemExample example = new MExtsystemExample();
			example.createCriteria().andUseflagEqualTo(Byte.valueOf("1")).andActiveEqualTo(Byte.valueOf("1"));
			List<MExtsystem> list = extsystemMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				extsystem = list.get(0);
			}
			if (extsystem == null) {
				result.setMessage("接口配置错误");
				return result;
			}
			Result r = goodsOne(extsystem, code);
			if (Common.isActive(r)) {
				com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(r.getData().toString());
				String id = jsonObject.getString("id");
				String brandcode = jsonObject.get("brandcode") == null ? "" : jsonObject.getString("brandcode");
				String categoryid = jsonObject.get("categoryid") == null ? "" : jsonObject.getString("categoryid");
				String midegoryid = jsonObject.get("midegoryid") == null ? "" : jsonObject.getString("midegoryid");
				jsonObject.put("id", null);
				MGoods goods = JSON.parseObject(jsonObject.toString(), MGoods.class);
				goods.setExtid(id);
				
				if (!Common.isEmpty(brandcode)) {
					MBrandExample brandExample = new MBrandExample();
					brandExample.createCriteria().andCodeEqualTo(brandcode.trim());
					brandExample.setOrderByClause(" useflag desc,id desc");
					List<MBrand> brands = brandMapper.selectByExample(brandExample);
					if (brands != null && brands.size() > 0) {
						goods.setBrandid(brands.get(0).getId());
					}
				}
				if (!Common.isEmpty(categoryid)) {
					MGoodscategoryExample goodscategoryExample = new MGoodscategoryExample();
					goodscategoryExample.createCriteria().andExtidEqualTo(categoryid.trim()).andGradeEqualTo(Byte.valueOf("1"));
					goodscategoryExample.setOrderByClause(" useflag desc,id desc");
					List<MGoodscategory> goodscategories = goodscategoryMapper.selectByExample(goodscategoryExample);
					if (goodscategories != null && goodscategories.size() > 0) {
						goods.setBigcategory(goodscategories.get(0).getId());
					}
				}
				if (!Common.isEmpty(midegoryid)) {
					MGoodscategoryExample goodscategoryExample = new MGoodscategoryExample();
					goodscategoryExample.createCriteria().andExtidEqualTo(midegoryid.trim()).andGradeEqualTo(Byte.valueOf("2"));
					goodscategoryExample.setOrderByClause(" useflag desc,id desc");
					List<MGoodscategory> goodscategories = goodscategoryMapper.selectByExample(goodscategoryExample);
					if (goodscategories != null && goodscategories.size() > 0) {
						goods.setMiddlecategory(goodscategories.get(0).getId());
					}
				}
				
				LinkedHashMap<String, Object> map = BeanUtil.convertObjToLinkedHashMap(goods);
				List<LinkedHashMap<String, Object>> datas = new ArrayList<>();
				datas.add(map);
				datas = CommonUtil.transformUpperCase(datas);
				datas = dataServiceImpl.completeGoods(datas);
				result.setErrcode(Integer.valueOf(0));
				result.setData(datas.get(0));
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.queryGoodsByCode报错：", e);
		}

		return result;
	}

	@Override
	public Result queryOrderStatus(MExtsystem extsystem, String no) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/POSSales/getOrderFlag";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("no", no);
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.queryOrderStatus报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipPointRecord(MExtsystem extsystem, String vipcode, String begintime, String endtime) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/vip/pointList";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("vipCode", vipcode);
			if (!Common.isEmpty(begintime)) {
				jsonObject.put("beginDate", begintime);
			}
			if (!Common.isEmpty(endtime)) {
				jsonObject.put("endDate", endtime);
			}
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.queryVipPointRecord报错：", e);
		}

		return result;
	}

	@Override
	public Result queryVipDepositRecord(MExtsystem extsystem, String vipcode, String begintime, String endtime) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/vip/storedValueList";
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("vipCode", vipcode);
			if (!Common.isEmpty(begintime)) {
				jsonObject.put("beginDate", begintime);
			}
			if (!Common.isEmpty(endtime)) {
				jsonObject.put("endDate", endtime);
			}
			JSONObject object = CommonUtil.httpRequest(url, "POST", jsonObject.toString());
			if (object != null) {
				result = com.alibaba.fastjson.JSONObject.parseObject(object.toString(), Result.class);
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用ExtServiceImpl.queryVipDepositRecord报错：", e);
		}

		return result;
	}

}
