package com.springboot.personapicode.service;

import com.springboot.personapicode.dto.request.PersonDTO;
import com.springboot.personapicode.dto.response.MessageResponseDTO;
import com.springboot.personapicode.entity.Person;
import com.springboot.personapicode.exception.PersonNotFoundException;
import com.springboot.personapicode.mapper.PersonMapper;
import com.springboot.personapicode.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {
       List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return   personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

    }
}
