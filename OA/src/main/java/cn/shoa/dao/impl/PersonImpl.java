package cn.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.shoa.dao.PersonDao;
import cn.shoa.dao.base.impl.BaseDaoImpl;
import cn.shoa.domain.Person;
//表示spring自动注入，id=personDao;
@Repository("personDao")
public class PersonImpl extends BaseDaoImpl<Person> implements PersonDao {

	//实现的方法仅仅是PersonDao里面的方法！
	//利用继承类的HibernateTemplate进行方法的实现即可

}
