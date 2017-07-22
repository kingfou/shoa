package cn.shoa.test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.shoa.domain.system.Department;
import cn.shoa.domain.system.User;
import cn.shoa.service.DepartmentService;
import cn.shoa.service.JobService;
import cn.shoa.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")

public class TestUtil {
	@Resource(name="departmentService")
	DepartmentService dp;
	
	@Resource(name="jobService")
	JobService jobService;
	
	@Resource(name="userService")
	UserService userService;
	
	@Test
	@Transactional(readOnly=false)
	public void test2() {
		//hibernate的加载原理：对象和集合存在懒加载
		//根源：为什么这里会出现懒加载的问题？原因就是
		//我们要把一整个的内容作为一个事务！
		//不然的话就会出现懒加载，因此执行完一句语句session就关闭了。
		//hibernateTemplate会字段关闭sesson，如果执行完事务的话，
		//但是此时的事务是再service里面定义的，也就是我们的事务是再service
		//里卖弄才存在的，其他地方会自己执行完我们的sql语句就直接关闭
		Department dep=dp.getDepartmentById(2);
		System.out.println(dep.getName());
		Set<User> users=dep.getUsers();
		for(User u:users){
			System.out.println(u.getUsername());
		}
		Department dep1=new Department();
		dep1.setName("xssax");
		dep1.setDescription("xasxasxasxas");
		dp.saveDepartment(dep1);
		//junit测试会自动回滚数据.所以我加上rollback=false注解就可以了.
	}
	@Test
	@Transactional(readOnly=false)
	public void test3(){
		String name=userService.getUserById(2).getUsername();
		System.out.println(name);
	}
	
	@Resource(name="processEngine")
	private ProcessEngine pe;
	@Test
	public void testDeploy(){
		System.out.println(pe);
	}

}
