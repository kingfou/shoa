package cn.shoa.dao;

import cn.shoa.dao.base.BaseDao;
import cn.shoa.domain.system.User;

public interface UserDao extends BaseDao<User> {
	public User getUserByNameAndPwd(String name,String password);
}
