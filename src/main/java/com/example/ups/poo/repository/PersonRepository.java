package com.example.ups.poo.repository;

import com.example.ups.poo.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
