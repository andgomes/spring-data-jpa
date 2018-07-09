package org.andgomes;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import org.andgomes.service.IPersonService;
import org.andgomes.model.Person;

@SpringBootApplication
public class Application {

	private static final Logger logger =
			LoggerFactory.getLogger(Application.class); 

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLine(IPersonService personService) {

		return arg -> {

			logger.info("===============================");

			List<Person> persons = personService.listAll();

			for (Person person : persons) {
				logger.info("Id: " + person.getId());
				logger.info("Name: " + person.getName());
			}

			logger.info("===============================");

		};

	}

}