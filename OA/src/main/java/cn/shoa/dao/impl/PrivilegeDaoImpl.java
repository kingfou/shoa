package cn.shoa.dao.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.shoa.dao.PrivilegeDao;
import cn.shoa.dao.base.impl.BaseDaoImpl;
import cn.shoa.domain.system.Privilege;
@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements
		PrivilegeDao {

	@Override
	
	public Collection<Privilege> getPrivilegeByJobs(Integer jid) {
		// TODO Auto-generated method stub
		Collection<Privilege> allPrivileges=this.getAllEntry();
		for(Privilege p:allPrivileges){p.setChecked(false);};
		Collection<Object> jobPrivilege=this.hibernateTemplate.find("from Privilege p inner join fetch p.jobs j where j.jid=?",jid);
		for(Privilege p1:allPrivileges){
			for(Object p2:jobPrivilege){
				
				if(((Privilege)p2).getId()==p1.getId()){
					p1.setChecked(true);
					
					break;//表示只要一个一样就可以直接的跳出可。
				}
			}
		}
		return allPrivileges;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection<Privilege> getMenuByUid(Integer uid,String username) {
		// TODO Auto-generated method stub
		if("admin".equals(username)){
			return new ArrayList(this.hibernateTemplate.find("from Privilege where flag='1'"));
		}
		StringBuffer sb=new StringBuffer();
		sb.append("from Privilege p inner join fetch p.jobs j inner join fetch j.users u ");
		sb.append("where u.uid=? and flag='1'");
		
		
		return new ArrayList(this.hibernateTemplate.find(sb.toString(),uid));
		
	}
	
	

}
