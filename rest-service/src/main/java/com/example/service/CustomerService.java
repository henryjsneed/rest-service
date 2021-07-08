package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.RestServiceApplication;
import com.example.model.Customer;

@Service
public class CustomerService {
	
    private final RabbitTemplate template;
    
    private final CustomerDetailsService customerData;

    @Autowired
	public CustomerService(RabbitTemplate template, CustomerDetailsService customerData) {
		super();
		this.template = template;
		this.customerData = customerData;
	}
    
	public String save(Customer c) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(c.getPassword());
        // Sets customer password as encrypted password
        c.setPassword(encodedPassword); 
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.convertAndSend(RestServiceApplication.topicExchangeName, "foo.bar.baz", c);
        return "register_success";
//        return customerData.saveCustomer(c);
	}
	
	// is there a best practice for void methods vs, for exmaple, returning "users"?
	public List<Customer> findAll() {
		return customerData.findAll();	
	}

	public Optional<Customer> findById(long id) {
		return customerData.findById(id);
	}
	
}
