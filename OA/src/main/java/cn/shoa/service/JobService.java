package cn.shoa.service;

import java.util.Collection;

import cn.shoa.domain.system.Job;

public interface JobService {
	Collection<Job> getAllJobs();
	public void saveJob(Job job);
	public void deleteJob(int id);
	public Job getJobById(int id);
	public void updateJob(Job job);
	public Collection<Job> getEntrysByIds(Integer []jids);
	Collection<Job> getUserJobs(Integer uid);
	public void updatePrivilege(Integer []ids,Job job);
	
}
