package org.andgomes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.andgomes.model.Person;
import org.andgomes.persistence.PersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private PersonRepository repo;

	public void save(Person person) {
		repo.save(person);
	}

	public Person findById(Long id) {

		Optional<Person> person = repo.findById(id);
	
		if (person.isPresent()) {
			return person.get();
		}

		return null;

	}

	public void update(Person person) {
		save(person);
	}

	public void delete(Person person) {
		repo.delete(person);
	}

	public List<Person> listAll() {
		return repo.findAll();
	}

}