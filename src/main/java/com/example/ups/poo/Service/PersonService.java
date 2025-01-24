package com.example.ups.poo.Service;

import com.example.ups.poo.dto.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<>();
        Person p1 = new Person("Deby", "Benavides", 20, "09658472325");
        Person p2 = new Person("Made", "Ortiz", 19, "0231564789");
        personList.add(p1);
        personList.add(p2);
        return personList;
    }
}

