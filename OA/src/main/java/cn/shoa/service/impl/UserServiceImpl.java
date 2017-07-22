package cn.shoa.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shoa.dao.DepartmentDao;
import cn.shoa.dao.JobDao;
import cn.shoa.dao.UserDao;
import cn.shoa.domain.system.Department;
import cn.shoa.domain.system.Job;
import cn.shoa.domain.system.User;
import cn.shoa.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="jobDao")
	private JobDao jobDao;
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllEntry();
	}

	@Override
	public void saveUser(Integer did, Integer[] jids, User user) {
		// TODO Auto-generated method stub
		Department department=departmentDao.getEntryById(did);
		user.setDepartment(department);
		System.out.println(jids.length);
		if(jids.length>0){
		Set<Job> jobs=new HashSet(jobDao.getEntrysByIds(jids));		
		user.setJobs(jobs);}
		userDao.saveEntry(user);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.deleteEntry(id);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getEntryById(id);
	}

	@Override
	public void updateUser(Integer did,Integer []jids,User user) {
		// TODO Auto-generated method stub
		Department department=departmentDao.getEntryById(did);
		user.setDepartment(department);
		System.out.println(jids.length);
		if(jids.length>0){
		Set<Job> jobs=new HashSet(jobDao.getEntrysByIds(jids));		
		user.setJobs(jobs);}
		userDao.updateEntry(user);
	}

	@Override
	public void updateUser(Integer[] jids, User user) {
		// TODO Auto-generated method stub
		if(jids.length>0){
			@SuppressWarnings("unchecked")
			Set<Job> jobs=new HashSet(jobDao.getEntrysByIds(jids));		
			user.setJobs(jobs);
			}
		//我们是对一个已经存在的user进行的数据的操作，所有一般采用的就是直接的更新就行。
		userDao.updateEntry(user);
	}

	@Override
	public User checkedUser(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.getUserByNameAndPwd(name, password);
	}



	

}
