package cn.shoa.struts2.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.components.ActionError;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.shoa.domain.system.Department;
import cn.shoa.domain.system.User;
import cn.shoa.service.DepartmentService;
import cn.shoa.struts2.action.base.BaseAction;

@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
	
	private static final long serialVersionUID = 1L;
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	
	
	public String showDepartments(){
		Collection<Department> departmentList=departmentService.getAllDepartments();
		//表示将数据放到map栈里面；
		//值栈里面分为对象栈和map栈，也就是和OGNL的根和ActionContext一样。访问对象栈可以直接访问
		//访问map栈的时候选需要加#进行访问。
		ActionContext.getContext().put("departmentList", departmentList);
		return listAction;
	}
	
	public String addUI(){
		//跳转到添加页面
		return addUI;
	}
	
	public String add(){
		Department department=new Department();
		BeanUtils.copyProperties( getModel(),department);
		if(department.getName()==null
				||"".equals(department.getName())
				||department.getDescription()==null
				||"".equals(department.getDescription())){
			this.addActionError("数据不能为空！！");
			return addUI;
		}
		departmentService.saveDepartment(department);
		return action2action;
	}
	
	public String delete(){
		int id=getModel().getDid();
		
		Department department=departmentService.getDepartmentById(id);
		Set<User> users=department.getUsers();
		for(User u:users){
			//对外键进行维护，设置为空
			u.setDepartment(null);
		}
		departmentService.deleteDepartment(id);
		return action2action;
	}
	
	public String updateUI(){
		int did=getModel().getDid();
		Department department=departmentService.getDepartmentById(did);
		ActionContext.getContext().getValueStack().push(department);
		return updateUI;
	}
	
	public String update(){
		Department department=new Department();
		BeanUtils.copyProperties(getModel(), department);
		departmentService.updateDepartment(department);
		return action2action;
	}
	
	
	
	

}
