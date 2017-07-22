package cn.shoa.dao;

import java.util.Collection;

import cn.shoa.dao.base.BaseDao;
import cn.shoa.domain.system.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege>{
	public Collection<Privilege> getPrivilegeByJobs(Integer jid);

	public Collection<Privilege> getMenuByUid(Integer uid, String username);
}
