package com.example.controller;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomerManager;
import com.example.model.Customer;
import com.example.repository.CustomerDataRepo;
import com.example.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@Component
public class AppRestController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    
    private final CustomerService service;
    
   	@Autowired
   	public AppRestController(CustomerService service) {
   		this.service = service;
   	}
    
    @GetMapping
    public Iterable<Customer> findAllCustomers() {
        return service.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findUserById(@PathVariable(value = "id") long id) {
    	 Optional<Customer> customer = service.findById(id);

    	    if(customer.isPresent()) {
    	        return ResponseEntity.ok().body(customer.get());
    	    } else {
    	        return ResponseEntity.notFound().build();
    	    }
    }
    
    @PostMapping
    public void saveCustomer(@Validated @RequestBody Customer customer) {
    	service.save(customer);
    }
    
    // ensures HTTP GET requests to /CustomerManager are mapped to the manager() method
    @GetMapping("/manager")
    public CustomerManager manager(@RequestParam(value = "name", defaultValue = "World") String name) {
    	// returns a new CustomerManager object with id and content attributes based on the next value from 
    	// the counter and formats the given name by using the CustomerManager template
    	return new CustomerManager(counter.incrementAndGet(), String.format(template, name));
    }
    
//    @Repository
//    public interface GroupRepository extends CrudRepository<GroupInfo, String> {
//
//      @EntityGraph(value = "GroupInfo.detail", type = EntityGraphType.LOAD)
//      GroupInfo getByGroupName(String name);
//
//    }
}
