package cn.shoa.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.shoa.domain.menu.Menuitem;
import cn.shoa.service.MenuitemService;
import cn.shoa.struts2.action.base.BaseAction;
@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{

	private static final long serialVersionUID = 1L;
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	public String show(){
		Collection<Menuitem> itemList=menuitemService.getAllMenu();		
		ActionContext.getContext().getValueStack().push(itemList);
		for(Menuitem i:itemList){
			System.out.println(i.getName());
		}
		return SUCCESS;
	}
}
