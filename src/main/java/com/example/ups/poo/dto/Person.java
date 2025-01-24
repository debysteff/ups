package com.example.ups.poo.dto;

public class Person {
    private String name;
    private String lastname;
    private int age;
    private String id;

    public Person() {}

    public Person(String name, String lastname, int age, String id) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
