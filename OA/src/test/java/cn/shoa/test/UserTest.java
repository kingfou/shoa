package cn.shoa.test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jbpm.api.ProcessEngine;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import cn.shoa.dao.JobDao;
import cn.shoa.dao.PrivilegeDao;
import cn.shoa.dao.UserDao;
import cn.shoa.domain.menu.Menuitem;
import cn.shoa.domain.system.Job;
import cn.shoa.domain.system.Privilege;
import cn.shoa.domain.system.User;
import cn.shoa.service.JobService;
import cn.shoa.service.UserService;

public class UserTest {

	@SuppressWarnings({ "unchecked", "unchecked", "unchecked" })
	@Test
	public void test1() {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		
		SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
		Session session=sessionFactory.openSession();
		Transaction t=session.beginTransaction();
		/*List<User> list=session.createQuery("from User").list();
		System.out.println(((User)list.get(0)).getUsername());
		System.out.println(((User)list.get(0)).getDepartment());*/
		
		//不取消懒加载的话，可以使用抓取策略进行数据封装
		
		/*System.out.println("------------------");
		@SuppressWarnings("unused")
		List<User> list2=session.createQuery("from User u left outer join fetch u.department d left join fetch u.jobs j").list();
		System.out.println(list2.get(1).getDepartment().getName());*/
		User uu=(User) session.createQuery("from User where id=1").uniqueResult();
		uu.setEmail("xaxasx11");//会和一级缓存里面的快照进行比较，看看是否在提交的时候需要执行我么你的update语句
		//hibernate的批量更新操作只要就是降低数据库的访问此事，提高效率。 
		//commit之前的操作就是事务！commit后会发出各种sql语句
		uu.getDepartment().setName("前台小姐");
		uu.getDepartment().setDescription("test it for change!");
		
		//由此可知，在session还没有关闭之前，我们都是可以去得到数据的，这就是懒加载的基础，
		//但是在事务的时候，我们要注意的就是session在什么时候关闭，不然jsp页面在取数据的时候就会遇到懒加载的session关闭问题
		//使用sessionFactory.openSession的session和spring事务管理的不是同一个session!!所以会造成session没有关闭
		t.commit();
		session.close();
	}
	
	@Test 
	@Transactional
	public void test2(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		JobDao jobDao=(JobDao) ac.getBean("jobDao");
		Integer []ids={1,3,5,6,8};
		Set<Job> jobs=(Set<Job>) jobDao.getEntrysByIds(ids);
		for(Job j:jobs){
			System.out.println(j.getName());
		}
		
	}
	
	
	@Test 
	@Transactional
	public void test3(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		UserService us=(UserService) ac.getBean("userService");
		int i=us.getUserById(12).getDepartment().getDid();
		System.out.println(i);
	}
	
	@Test
	public void test6(){
		 ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
			
			SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
			Session session=sessionFactory.openSession();
			Transaction t=session.beginTransaction();
			
			
			Menuitem Menuitemitem1 = new Menuitem();
			Menuitemitem1.setMid(1L);
			Menuitemitem1.setIcon("css/images/MenuIcon/FUNC20082.gif");
			Menuitemitem1.setName("办公自动化");
			Menuitemitem1.setPid(0L);
			//Menuitemitem1.setChecked(false);
			Menuitemitem1.setParentis(true);
			
			Menuitem Menuitem2 = new Menuitem();
			Menuitem2.setMid(2L);
			Menuitem2.setIcon("css/images/MenuIcon/FUNC20001.gif");
			Menuitem2.setName("个人办公");
			//Menuitem2.setChecked(false);
			Menuitem2.setPid(1L);
			Menuitem2.setParentis(true);
			
			Menuitem Menuitem21 = new Menuitem();
			Menuitem21.setMid(21L);
			Menuitem21.setIcon("css/images/MenuIcon/FUNC20054.gif");
			Menuitem21.setName("个人考勤");
			//Menuitem21.setChecked(false);
			Menuitem21.setPid(2L);
			Menuitem21.setParentis(false);
			
			
			Menuitem Menuitem22 = new Menuitem();
			Menuitem22.setMid(22L);
			Menuitem22.setIcon("css/images/MenuIcon/FUNC23700.gif");
			Menuitem22.setName("日程安排");
			//Menuitem22.setChecked(false);
			Menuitem22.setPid(2L);
			Menuitem22.setParentis(false);
			
			Menuitem Menuitem23 = new Menuitem();
			Menuitem23.setMid(23L);
			Menuitem23.setIcon("css/images/MenuIcon/FUNC20069.gif");
			Menuitem23.setName("工作计划");
			//Menuitem23.setChecked(false);
			Menuitem23.setPid(2L);
			Menuitem23.setParentis(false);
			
			Menuitem Menuitem24 = new Menuitem();
			Menuitem24.setMid(24L);
			Menuitem24.setIcon("css/images/MenuIcon/FUNC20056.gif");
			Menuitem24.setName("工作日记");
			//Menuitem24.setChecked(false);
			Menuitem24.setPid(2L);
			Menuitem24.setParentis(false);
			
			Menuitem Menuitem25 = new Menuitem();
			Menuitem25.setMid(25L);
			Menuitem25.setIcon("css/images/MenuIcon/time_date.gif");
			Menuitem25.setName("通讯录");
			//Menuitem25.setChecked(false);
			Menuitem25.setPid(2L);
			Menuitem25.setParentis(false);
	/*********************************************************************************/	
			/*
			 * 审批流转
			 */
			Menuitem Menuitem3 = new Menuitem();
			Menuitem3.setMid(3L);
			///Menuitem3.setChecked(false);
			Menuitem3.setParentis(true);
			Menuitem3.setPid(1L);
			Menuitem3.setName("审批流转");
			Menuitem3.setIcon("css/images/MenuIcon/FUNC20057.gif");
			
			Menuitem Menuitem31 = new Menuitem();
			Menuitem31.setMid(31L);
			//Menuitem31.setChecked(false);
			Menuitem31.setParentis(false);
			Menuitem31.setPid(3L);
			Menuitem31.setName("审批流程管理");
			Menuitem31.setIcon("css/images/MenuIcon/manager.gif");
			
			Menuitem Menuitem32 = new Menuitem();
			Menuitem32.setMid(32L);
			//Menuitem32.setChecked(false);
			Menuitem32.setParentis(false);
			Menuitem32.setPid(3L);
			Menuitem32.setName("表单模板管理");
			Menuitem32.setIcon("css/images/MenuIcon/formmodel.gif");
			
			Menuitem Menuitem33 = new Menuitem();
			Menuitem33.setMid(33L);
			Menuitem33.setParentis(false);
			//Menuitem33.setChecked(false);
			Menuitem33.setPid(3L);
			Menuitem33.setName("发起申请");
			Menuitem33.setIcon("css/images/MenuIcon/FUNC241000.gif");
			
			Menuitem Menuitem34 = new Menuitem();
			Menuitem34.setMid(34L);
			Menuitem34.setParentis(false);
			//Menuitem34.setChecked(false);
			Menuitem34.setPid(3L);
			Menuitem34.setName("审批申请");
			Menuitem34.setIcon("css/images/MenuIcon/FUNC20029.gif");
			
			Menuitem Menuitem35 = new Menuitem();
			Menuitem35.setMid(35L);
			Menuitem35.setParentis(false);
			//Menuitem35.setChecked(false);
			Menuitem35.setPid(3L);
			Menuitem35.setName("状态查询");
			Menuitem35.setIcon("css/images/MenuIcon/FUNC20029.gif");
	/************************************************************************************/
			/*
			 * 知识管理
			 */
			Menuitem Menuitem4 = new Menuitem();
			Menuitem4.setMid(4L);
			Menuitem4.setParentis(false);
			//Menuitem4.setChecked(false);
			Menuitem4.setPid(1L);
			Menuitem4.setName("知识管理");
			Menuitem4.setIcon("css/images/MenuIcon/FUNC20056.gif");
	/*******************************************************************************/
			/*
			 * 综合行政
			 */
			Menuitem Menuitem5 = new Menuitem();
			Menuitem5.setMid(5L);
			Menuitem5.setParentis(true);
			//Menuitem5.setChecked(false);
			Menuitem5.setPid(1L);
			Menuitem5.setName("管理行政");
			Menuitem5.setIcon("css/images/MenuIcon/manager.gif");
			
			Menuitem Menuitem51 = new Menuitem();
			Menuitem51.setMid(51L);
			Menuitem51.setParentis(false);
			//Menuitem51.setChecked(false);
			Menuitem51.setPid(5L);
			Menuitem51.setName("考勤管理");
			Menuitem51.setIcon("css/images/MenuIcon/FUNC20070.gif");
			
			Menuitem Menuitem52 = new Menuitem();
			Menuitem52.setMid(52L);
			Menuitem52.setParentis(false);
			//Menuitem52.setChecked(false);
			Menuitem52.setPid(5L);
			Menuitem52.setName("会议管理");
			Menuitem52.setIcon("css/images/MenuIcon/FUNC20064.gif");
			
			Menuitem Menuitem53 = new Menuitem();
			Menuitem53.setMid(53L);
			Menuitem53.setParentis(false);
			//Menuitem53.setChecked(false);
			Menuitem53.setPid(5L);
			Menuitem53.setName("车辆管理");
			Menuitem53.setIcon("css/images/MenuIcon/radio_blue.gif");
	/**************************************************************************************/
			/*
			 * 人力资源管理
			 * 	档案管理
			 * 	培训记录
			 * 	奖金记录
			 * 	职位变更
			 * 	人事合同
			 * 	薪酬制度
			 */
			Menuitem Menuitem6 = new Menuitem();
			Menuitem6.setMid(6L);
			Menuitem6.setParentis(true);
			//Menuitem6.setChecked(false);
			Menuitem6.setPid(1L);
			Menuitem6.setName("人力资源");
			Menuitem6.setIcon("css/images/MenuIcon/FUNC20001.gif");
			
			Menuitem Menuitem61 = new Menuitem();
			Menuitem61.setMid(61L);
			Menuitem61.setParentis(false);
			//Menuitem61.setChecked(false);
			Menuitem61.setPid(6L);
			Menuitem61.setName("档案管理");
			Menuitem61.setIcon("css/images/MenuIcon/FUNC20076.gif");
			
			Menuitem Menuitem62 = new Menuitem();
			Menuitem62.setMid(62L);
			Menuitem62.setParentis(false);
			//Menuitem62.setChecked(false);
			Menuitem62.setPid(6L);
			Menuitem62.setName("培训记录");
			Menuitem62.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Menuitem Menuitem63 = new Menuitem();
			Menuitem63.setMid(63L);
			Menuitem63.setParentis(false);
			//Menuitem63.setChecked(false);
			Menuitem63.setPid(6L);
			Menuitem63.setName("奖赏记录");
			Menuitem63.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Menuitem Menuitem64 = new Menuitem();
			Menuitem64.setMid(64L);
			Menuitem64.setParentis(false);
			//Menuitem64.setChecked(false);
			Menuitem64.setPid(6L);
			Menuitem64.setName("职位变更");
			Menuitem64.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Menuitem Menuitem65 = new Menuitem();
			Menuitem65.setMid(65L);
			Menuitem65.setParentis(false);
			//Menuitem65.setChecked(false);
			Menuitem65.setPid(6L);
			Menuitem65.setName("人事合同");
			Menuitem65.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Menuitem Menuitem66 = new Menuitem();
			Menuitem66.setMid(66L);
			Menuitem66.setParentis(false);
			//Menuitem66.setChecked(false);
			Menuitem66.setPid(6L);
			Menuitem66.setName("薪酬制度");
			Menuitem66.setIcon("css/images/MenuIcon/FUNC20001.gif");
	/*****************************************************************************************/
			/*
			 * 电子邮件
			 */
			Menuitem Menuitem7 = new Menuitem();
			Menuitem7.setMid(7L);
			Menuitem7.setParentis(false);
			//Menuitem7.setChecked(false);
			Menuitem7.setPid(1L);
			Menuitem7.setName("电子邮件");
			Menuitem7.setIcon("css/images/MenuIcon/eml.gif");

	/*******************************************************************/
			/*
			 * 实用工具
			 * 	车票预定
			 * 	GIS查询
			 * 	邮政编码
			 */
			Menuitem Menuitem8 = new Menuitem();
			Menuitem8.setMid(8L);
			Menuitem8.setParentis(true);
			//Menuitem8.setChecked(false);
			Menuitem8.setPid(1L);
			Menuitem8.setName("实用工具");
			Menuitem8.setIcon("css/images/MenuIcon/FUNC20076.gif");
			Menuitem Menuitem81 = new Menuitem();
			Menuitem81.setMid(81L);
			Menuitem81.setParentis(false);
			//Menuitem81.setChecked(false);
			Menuitem81.setPid(8L);
			Menuitem81.setName("车票预定");
			Menuitem81.setIcon("css/images/MenuIcon/FUNC220000.gif");
			Menuitem Menuitem82 = new Menuitem();
			Menuitem82.setMid(82L);
			Menuitem82.setParentis(false);
			//Menuitem82.setChecked(false);
			Menuitem82.setPid(8L);
			Menuitem82.setName("GIS查询");
			Menuitem82.setIcon("css/images/MenuIcon/search.gif");
			Menuitem Menuitem83 = new Menuitem();
			Menuitem83.setMid(83L);
			Menuitem83.setParentis(false);
			//Menuitem83.setChecked(false);
			Menuitem83.setPid(8L);
			Menuitem83.setName("邮政编码");
			Menuitem83.setIcon("css/images/MenuIcon/FUNC249000.gif");
	/**************************************************************************/
			/*
			 * 个人设置
			 * 		个人信息
			 * 		密码修改
			 */
			Menuitem Menuitem9 = new Menuitem();
			Menuitem9.setMid(9L);
			Menuitem9.setParentis(true);
			//Menuitem9.setChecked(false);
			Menuitem9.setPid(1L);
			Menuitem9.setName("个人设置");
			Menuitem9.setIcon("css/images/MenuIcon/FUNC20001.gif");
			Menuitem Menuitem91 = new Menuitem();
			Menuitem91.setMid(91L);
			Menuitem91.setParentis(false);
			//Menuitem91.setChecked(false);
			Menuitem91.setPid(9L);
			Menuitem91.setName("个人信息");
			Menuitem91.setIcon("css/images/MenuIcon/FUNC20001.gif");
			Menuitem Menuitem92 = new Menuitem();
			Menuitem92.setMid(92L);
			Menuitem92.setParentis(false);
			//Menuitem92.setChecked(false);
			Menuitem92.setPid(9L);
			Menuitem92.setName("密码修改");
			Menuitem92.setIcon("css/images/MenuIcon/FUNC241000.gif");
	/***********************************************************************************/
			/*
			 * 系统管理
			 * 	岗位管理
			 * 	部门管理
			 * 	用户管理
			 */
			Menuitem Menuitem10 = new Menuitem();
			Menuitem10.setMid(10L);
			Menuitem10.setParentis(true);
			//Menuitem10.setChecked(false);
			Menuitem10.setPid(1L);
			Menuitem10.setName("系统管理");
			Menuitem10.setIcon("css/images/MenuIcon/system.gif");
			Menuitem Menuitem101 = new Menuitem();
			Menuitem101.setMid(101L);
			Menuitem101.setParentis(false);
			//Menuitem101.setChecked(false);
			Menuitem101.setPid(10L);
			Menuitem101.setName("岗位管理");
			Menuitem101.setIcon("css/images/MenuIcon/FUNC20001.gif");
			Menuitem Menuitem102 = new Menuitem();
			Menuitem102.setMid(102L);
			Menuitem102.setParentis(false);
			//Menuitem102.setChecked(false);
			Menuitem102.setPid(10L);
			Menuitem102.setName("部门管理");
			Menuitem102.setIcon("css/images/MenuIcon/department.gif");
			Menuitem Menuitem103 = new Menuitem();
			Menuitem103.setMid(103L);
			Menuitem103.setParentis(false);
			//Menuitem103.setChecked(false);
			Menuitem103.setPid(10L);
			Menuitem103.setName("用户管理");
			Menuitem103.setIcon("css/images/MenuIcon/FUNC20001.gif");
	/**********************************************************************/

			session.save(Menuitemitem1);
			
			session.save(Menuitem2);
			session.save(Menuitem21);
			session.save(Menuitem22);
			session.save(Menuitem23);
			session.save(Menuitem24);
			session.save(Menuitem25);
			
			
			session.save(Menuitem3);
			session.save(Menuitem31);
			session.save(Menuitem32);
			session.save(Menuitem33);
			session.save(Menuitem34);
			session.save(Menuitem35);
			
			session.save(Menuitem4);
			
			session.save(Menuitem5);
			session.save(Menuitem51);
			session.save(Menuitem52);
			session.save(Menuitem53);
			
			session.save(Menuitem6);
			
			session.save(Menuitem61);
			session.save(Menuitem62);
			session.save(Menuitem63);
			session.save(Menuitem64);
			session.save(Menuitem65);
			session.save(Menuitem66);
			
			session.save(Menuitem7);
			
			session.save(Menuitem8);
			session.save(Menuitem81);
			session.save(Menuitem82);
			session.save(Menuitem83);
			
			session.save(Menuitem9);
			session.save(Menuitem91);
			session.save(Menuitem92);
			
			session.save(Menuitem10);
			session.save(Menuitem101);
			session.save(Menuitem102);
			session.save(Menuitem103);
			t.commit();
			session.close();

			
	}
	
	@Test
	public void test7(){
		 ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
			
			SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
			Session session=sessionFactory.openSession();
			Transaction t=session.beginTransaction();
			
			//权限是什么？权限是菜单，是功能，是资源，是url.
			/*
			 * 个人办公
			 */
			Privilege Privilegeitem1 = new Privilege();
			Privilegeitem1.setId(1L);
			Privilegeitem1.setIcon("css/images/MenuIcon/FUNC20082.gif");
			Privilegeitem1.setName("办公自动化");
			Privilegeitem1.setPid(0L);
			Privilegeitem1.setFlag("1");
			Privilegeitem1.setIsParent(true);
			
			Privilege Privilege2 = new Privilege();
			Privilege2.setId(2L);
			Privilege2.setIcon("css/images/MenuIcon/FUNC20001.gif");
			Privilege2.setName("个人办公");
			//Privilege2.setChecked(false);
			Privilege2.setPid(1L);
			Privilege2.setFlag("1");
			Privilege2.setIsParent(true);
			
			Privilege Privilege21 = new Privilege();
			Privilege21.setId(21L);
			Privilege21.setIcon("css/images/MenuIcon/FUNC20054.gif");
			Privilege21.setName("个人考勤");
			//Privilege21.setChecked(false);
			Privilege21.setPid(2L);
			Privilege21.setFlag("1");
			Privilege21.setIsParent(false);
			
			
			Privilege Privilege22 = new Privilege();
			Privilege22.setId(22L);
			Privilege22.setIcon("css/images/MenuIcon/FUNC23700.gif");
			Privilege22.setName("日程安排");
			//Privilege22.setChecked(false);
			Privilege22.setPid(2L);
			Privilege22.setFlag("1");
			Privilege22.setIsParent(false);
			
			Privilege Privilege23 = new Privilege();
			Privilege23.setId(23L);
			Privilege23.setIcon("css/images/MenuIcon/FUNC20069.gif");
			Privilege23.setName("工作计划");
			//Privilege23.setChecked(false);
			Privilege23.setPid(2L);
			Privilege23.setFlag("1");
			Privilege23.setIsParent(false);
			
			Privilege Privilege24 = new Privilege();
			Privilege24.setId(24L);
			Privilege24.setIcon("css/images/MenuIcon/FUNC20056.gif");
			Privilege24.setName("工作日记");
			//Privilege24.setChecked(false);
			Privilege24.setPid(2L);
			Privilege24.setFlag("1");
			Privilege24.setIsParent(false);
			
			Privilege Privilege25 = new Privilege();
			Privilege25.setId(25L);
			Privilege25.setIcon("css/images/MenuIcon/time_date.gif");
			Privilege25.setName("通讯录");
			//Privilege25.setChecked(false);
			Privilege25.setPid(2L);
			Privilege25.setFlag("1");
			Privilege25.setIsParent(false);
	/*********************************************************************************/	
			/*
			 * 审批流转
			 */
			Privilege Privilege3 = new Privilege();
			Privilege3.setId(3L);
			///Privilege3.setChecked(false);
			Privilege3.setIsParent(true);
			Privilege3.setPid(1L);
			Privilege3.setName("审批流转");
			Privilege3.setFlag("1");
			Privilege3.setIcon("css/images/MenuIcon/FUNC20057.gif");
			
			Privilege Privilege31 = new Privilege();
			Privilege31.setId(31L);
			//Privilege31.setChecked(false);
			Privilege31.setIsParent(false);
			Privilege31.setPid(3L);
			Privilege31.setFlag("1");
			Privilege31.setName("审批流程管理");
			Privilege31.setIcon("css/images/MenuIcon/manager.gif");
			
			Privilege Privilege32 = new Privilege();
			Privilege32.setId(32L);
			//Privilege32.setChecked(false);
			Privilege32.setIsParent(false);
			Privilege32.setPid(3L);
			Privilege32.setFlag("1");
			Privilege32.setName("表单模板管理");
			Privilege32.setIcon("css/images/MenuIcon/formmodel.gif");
			
			Privilege Privilege33 = new Privilege();
			Privilege33.setId(33L);
			Privilege33.setIsParent(false);
			//Privilege33.setChecked(false);
			Privilege33.setPid(3L);
			Privilege33.setFlag("1");
			Privilege33.setName("发起申请");
			Privilege33.setIcon("css/images/MenuIcon/FUNC241000.gif");
			
			Privilege Privilege34 = new Privilege();
			Privilege34.setId(34L);
			Privilege34.setIsParent(false);
			//Privilege34.setChecked(false);
			Privilege34.setPid(3L);
			Privilege34.setFlag("1");
			Privilege34.setName("审批申请");
			Privilege34.setIcon("css/images/MenuIcon/FUNC20029.gif");
			
			Privilege Privilege35 = new Privilege();
			Privilege35.setId(35L);
			Privilege35.setIsParent(false);
			//Privilege35.setChecked(false);
			Privilege35.setPid(3L);
			Privilege35.setName("状态查询");
			Privilege35.setFlag("1");
			Privilege35.setIcon("css/images/MenuIcon/FUNC20029.gif");
	/************************************************************************************/
			/*
			 * 知识管理
			 */
			Privilege Privilege4 = new Privilege();
			Privilege4.setId(4L);
			Privilege4.setIsParent(false);
			//Privilege4.setChecked(false);
			Privilege4.setPid(1L);
			Privilege4.setFlag("1");
			Privilege4.setName("知识管理");
			Privilege4.setIcon("css/images/MenuIcon/FUNC20056.gif");
	/*******************************************************************************/
			/*
			 * 综合行政
			 */
			Privilege Privilege5 = new Privilege();
			Privilege5.setId(5L);
			Privilege5.setIsParent(true);
			//Privilege5.setChecked(false);
			Privilege5.setPid(1L);
			Privilege5.setFlag("1");
			Privilege5.setName("管理行政");
			Privilege5.setIcon("css/images/MenuIcon/manager.gif");
			
			Privilege Privilege51 = new Privilege();
			Privilege51.setId(51L);
			Privilege51.setIsParent(false);
			//Privilege51.setChecked(false);
			Privilege51.setPid(5L);
			Privilege51.setName("考勤管理");
			Privilege51.setFlag("1");
			Privilege51.setIcon("css/images/MenuIcon/FUNC20070.gif");
			
			Privilege Privilege52 = new Privilege();
			Privilege52.setId(52L);
			Privilege52.setIsParent(false);
			//Privilege52.setChecked(false);
			Privilege52.setPid(5L);
			Privilege52.setFlag("1");
			Privilege52.setName("会议管理");
			Privilege52.setIcon("css/images/MenuIcon/FUNC20064.gif");
			
			Privilege Privilege53 = new Privilege();
			Privilege53.setId(53L);
			Privilege53.setIsParent(false);
			//Privilege53.setChecked(false);
			Privilege53.setPid(5L);
			Privilege53.setFlag("1");
			Privilege53.setName("车辆管理");
			Privilege53.setIcon("css/images/MenuIcon/radio_blue.gif");
	/**************************************************************************************/
			/*
			 * 人力资源管理
			 * 	档案管理
			 * 	培训记录
			 * 	奖金记录
			 * 	职位变更
			 * 	人事合同
			 * 	薪酬制度
			 */
			Privilege Privilege6 = new Privilege();
			Privilege6.setId(6L);
			Privilege6.setIsParent(true);
			//Privilege6.setChecked(false);
			Privilege6.setPid(1L);
			Privilege6.setFlag("1");
			Privilege6.setName("人力资源");
			Privilege6.setIcon("css/images/MenuIcon/FUNC20001.gif");
			
			Privilege Privilege61 = new Privilege();
			Privilege61.setId(61L);
			Privilege61.setIsParent(false);
			//Privilege61.setChecked(false);
			Privilege61.setPid(6L);
			Privilege61.setFlag("1");
			Privilege61.setName("档案管理");
			Privilege61.setIcon("css/images/MenuIcon/FUNC20076.gif");
			
			Privilege Privilege62 = new Privilege();
			Privilege62.setId(62L);
			Privilege62.setFlag("1");
			Privilege62.setIsParent(false);
			//Privilege62.setChecked(false);
			Privilege62.setPid(6L);
			Privilege62.setName("培训记录");
			Privilege62.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Privilege Privilege63 = new Privilege();
			Privilege63.setId(63L);
			Privilege63.setFlag("1");
			Privilege63.setIsParent(false);
			//Privilege63.setChecked(false);
			Privilege63.setPid(6L);
			Privilege63.setName("奖赏记录");
			Privilege63.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Privilege Privilege64 = new Privilege();
			Privilege64.setId(64L);
			Privilege64.setIsParent(false);
			Privilege64.setFlag("1");
			//Privilege64.setChecked(false);
			Privilege64.setPid(6L);
			Privilege64.setName("职位变更");
			Privilege64.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Privilege Privilege65 = new Privilege();
			Privilege65.setId(65L);
			Privilege65.setIsParent(false);
			//Privilege65.setChecked(false);
			Privilege65.setPid(6L);
			Privilege65.setFlag("1");
			Privilege65.setName("人事合同");
			Privilege65.setIcon("css/images/MenuIcon/FUNC55000.gif");
			
			Privilege Privilege66 = new Privilege();
			Privilege66.setId(66L);
			Privilege66.setIsParent(false);
			//Privilege66.setChecked(false);
			Privilege66.setPid(6L);
			Privilege66.setFlag("1");
			Privilege66.setName("薪酬制度");
			Privilege66.setIcon("css/images/MenuIcon/FUNC20001.gif");
	/*****************************************************************************************/
			/*
			 * 电子邮件
			 */
			Privilege Privilege7 = new Privilege();
			Privilege7.setId(7L);
			Privilege7.setIsParent(false);
			//Privilege7.setChecked(false);
			Privilege7.setPid(1L);
			Privilege7.setFlag("1");
			Privilege7.setName("电子邮件");
			Privilege7.setIcon("css/images/MenuIcon/eml.gif");

	/*******************************************************************/
			/*
			 * 实用工具
			 * 	车票预定
			 * 	GIS查询
			 * 	邮政编码
			 */
			Privilege Privilege8 = new Privilege();
			Privilege8.setId(8L);
			Privilege8.setIsParent(true);
			//Privilege8.setChecked(false);
			Privilege8.setPid(1L);
			Privilege8.setFlag("1");
			Privilege8.setName("实用工具");
			Privilege8.setIcon("css/images/MenuIcon/FUNC20076.gif");
			
			
			Privilege Privilege81 = new Privilege();
			Privilege81.setId(81L);
			Privilege81.setIsParent(false);
			//Privilege81.setChecked(false);
			Privilege81.setPid(8L);
			Privilege81.setFlag("1");
			Privilege81.setName("车票预定");
			Privilege81.setIcon("css/images/MenuIcon/FUNC220000.gif");
			
			
			Privilege Privilege82 = new Privilege();
			Privilege82.setId(82L);
			Privilege82.setIsParent(false);
			//Privilege82.setChecked(false);
			Privilege82.setPid(8L);
			Privilege82.setFlag("1");
			Privilege82.setName("GIS查询");
			Privilege82.setIcon("css/images/MenuIcon/search.gif");
			
			
			Privilege Privilege83 = new Privilege();
			Privilege83.setId(83L);
			Privilege83.setIsParent(false);
			//Privilege83.setChecked(false);
			Privilege83.setFlag("1");
			Privilege83.setPid(8L);
			Privilege83.setName("邮政编码");
			Privilege83.setIcon("css/images/MenuIcon/FUNC249000.gif");
	/**************************************************************************/
			/*
			 * 个人设置
			 * 		个人信息
			 * 		密码修改
			 */
			Privilege Privilege9 = new Privilege();
			Privilege9.setId(9L);
			Privilege9.setIsParent(true);
			//Privilege9.setChecked(false);
			Privilege9.setPid(1L);
			Privilege9.setFlag("1");
			Privilege9.setName("个人设置");
			Privilege9.setIcon("css/images/MenuIcon/FUNC20001.gif");
			
			
			Privilege Privilege91 = new Privilege();
			Privilege91.setId(91L);
			Privilege91.setIsParent(false);
			//Privilege91.setChecked(false);
			Privilege91.setPid(9L);
			Privilege91.setFlag("1");
			Privilege91.setName("个人信息");
			Privilege91.setIcon("css/images/MenuIcon/FUNC20001.gif");
			
			
			Privilege Privilege92 = new Privilege();
			Privilege92.setId(92L);
			Privilege92.setIsParent(false);
			//Privilege92.setChecked(false);
			Privilege92.setFlag("1");
			Privilege92.setPid(9L);
			Privilege92.setName("密码修改");
			Privilege92.setIcon("css/images/MenuIcon/FUNC241000.gif");
	/***********************************************************************************/
			/*
			 * 系统管理
			 * 	岗位管理
			 * 	部门管理
			 * 	用户管理
			 */
			Privilege Privilege10 = new Privilege();
			Privilege10.setId(10L);
			Privilege10.setIsParent(true);
			//Privilege10.setChecked(false);
			Privilege10.setPid(1L);
			Privilege10.setFlag("1");
			Privilege10.setName("系统管理");
			Privilege10.setIcon("css/images/MenuIcon/system.gif");
			
			
			Privilege Privilege101 = new Privilege();
			Privilege101.setId(101L);
			Privilege101.setIsParent(false);
			//Privilege101.setChecked(false);
			Privilege101.setPid(10L);
			Privilege101.setFlag("1");
			Privilege101.setName("岗位管理");
			Privilege101.setIcon("css/images/MenuIcon/FUNC20001.gif");
			
			
			Privilege Privilege102 = new Privilege();
			Privilege102.setId(102L);
			Privilege102.setIsParent(false);
			//Privilege102.setChecked(false);
			Privilege102.setPid(10L);
			Privilege102.setName("部门管理");
			Privilege102.setFlag("1");
			Privilege102.setIcon("css/images/MenuIcon/department.gif");
			
			
			Privilege Privilege103 = new Privilege();
			Privilege103.setId(103L);
			Privilege103.setIsParent(false);
			//Privilege103.setChecked(false);
			Privilege103.setPid(10L);
			Privilege103.setFlag("1");
			Privilege103.setName("用户管理");
			Privilege103.setIcon("css/images/MenuIcon/FUNC20001.gif");
	/**********************************************************************/
			/*
			 * {
			 * 	1,1
			 * 	2,5
			 * 	3,5
			 * 	4,1
			 * 	5,3
			 * 	6,6
			 * 	7,1
			 * 	8,3
			 * 	9,2
			 * 	10,3
			 * }
			 */
			
			session.save(Privilegeitem1);
			
			session.save(Privilege2);
			session.save(Privilege21);
			session.save(Privilege22);
			session.save(Privilege23);
			session.save(Privilege24);
			session.save(Privilege25);
			
			
			session.save(Privilege3);
			session.save(Privilege31);
			session.save(Privilege32);
			session.save(Privilege33);
			session.save(Privilege34);
			session.save(Privilege35);
			
			session.save(Privilege4);
			
			session.save(Privilege5);
			session.save(Privilege51);
			session.save(Privilege52);
			session.save(Privilege53);
			
			session.save(Privilege6);
			
			session.save(Privilege61);
			session.save(Privilege62);
			session.save(Privilege63);
			session.save(Privilege64);
			session.save(Privilege65);
			session.save(Privilege66);
			
			session.save(Privilege7);
			
			session.save(Privilege8);
			session.save(Privilege81);
			session.save(Privilege82);
			session.save(Privilege83);
			
			session.save(Privilege9);
			session.save(Privilege91);
			session.save(Privilege92);
			
			session.save(Privilege10);
			session.save(Privilege101);
			session.save(Privilege102);
			session.save(Privilege103);
			t.commit();
			
	
	}
	
	@Test
	public void test8(){//给你一个权限，你就有这个权限做什么事情，权限的设计是什么？？
		//权限是树？？？？
		 ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
			
			SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
			Session session=sessionFactory.openSession();
			Transaction t=session.beginTransaction();
	
			Privilege Privilege1 = new Privilege();
			Privilege1.setId(201L);
			Privilege1.setFlag("2");
			Privilege1.setPid(0L);
			Privilege1.setIcon("css/images/MenuIcon/system.gif");
			Privilege1.setName("用户增加");
			
			
			Privilege Privilege2 = new Privilege();
			Privilege2.setId(202L);
			Privilege2.setFlag("2");
			Privilege2.setPid(0L);
			Privilege2.setIcon("css/images/MenuIcon/system.gif");
			Privilege2.setName("用户删除");
			
			Privilege Privilege3 = new Privilege();
			Privilege3.setId(203L);
			Privilege3.setFlag("2");
			Privilege3.setPid(0L);
			Privilege3.setIcon("css/images/MenuIcon/system.gif");
			Privilege3.setName("用户修改");
			
			Privilege Privilege32 = new Privilege();
			Privilege32.setId(210L);
			Privilege32.setFlag("2");
			Privilege32.setPid(0L);
			Privilege32.setIcon("css/images/MenuIcon/system.gif");
			Privilege32.setName("用户查询");
			
			Privilege Privilege4 = new Privilege();
			Privilege4.setId(204L);
			Privilege4.setFlag("2");
			Privilege4.setPid(0L);
			Privilege4.setIcon("css/images/MenuIcon/system.gif");
			Privilege4.setName("部门增加");
			
			
			Privilege Privilege5 = new Privilege();
			Privilege5.setId(205L);
			Privilege5.setFlag("2");
			Privilege5.setPid(0L);
			Privilege5.setIcon("css/images/MenuIcon/system.gif");
			Privilege5.setName("部门删除");
			
			Privilege Privilege6 = new Privilege();
			Privilege6.setId(206L);
			Privilege6.setFlag("2");
			Privilege6.setPid(0L);
			Privilege6.setIcon("css/images/MenuIcon/system.gif");
			Privilege6.setName("部门修改");
			
			Privilege Privilege66 = new Privilege();
			Privilege66.setId(211L);
			Privilege66.setFlag("2");
			Privilege66.setPid(0L);
			Privilege66.setIcon("css/images/MenuIcon/system.gif");
			Privilege66.setName("部门查询");
			
			Privilege Privilege7 = new Privilege();
			Privilege7.setId(207L);
			Privilege7.setFlag("2");
			Privilege7.setPid(0L);
			Privilege7.setIcon("css/images/MenuIcon/system.gif");
			Privilege7.setName("岗位增加");
			
			
			Privilege Privilege8 = new Privilege();
			Privilege8.setId(208L);
			Privilege8.setFlag("2");
			Privilege8.setPid(0L);
			Privilege8.setIcon("css/images/MenuIcon/system.gif");
			Privilege8.setName("岗位删除");
			
			Privilege Privilege9 = new Privilege();
			Privilege9.setId(209L);
			Privilege9.setFlag("2");
			Privilege9.setPid(0L);
			Privilege9.setIcon("css/images/MenuIcon/system.gif");
			Privilege9.setName("岗位修改");
			
			Privilege Privilege99 = new Privilege();
			Privilege99.setId(212L);
			Privilege99.setFlag("2");
			Privilege99.setPid(0L);
			Privilege99.setIcon("css/images/MenuIcon/system.gif");
			Privilege99.setName("岗位查询");
			
			session.save(Privilege1);
			session.save(Privilege2);
			session.save(Privilege3);
			session.save(Privilege32);
			session.save(Privilege66);
			session.save(Privilege99);
			session.save(Privilege4);
			session.save(Privilege5);
			
			session.save(Privilege6);
			session.save(Privilege7);
			session.save(Privilege8);
			session.save(Privilege9);
			t.commit();
			session.close();
	
	}
	
	@Test
	public void test10(){
		String param="1,2,3,4,5,6,7,8,9,10,11,22";
		String [] index=param.split(",");
		Integer[] jids=new Integer[index.length];
		for(int i=0;i<jids.length;i++){
			jids[i]=Integer.valueOf(index[i]);
		}
		
		for(int i=0;i<jids.length;i++){
			System.out.println(jids[i]);
		}
	}
	
	
	
	@Test	
	public void test11(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		JobService jobService=(JobService) ac.getBean("jobService");
		Integer i=1;	
		jobService.getUserJobs(i);
	}
	
	@Test	
	
	public void test12(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		PrivilegeDao privilege=(PrivilegeDao) ac.getBean("privilegeDao");
		Collection<Privilege> all=privilege.getPrivilegeByJobs(2);
		for(Privilege p:all){
			System.out.println(p.getChecked());
		}
	}
	@Test
	public void test13(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		UserDao userDao=(UserDao) ac.getBean("userDao");
		userDao.getUserByNameAndPwd("张三", "12222");
	}
	
	
	@Test
	public void test14(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		PrivilegeDao pd=(PrivilegeDao) ac.getBean("privilegeDao");
		Collection<Privilege> alls=pd.getMenuByUid(2,"admin");
		
		for(Privilege p:alls){
		System.out.println(p.getName());
		}
	}
	
	@Test
	public void getDeploy(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		ProcessEngine pe=(ProcessEngine) ac.getBean("processEngine");
		System.out.println(pe);
	}

}
