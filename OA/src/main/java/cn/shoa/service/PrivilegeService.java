package cn.shoa.service;

import java.util.Collection;

import cn.shoa.domain.system.Privilege;

public interface PrivilegeService {
	public Collection<Privilege> getPrivilegeByJobs(Integer jid);

	public Collection<Privilege> getMenuByUid(Integer uid, String string);
}
