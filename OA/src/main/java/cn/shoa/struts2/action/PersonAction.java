package cn.shoa.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.shoa.domain.Person;
import cn.shoa.service.PersonService;
import cn.shoa.struts2.action.base.BaseAction;

import com.opensymphony.xwork2.ActionSupport;
@Controller("personAction")
@Scope("prototype")
public class PersonAction extends BaseAction<Person> {
	//Resource(name="id")相当于为一个bean写了一个ref=name这个标签
	//其他的Controller(""),Repository(""),Service("")就只是说明是<bean id="">标签而已！！！

	private static final long serialVersionUID = 1L;
	
	@Resource(name="personService")
	private PersonService personService;

	 
	public String save(){
		Person p=new Person();
		p.setName("李四");
		p.setAge(22);
		p.setSex("男");
		personService.savePerson(p);
		return null;
	}

}
