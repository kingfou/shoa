package cn.shoa.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shoa.dao.DepartmentDao;
import cn.shoa.domain.system.Department;
import cn.shoa.service.DepartmentService;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	@Override
	public Collection<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentDao.getAllEntry();
	}
	@Override
	public void saveDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentDao.saveEntry(department);
	}
	@Override
	public void deleteDepartment(int id) {
		// TODO Auto-generated method stub
		departmentDao.deleteEntry(id);
	}
	@Override
	public Department getDepartmentById(int id) {
		// TODO Auto-generated method stub
		return departmentDao.getEntryById(id);
	}
	@Override
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentDao.updateEntry(department);
	}

}
