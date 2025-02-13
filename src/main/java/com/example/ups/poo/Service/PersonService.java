package com.example.ups.poo.Service;

import com.example.ups.poo.dto.PersonDTO;
import com.example.ups.poo.entity.Person;
import com.example.ups.poo.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private PersonDTO mapPersonToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName() + " " + person.getLastname());
        personDTO.setAge(person.getAge());
        personDTO.setId(person.getPersonId());
        return personDTO;
    }

    private Person mapPersonDTOToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setPersonId(personDTO.getId());
        String name = personDTO.getName();
        String[] fullNameArray = name.split(" ");
        person.setName(fullNameArray[0]);
        person.setLastname(fullNameArray[1]);
        person.setAge(personDTO.getAge());
        return person;
    }

    private List<PersonDTO> fetchAllPeopleRecords() {
        Iterable<Person> personIterable = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();

        for (Person per : personIterable) {
            PersonDTO personDTO = mapPersonToPersonDTO(per);
            personDTOList.add(personDTO);
        }
        return personDTOList;
    }

    public ResponseEntity getAllPeople() {
        List<PersonDTO> personDTOList = fetchAllPeopleRecords();
        if (personDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person list is empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    public ResponseEntity getPersonById(String id) {
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if (personOptional.isPresent()) {
            PersonDTO personDTO = mapPersonToPersonDTO(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        } else {
            String message = "Person with id: " + id + "not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + id + " not found ");
        }
    }

    public ResponseEntity createPerson(PersonDTO personDTO) {
        if (personDTO.getId() == null || personDTO.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is a mandatory field");
        }
        if (personDTO.getName() == null || personDTO.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is a mandatory field");
        }
        if (!personDTO.getName().contains(" ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide your full name");
        }
        if (personDTO.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Age is a mandatory field");
        }

        Optional<Person> existingPerson = personRepository.findByPersonId(personDTO.getId());
        if (existingPerson.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person with this Id already exits");
        }

        Person person = mapPersonDTOToPerson(personDTO);

        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.OK).body("Person successfully created and saved");
    }

    public ResponseEntity updatePerson(PersonDTO personDTO) {
        Optional<Person> personOptional = personRepository.findByPersonId(personDTO.getId());
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            if (personDTO.getName() != null && !personDTO.getName().isEmpty()) {
                String fullName = personDTO.getName();
                if (fullName.contains(" ")) {
                    String[] fullNameArray = fullName.split(" ");
                    person.setName(fullNameArray[0]);
                    person.setLastname(fullNameArray[1]);
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The name must contain both a first and last name.");
                }
            }
            if (personDTO.getAge() > 0) {
                person.setAge(personDTO.getAge());
            }
            personRepository.save(person);
            return ResponseEntity.status(HttpStatus.OK).body("Person with id: " + personDTO.getId() + " was successfully updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + personDTO.getId() + " not found");
    }

    public ResponseEntity deletePersonById(String id) {
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if (personOptional.isPresent()) {
            personRepository.delete(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("Person with id: " + id + " was successfully deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + id + " not found");
    }
}



