package com.springboot.personapicode.service;

import com.springboot.personapicode.dto.response.MessageResponseDTO;
import com.springboot.personapicode.entity.Person;
import com.springboot.personapicode.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person) {
        Person savePerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("created person with ID: " + savePerson.getId())
                .build();
    }
}
