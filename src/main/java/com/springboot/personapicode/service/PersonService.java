package com.springboot.personapicode.service;

import com.springboot.personapicode.dto.request.PersonDTO;
import com.springboot.personapicode.dto.response.MessageResponseDTO;
import com.springboot.personapicode.entity.Person;
import com.springboot.personapicode.mapper.PersonMapper;
import com.springboot.personapicode.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private  final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savePerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("created person with ID: " + savePerson.getId())
                .build();
    }
}
