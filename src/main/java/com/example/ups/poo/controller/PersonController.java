package com.example.ups.poo.controller;

import com.example.ups.poo.Service.PersonService;
import com.example.ups.poo.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/get-person")
    public ResponseEntity getPersonById(@RequestParam String id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/update-person")
    public ResponseEntity updatePerson(@RequestBody Person person) {
        return personService.updatePerson(person);
    }
}
