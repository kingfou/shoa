package cn.shoa.service;

import java.util.Collection;

import cn.shoa.domain.system.User;

public interface UserService {
	Collection<User> getAllUsers();
	public void saveUser(Integer did,Integer[] jids,User user);
	public void deleteUser(int id);
	public User getUserById(int id);
	public void updateUser(Integer did,Integer []jids,User user);
	//方法的重构
	public void updateUser(Integer[] jids,User user);
	public User checkedUser(String name,String password);

}
