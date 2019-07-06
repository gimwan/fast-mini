package com.fast.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fast.base.Result;
import com.fast.base.data.dao.MEmployeeMapper;
import com.fast.base.data.entity.MEmployee;
import com.fast.base.data.entity.MUser;
import com.fast.service.IEmployeeMaintService;
import com.fast.system.log.FastLog;
import com.fast.util.BeanUtil;

/**
 * 员工管理
 * @author J
 *
 */
@Service
public class EmployeeMaintServiceImpl implements IEmployeeMaintService, Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Autowired
	MEmployeeMapper employeeMapper;

	@Override
	public Result changeEmployee(MEmployee employee, MUser user) {
		Result result = new Result();

		try {
			Date now = new Date();
			MEmployee mEmployee = new MEmployee();
			employee.setUpdatedtime(now);
			if (employee.getId() != null) {
				mEmployee = employeeMapper.selectByPrimaryKey(employee.getId());
				BeanUtil.copyPropertiesIgnoreNull(employee, mEmployee);
				mEmployee.setModifier(user.getName());
				mEmployee.setModifytime(now);
				int changeNum = employeeMapper.updateByPrimaryKeySelective(mEmployee);
				if (changeNum > 0) {
					result.setErrcode(0);
					result.setId(mEmployee.getId());
					result.setData(mEmployee);
					result.setMessage("保存成功");
				} else {
					result.setMessage("保存失败");
				}
			} else {
				BeanUtil.copyPropertiesIgnoreNull(employee, mEmployee);
				mEmployee.setCreator(user.getName());
				mEmployee.setCreatetime(now);
				int key = employeeMapper.insertSelective(mEmployee);
				if (key > 0) {
					result.setErrcode(0);
					result.setId(mEmployee.getId());
					result.setData(mEmployee);
					result.setMessage("新增成功");
				} else {
					result.setMessage("新增失败");
				}
			}
			
		} catch (Exception e) {
			result.setMessage(e.getMessage());
			FastLog.error("调用EmployeeMaintServiceImpl.changeEmployee报错：", e);
		}

		return result;
	}

}
