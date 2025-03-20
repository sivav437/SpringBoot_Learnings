package com.spring.springboot_cache.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springboot_cache.model.Person;
import com.spring.springboot_cache.service.CacheInspectionService;
import com.spring.springboot_cache.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@Autowired // wired the cacheInspection service
	private CacheInspectionService cacheService;
	
	private static final Logger logger=LogManager.getLogger();
	
	
	@GetMapping("/cacheData") // wrote api for checking cacheData
	public ResponseEntity<String> getCacheData(@RequestHeader String cacheName) throws Exception {
		String cacheRes=cacheService.printCacheContents(cacheName);
		return new ResponseEntity<>(cacheRes,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Person>> getPersons() throws Exception{
		List<Person> persons=service.getPersons();
		return new ResponseEntity<>(persons, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable int id) throws Exception{
		Person person=service.getPersonById(id);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Person> addPerson(@RequestBody Person person) throws Exception{
		Person addedperson=service.addPerson(person);
		return new ResponseEntity<>(addedperson,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person,@PathVariable int id) throws Exception{
		Person updatedperson=service.updatePerson(person,id);
		return new ResponseEntity<>(updatedperson,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePerson(@PathVariable int id) throws Exception{
		service.deletePerson(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

}

