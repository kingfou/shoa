package cn.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.shoa.dao.UserDao;
import cn.shoa.dao.base.impl.BaseDaoImpl;
import cn.shoa.domain.system.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User getUserByNameAndPwd(String name, String password) {
		// TODO Auto-generated method stub
		return (User) this.hibernateTemplate.find("from User where username='"+name+"' and password='"+password+"'").get(0);
	}
	

}
