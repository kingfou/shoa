package cn.shoa.struts2.action;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.opensymphony.xwork2.ActionContext;

import cn.shoa.dao.JobDao;
import cn.shoa.domain.system.Department;
import cn.shoa.domain.system.Job;
import cn.shoa.domain.system.User;
import cn.shoa.service.DepartmentService;
import cn.shoa.service.JobService;
import cn.shoa.service.UserService;
import cn.shoa.struts2.action.base.BaseAction;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="jobService")
	private JobService jobService;
	
	@Resource(name="jobDao")
	private JobDao jobDao;
	private Integer[] jids;
	public void setJids(Integer[] jids) {
		this.jids = jids;
	}
	public Integer[] getJids() {
		return jids;
	}
	
	private Integer did;
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getDid() {
		return did;
	}
	
	
	public String showUsers(){
		Collection<User> UserList=userService.getAllUsers();
		//表示将数据放到map栈里面；
		//值栈里面分为对象栈和map栈，也就是和OGNL的根和ActionContext一样。访问对象栈可以直接访问
		//访问map栈的时候选需要加#进行访问。
		ActionContext.getContext().put("userList", UserList);
		return listAction;
	}
	
	public String addUI(){
		Collection<Department> dList=departmentService.getAllDepartments();
		Collection<Job> jList=jobService.getAllJobs();
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("jList", jList);
		
		//跳转到添加页面
		return addUI;
	}
	
	public String add(){
		User user=new User();
		BeanUtils.copyProperties( getModel(),user);
		
		//自己维护jobs的关系
		for(int i:jids){
			System.out.println(i);
		}
		
		userService.saveUser(did,jids,user);
		return action2action;
	}
	
	public String delete(){
		int id=getModel().getUid();

		userService.deleteUser(id);
		return action2action;
	}
	
	public String updateUI(){
		int uid=getModel().getUid();
		System.out.println(uid);
		User user=userService.getUserById(uid);
		this.did=user.getDepartment().getDid();
		this.jids=new Integer[user.getJobs().size()];
		int i=0;
		for(Job job:user.getJobs()){
			jids[i]=job.getJid();
			i++;
		}
		i=0;
		Collection<Department> dList=departmentService.getAllDepartments();
		Collection<Job> jList=jobService.getAllJobs();
		//我们得将要默认回显得数据直接放到栈顶！！
		ActionContext.getContext().getValueStack().push(user);
		ActionContext.getContext().put("dList", dList);
		ActionContext.getContext().put("jList", jList);
		return updateUI;
	}
	
	public String update(){
		User user=new User();
		//更新得前提就是id,所以要有一个隐藏域对id进行传递
		BeanUtils.copyProperties(getModel(), user);
		userService.updateUser(did,jids,user);
		return action2action;
	}
	
	//用户登陆验证：
	public String login(){
		String username=getModel().getUsername();
		String password=getModel().getPassword();
		if("".equals(username)||username==null||password==null||"".equals(password)){
			this.addActionMessage("密码或者账户错误！");
			return LOGIN;
		}
		User user=userService.checkedUser(username,password);
		if(user==null){
			this.addActionMessage("密码或者账户错误！");
			return LOGIN;
		}else{
			HttpSession session = null;
			session = ServletActionContext.getRequest().getSession();
			session.setAttribute("user", user);//登陆后直接将user放到session里面就行
			return "frame";
		}
		
	}
	
	
	//系统退出：
	public String logOut(){
		HttpSession session = null;
		session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("user");
		return "logOut";
	}
	

}
