package cn.shoa.domain.system;

import java.io.Serializable;
import java.util.Set;

public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	private int did;
	private String name;
	private String description;
	private Set<User> users;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
