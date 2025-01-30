package com.example.ups.poo.Service;

import com.example.ups.poo.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    List<Person> personList = new ArrayList<>();

    public ResponseEntity getAllPeople() {
        if (personList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person list is empty");
        }

        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id) {
        for (Person person : personList) {
            if (id.equalsIgnoreCase(person.getId())) {
                return ResponseEntity.status(HttpStatus.OK).body(person);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id: " + id + " not found ");
    }

    public ResponseEntity createPerson(Person person) {
        if (person.getId() == null || person.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is a mandatory field");
        }
        if (person.getName() == null || person.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is a mandatory field");
        }
        if (person.getLastname() == null || person.getLastname().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lastname is a mandatory field");
        }
        if (person.getAge() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Age is a mandatory field");
        }
        for (Person existingPerson : personList) {
            if (existingPerson.getId().equalsIgnoreCase(person.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Person with id alredy exists");
            }
        }
        personList.add(person);
        return ResponseEntity.status(HttpStatus.OK).body("Person successfully registered");
    }
}



