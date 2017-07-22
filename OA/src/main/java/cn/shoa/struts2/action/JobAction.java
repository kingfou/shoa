package cn.shoa.struts2.action;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.shoa.domain.system.Job;
import cn.shoa.domain.system.User;
import cn.shoa.service.JobService;
import cn.shoa.service.UserService;
import cn.shoa.struts2.action.base.BaseAction;
@Controller("jobAction")
@Scope("prototype")
public class JobAction extends BaseAction<Job> {

	private static final long serialVersionUID = 1L;
	@Resource(name="jobService")
	private JobService jobService;
	
	@Resource(name="userService")
	private UserService userService;
	
	//得到传进来的用于交互的数据：
	private Integer uid;
	private String param;
	
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String showJobs(){
		Collection<Job> JobList=jobService.getAllJobs();
		//表示将数据放到map栈里面；
		//值栈里面分为对象栈和map栈，也就是和OGNL的根和ActionContext一样。访问对象栈可以直接访问
		//访问map栈的时候选需要加#进行访问。
		ActionContext.getContext().put("jobList", JobList);
		return listAction;
	}
	
	public String addUI(){
		//跳转到添加页面
		return addUI;
	}
	
	public String add(){
		Job Job=new Job();
		BeanUtils.copyProperties( getModel(),Job);
		if(Job.getName()==null
				||"".equals(Job.getName())
				||Job.getDescription()==null
				||"".equals(Job.getDescription())){
			this.addActionError("数据不能为空！！");
			return addUI;
		}
		jobService.saveJob(Job);
		return action2action;
	}
	
	public String delete(){
		int id=getModel().getJid();//多对多两边都维护关系，因此可以直接删除
		jobService.deleteJob(id);
		return action2action;
	}
	
	public String updateUI(){
		int jid=getModel().getJid();
		Job Job=jobService.getJobById(jid);
		ActionContext.getContext().getValueStack().push(Job);
		return updateUI;
	}
	
	public String update(){
		Job Job=new Job();
		BeanUtils.copyProperties(getModel(), Job);
		jobService.updateJob(Job);
		return action2action;
	}
	
	//返回json格式的数据：
	public String getJSON() throws InterruptedException{
		//我们需要返回的是带有回显内容的所有的数据
		
		Collection<Job> JobTree=jobService.getUserJobs(uid);

		ActionContext.getContext().getValueStack().push(JobTree);
	
		Thread.sleep(1000);
		
		return SUCCESS;
	}
	
	//对用户个职业进行关系的更新：
	public String saveJobsAandUser(){
		//获取用户---根据uid
		//获取树的选择框的值  ---jids;
		//进行关系的建立；
		User user=userService.getUserById(uid);
		String [] index=param.split(",");
		Integer[] jids=new Integer[index.length];
		
		for(int i=0;i<jids.length;i++){
			jids[i]=Integer.valueOf(index[i]);
		}
		
		userService.updateUser(jids, user);
		
		return action2action;
	}

}
