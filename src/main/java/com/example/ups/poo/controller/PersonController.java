package com.example.ups.poo.controller;

import com.example.ups.poo.Service.PersonService;
import com.example.ups.poo.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPeople());
    }

    @GetMapping("/get-person")
    public ResponseEntity getPersonById(@RequestParam String id){
        return personService.getPersonById(id);
    }
}
