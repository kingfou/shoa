package cn.shoa.dao;

import java.util.Collection;

import cn.shoa.dao.base.BaseDao;
import cn.shoa.domain.system.Job;

public interface JobDao extends BaseDao<Job> {
	public Collection<Job> getUserJob(Integer uid);
}
