package com.example.demo;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();
    
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @GetMapping
    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> findUserById(@PathVariable(value = "id") long id) {
    	 Optional<Customer> customer = customerRepository.findById(id);

    	    if(customer.isPresent()) {
    	        return ResponseEntity.ok().body(customer.get());
    	    } else {
    	        return ResponseEntity.notFound().build();
    	    }
    }
    
    @PostMapping
    public Customer saveCustomer(@Validated @RequestBody Customer customer) {
    	return customerRepository.save(customer);
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
