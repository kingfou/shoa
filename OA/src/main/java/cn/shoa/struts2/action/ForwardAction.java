package cn.shoa.struts2.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("forwardAction")
@Scope("prototype")
public class ForwardAction extends ActionSupport{


	private static final long serialVersionUID = 1L;
	private String method;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	public String forward(){
		
		return this.method;
	}
}
