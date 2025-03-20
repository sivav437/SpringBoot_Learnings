package com.spring.springboot_cache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springboot_cache.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

}
