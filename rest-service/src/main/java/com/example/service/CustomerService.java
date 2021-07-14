package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.RestServiceApplication;
import com.example.model.Customer;
import com.example.repository.CustomerDataRepo;
import com.example.util.DataEvent;
import com.example.util.EventType;

import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	
    private final RabbitTemplate template;
    
    private final CustomerDetailsService customerData;

    private static final String SUPPLIER_BINDING_NAME = "save-out-0";

    private final StreamBridge stream;

    
    @Autowired
	public CustomerService(RabbitTemplate template, CustomerDetailsService customerData, StreamBridge stream) {
		super();
		this.template = template;
		this.stream = stream;
		this.customerData = customerData;
	}
    
 // Publishes customer to the "ints" channel (as defined
    // in the application.yml file) every second.
        
//    @Bean
//    public Supplier<DataEvent<String, Customer>> save() {
//        return () -> {
//            return null;
//        };
//    }
    public void save(Customer customer) {
    	 DataEvent<String, Customer> event = new DataEvent<String, Customer>(EventType.CREATE, "Customer Saved", customer);
    	  boolean sent = stream.send(SUPPLIER_BINDING_NAME, event);
    }
//        public Mono<User> saveUser(User user) {
//            return userRepository.save(user);
//        }

    
//	public String save(Customer c) {
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(c.getPassword());
//        // Sets customer password as encrypted password
//        c.setPassword(encodedPassword); 
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        template.convertAndSend(RestServiceApplication.topicExchangeName, "foo.bar.baz", c);
//        return "register_success";
////        return customerData.saveCustomer(c);
//	}
	
	// is there a best practice for void methods vs, for exmaple, returning "users"?
	public List<Customer> findAll() {
		return customerData.findAll();	
	}

	public Optional<Customer> findById(long id) {
		return customerData.findById(id);
	}
	
}
