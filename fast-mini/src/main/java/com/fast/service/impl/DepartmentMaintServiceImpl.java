package com.fast.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MDepartmentMapper;
import com.fast.base.data.dao.MRegionMapper;
import com.fast.base.data.entity.MDepartment;
import com.fast.base.data.entity.MDepartmentExample;
import com.fast.base.data.entity.MRegion;
import com.fast.base.data.entity.MRegionExample;
import com.fast.base.data.entity.MUser;
import com.fast.service.IDataService;
import com.fast.service.IDepartmentMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;
import com.fast.util.Common;

/**
 * 门店
 * @author J
 *
 */
@Service
public class DepartmentMaintServiceImpl implements IDepartmentMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MDepartmentMapper departmentMapper;
	
	@Autowired
	IDataService iDataService;
	
	@Autowired
	MRegionMapper regionMapper;

	@Override
	public Result changeDepartment(MDepartment department, MUser user) {
		Result result = new Result();

		try {
			MDepartmentExample example = new MDepartmentExample();
			if (department.getId() != null) {
				example.createCriteria().andCodeEqualTo(department.getCode().trim()).andIdNotEqualTo(department.getId());
			} else {
				example.createCriteria().andCodeEqualTo(department.getCode().trim());
			}
			List<MDepartment> list = departmentMapper.selectByExample(example);
			if (list != null && list.size() > 0) {
				result.setMessage("编号不能重复");
				return result;
			}
			
			Date now = new Date();
			MDepartment mDepartment = new MDepartment();
			department.setUpdatedtime(now);
			department = completeData(department);
			if (department.getId() != null) {
				mDepartment = departmentMapper.selectByPrimaryKey(department.getId());
				BeanUtil.copyPropertiesIgnoreNull(department, mDepartment);
				mDepartment.setModifier(user.getName());
				mDepartment.setModifytime(now);
				int changeNum = departmentMapper.updateByPrimaryKeySelective(mDepartment);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mDepartment.getId());
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(department, mDepartment);
				mDepartment.setCreator(user.getName());
				mDepartment.setCreatetime(now);
				mDepartment.setTypeid(Integer.valueOf(1));
				int key = departmentMapper.insertSelective(mDepartment);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mDepartment.getId());
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
			if (Common.isActive(result)) {
				Result r = iDataService.one("department", result.getId());
				if (Common.isActive(r)) {
					result.setData(r.getData());
				}
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用DepartmentMaintServiceImpl.changeEmployee报错：", e);
		}

		return result;
	}
	
	public MDepartment completeData(MDepartment department) {
		List<Integer> idList = new ArrayList<Integer>();
		if (department.getProvinceid() != null) {
			idList.add(department.getProvinceid());
		}
		if (department.getCityid() != null) {
			idList.add(department.getCityid());
		}
		if (department.getCountyid() != null) {
			idList.add(department.getCountyid());
		}
		if (idList != null && idList.size() > 0) {
			MRegionExample example = new MRegionExample();
			example.createCriteria().andIdIn(idList);
			List<MRegion> list = regionMapper.selectByExample(example);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId().intValue() == department.getProvinceid().intValue()) {
						department.setProvince(list.get(i).getName());
					}
					if (list.get(i).getId().intValue() == department.getCityid().intValue()) {
						department.setCity(list.get(i).getName());
					}
					if (list.get(i).getId().intValue() == department.getCountyid().intValue()) {
						department.setCounty(list.get(i).getName());
					}
				}
			}
		}
		return department;
	}

	@Override
	public Result deleteDepartment(Integer id) {
		Result result = new Result();

		try {
			int i = departmentMapper.deleteByPrimaryKey(id);
			if (i > 0) {
				result.setErrcode(0);
				result.setMessage("删除成功");
			} else {
				result.setMessage("删除失败");
			}
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用DepartmentMaintServiceImpl.deleteDepartment报错：", e);
		}

		return result;
	}

}
