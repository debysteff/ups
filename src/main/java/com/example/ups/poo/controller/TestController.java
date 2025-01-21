package com.example.ups.poo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Hello World, this is my first SpringBoot Project...!";
    }

    @GetMapping("/greet/{name}/{lastname}")
    public String greet(@PathVariable String name, @PathVariable String lastname) {
        String message = " Hello " + name + " " +
                lastname + ", this is my first SpringBoot Project...!";
        return message;

    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name, @RequestParam String lastname) {
        String message = " Hello " + name + " " +
                lastname + ", this is my first SpringBoot Project xxx!";
        return message;
    }
}
