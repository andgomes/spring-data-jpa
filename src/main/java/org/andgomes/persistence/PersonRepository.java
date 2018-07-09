package org.andgomes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.andgomes.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}