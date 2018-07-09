package org.andgomes.service;

import java.util.List;

import org.andgomes.model.Person;

public interface IPersonService {

	void save(Person person);
	Person findById(Long id);
	void update(Person person);
	void delete(Person person);
	List<Person> listAll();

}