package cn.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.shoa.dao.DepartmentDao;
import cn.shoa.dao.base.impl.BaseDaoImpl;
import cn.shoa.domain.system.Department;
@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {


	
}
