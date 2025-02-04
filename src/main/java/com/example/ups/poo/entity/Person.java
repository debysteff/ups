package com.example.ups.poo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String personId;
        private String name;
        private String lastname;
        private Integer age;
}
