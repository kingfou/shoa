package cn.shoa.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shoa.dao.PrivilegeDao;
import cn.shoa.domain.system.Privilege;
import cn.shoa.service.PrivilegeService;
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;

	@Override
	public Collection<Privilege> getPrivilegeByJobs(Integer jid) {
		// TODO Auto-generated method stub
		return privilegeDao.getPrivilegeByJobs(jid);
	}

	@Override
	public Collection<Privilege> getMenuByUid(Integer uid,String username) {
		// TODO Auto-generated method stub
		return privilegeDao.getMenuByUid(uid,username);
	}
}
