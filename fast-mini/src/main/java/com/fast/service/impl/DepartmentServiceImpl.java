package com.fast.service.impl;

import java.io.Serializable;
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
import com.fast.service.IDepartmentService;
import com.fast.system.log.FastLog;
import com.fast.util.Common;

/**
 * 门店管理
 * @author J
 *
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MDepartmentMapper mDepartmentMapper;
	
	@Autowired
	MRegionMapper regionMapper;
	
	@Override
	public Result department() {
		Result result = new Result();

		try {
			MDepartmentExample example = new MDepartmentExample();
			example.setOrderByClause("code asc");
			List<MDepartment> list = mDepartmentMapper.selectByExample(example);
			result.setErrcode(0);
			result.setData(list);
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用DepartmentServiceImpl.department报错：", e);
		}

		return result;
	}

	@Override
	public MDepartment resetDepartmentRegion(MDepartment department) {
		
		try {
			if (!Common.isEmpty(department.getProvince())) {
				MRegionExample regionExample = new MRegionExample();
				regionExample.createCriteria().andTypeEqualTo(Byte.valueOf("2")).andNameLike("%"+department.getProvince()+"%");
				List<MRegion> regions = regionMapper.selectByExample(regionExample);
				if (regions != null && regions.size() > 0) {
					department.setProvince(regions.get(0).getName());
					department.setProvinceid(regions.get(0).getId());
				}
			}
			if (!Common.isEmpty(department.getCity())) {
				MRegionExample regionExample = new MRegionExample();
				regionExample.createCriteria().andTypeEqualTo(Byte.valueOf("3")).andNameLike("%"+department.getCity()+"%");
				List<MRegion> regions = regionMapper.selectByExample(regionExample);
				if (regions != null && regions.size() > 0) {
					department.setCity(regions.get(0).getName());
					department.setCityid(regions.get(0).getId());
				}
			}
			if (!Common.isEmpty(department.getCounty())) {
				MRegionExample regionExample = new MRegionExample();
				regionExample.createCriteria().andTypeEqualTo(Byte.valueOf("4")).andNameLike("%"+department.getCounty()+"%");
				List<MRegion> regions = regionMapper.selectByExample(regionExample);
				if (regions != null && regions.size() > 0) {
					department.setCounty(regions.get(0).getName());
					department.setCityid(regions.get(0).getId());
				}
			}
		} catch (Exception e) {
			FastLog.error("调用DepartmentServiceImpl.resetDepartmentRegion报错：", e);
		}
		
		return department;
	}

}
