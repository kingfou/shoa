package cn.shoa.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import cn.shoa.dao.JobDao;
import cn.shoa.dao.base.impl.BaseDaoImpl;
import cn.shoa.domain.system.Job;
@Repository("jobDao")
public class JobDaoImpl extends BaseDaoImpl<Job> implements JobDao {

	@Override
	public Collection<Job> getUserJob(Integer uid) {
		// TODO Auto-generated method stub
		Collection<Job> allJobs=this.getAllEntry();
		for(Job j:allJobs){j.setChecked(false);}
		Collection<Object> userJobs=this.hibernateTemplate.find("from Job j inner join fetch j.users u where u.uid=?",uid);
		
		for(Job job1:allJobs){
			for(Object job2:userJobs){
				Job job3=(Job)job2;
				if(job3.getJid()==job1.getJid()){
					job1.setChecked(true);//表示当前的job是user里面有的
					break;
				}
			}
		}
		
		return allJobs;
	}


}
