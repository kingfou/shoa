package cn.shoa.service.impl;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shoa.dao.JobDao;
import cn.shoa.dao.PrivilegeDao;
import cn.shoa.domain.system.Job;
import cn.shoa.domain.system.Privilege;
import cn.shoa.service.JobService;

@Service("jobService")
public class JobServiceImpl implements JobService{
	@Resource(name="jobDao")
	private JobDao jobDao;
	
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;
	@Override
	public Collection<Job> getAllJobs() {
		// TODO Auto-generated method stub
		return jobDao.getAllEntry();
	}

	@Override
	public void saveJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.saveEntry(job);
	}

	@Override
	public void deleteJob(int id) {
		// TODO Auto-generated method stub
		jobDao.deleteEntry(id);
	}

	@Override
	public Job getJobById(int id) {
		// TODO Auto-generated method stub
		return jobDao.getEntryById(id);
	}

	@Override
	public void updateJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.updateEntry(job);
	}

	@Override
	public Collection<Job> getEntrysByIds(Integer[] jids) {
		// TODO Auto-generated method stub
		return jobDao.getEntrysByIds(jids);
	}

	@Override
	public Collection<Job> getUserJobs(Integer uid) {
		// TODO Auto-generated method stub
		//次方法的所有就是将uid有的标志好；
		
		return jobDao.getUserJob(uid);
	}

	@Override
	public void updatePrivilege(Integer[] ids, Job job) {
		// TODO Auto-generated method stub
		Set<Privilege> privileges=new HashSet(privilegeDao.getEntrysByIds(ids));
		job.setPrivileges(privileges);
		jobDao.updateEntry(job);
	}

	
}
