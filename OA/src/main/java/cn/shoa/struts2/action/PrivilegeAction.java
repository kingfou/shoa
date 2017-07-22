package cn.shoa.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.shoa.domain.system.Job;
import cn.shoa.domain.system.Privilege;
import cn.shoa.domain.system.User;
import cn.shoa.service.JobService;
import cn.shoa.service.PrivilegeService;
import cn.shoa.struts2.action.base.BaseAction;
@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege>{

	private static final long serialVersionUID = 1L;

	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	@Resource(name="jobService")
	private JobService jobService;
	
	//接受传进来的参数：
	Integer jid;
	String param;
	public Integer getJid() {
		return jid;
	}
	public void setJid(Integer jid) {
		this.jid = jid;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	public String showPrivilegeByJobs() throws InterruptedException{
		Collection<Privilege> privilegeJSON=privilegeService.getPrivilegeByJobs(jid);
		ActionContext.getContext().getValueStack().push(privilegeJSON);
		Thread.sleep(3000);
		return SUCCESS;
	}
	
	//更新关系：
	public String updatePrivilege(){
		Job job=jobService.getJobById(jid);
		String [] sb=param.split(",");
		Integer ids[]=new Integer[sb.length];
		for(int i=0;i<ids.length;i++){
			ids[i]=Integer.valueOf(sb[i]);
		}
		jobService.updatePrivilege(ids, job);
		return null;
	}
	
	public String showItems(){
		HttpSession session = null;
		session = ServletActionContext.getRequest().getSession();
		User user=(User) session.getAttribute("user");
		Collection<Privilege> items=privilegeService.getMenuByUid(user.getUid(),user.getUsername());
		/*for(Privilege p:items){
			System.out.println(p.getName()+"--"+p.getId());
		}*/
		ActionContext.getContext().getValueStack().push(items);
		return SUCCESS;
	}
	
}
