package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    
    // ensures HTTP GET requests to /CustomerManager are mapped to the manager() method
    @GetMapping("/manager")
    public CustomerManager manager(@RequestParam(value = "name", defaultValue = "World") String name) {
    	// returns a new CustomerManager object with id and content attributes based on the next value from 
    	// the counter and formats the given name by using the CustomerManager template
    	return new CustomerManager(counter.incrementAndGet(), String.format(template, name));
    }
}
