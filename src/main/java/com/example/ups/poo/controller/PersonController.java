package com.example.ups.poo.controller;

import com.example.ups.poo.dto.PersonDTO;
import com.example.ups.poo.Service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create-person")
    public ResponseEntity createPerson(@RequestBody PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @PutMapping("/update-person")
    public ResponseEntity updatePerson(@RequestBody PersonDTO personDTO) {
        return personService.updatePerson(personDTO);
    }

    @DeleteMapping("/delete-person")
    public ResponseEntity deletePerson(@RequestParam String id) {
        return personService.deletePersonById(id);
    }
}

