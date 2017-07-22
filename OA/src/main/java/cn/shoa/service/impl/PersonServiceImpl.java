package cn.shoa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shoa.dao.PersonDao;
import cn.shoa.domain.Person;
import cn.shoa.service.PersonService;
@Service("personService")
public class PersonServiceImpl implements PersonService {
	//这句话表示将id=personDao的bean为这个属性进行实例化！
	@Resource(name="personDao")
	private PersonDao personDao;

	@Override
	public void savePerson(Person person) {
		// TODO Auto-generated method stub
		personDao.saveEntry(person);
	}

	@Override
	public Person getPersonById(int id) {
		// TODO Auto-generated method stub
		return personDao.getEntryById(id);
	}

}
