package com.example.ups.poo.repository;

import com.example.ups.poo.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByPersonId(String personId);
}
