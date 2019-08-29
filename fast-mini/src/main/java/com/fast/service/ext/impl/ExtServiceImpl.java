package com.fast.service.ext.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MExtsystemMapper;
import com.fast.base.data.entity.MExtsystem;
import com.fast.base.data.entity.MExtsystemExample;
import com.fast.service.ext.IExtService;
import com.fast.system.log.FastLog;
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

	@Override
	public Result colorList(MExtsystem extsystem) {
		Result result = new Result();

		try {
			String url = extsystem.getServeraddress() + "/api/color/list";
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", null);
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
			JSONObject object = CommonUtil.httpsRequest(url, "POST", jsonObject.toString());
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

}
