package cn.shoa.service;

import java.util.Collection;

import cn.shoa.domain.system.Department;

public interface DepartmentService {
	Collection<Department> getAllDepartments();
	public void saveDepartment(Department department);
	public void deleteDepartment(int id);
	public Department getDepartmentById(int id);
	public void updateDepartment(Department department);
}
