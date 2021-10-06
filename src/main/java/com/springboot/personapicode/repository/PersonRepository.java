package com.springboot.personapicode.repository;

import com.springboot.personapicode.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
