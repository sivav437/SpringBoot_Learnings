package com.spring.springboot_cache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.spring.springboot_cache.model.Person;
import com.spring.springboot_cache.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository repo;
	
	@Cacheable(value="persons",key="#root.methodName")
	public List<Person> getPersons() throws Exception{
		List<Person> persons=repo.findAll();
		if(persons.isEmpty()) {
			throw new Exception("failed to get all Persons data due to it is empty in database ");
		}
		return repo.findAll();
	}
	
	@Cacheable(value="person",key="#id") //condition="#id > 10" or condition="#person.id"
	public Person getPersonById(int id) throws Exception {
		Optional<Person> person=repo.findById(id);
		if(person.isPresent()) {
			return person.get();
		}
		throw new Exception("failed to get Person data , due to data is not found in database with id: "+id);
	}
	
	@Cacheable(value="person",key="#person.id")
	public Person addPerson(Person person) throws Exception {
		if(person !=null) {
			return repo.save(person);
		}
		throw new Exception("failed to add Person data , due to person data is null");
	}
	
	@CachePut(value="person",key="#id")
	public Person updatePerson(Person person,int id) throws Exception {
		Optional<Person> op_person=repo.findById(1);
		if(op_person.isPresent()) {
			return repo.save(person);
		}
		throw new Exception("failed to update Person data , due to not found in database with id: "+id);
	}
	
	@CacheEvict(value="person",key="#id")
	public void deletePerson(int id) throws Exception {
		Optional<Person> op_person=repo.findById(id);
		if(op_person.isPresent()) {
			repo.delete(op_person.get());
		}
		
	   throw new Exception("failed to delete Person data, due to not found in database with id: "+id);

		
	}

}
