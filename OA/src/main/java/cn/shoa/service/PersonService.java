package cn.shoa.service;

import cn.shoa.domain.Person;

public interface PersonService {
	public void savePerson(Person person);
	public Person getPersonById(int id);
}
