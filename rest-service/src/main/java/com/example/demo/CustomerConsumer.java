package com.example.demo;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.model.Customer;
import com.example.model.Order;
import com.example.service.CustomerService;

//Sink

@Component
public class CustomerConsumer {
	
    private CustomerService detailsService;

    @Autowired
    public CustomerConsumer(CustomerService service) {
    	this.detailsService = service;
    }
    
    @Bean
    public Consumer<Customer> receive() {
        return customer -> {
        	System.out.println("\n\nCustomer received: " + customer.getLastName());
        	Customer cust = new Customer(customer.getFirstName(),customer.getLastName(), customer.getEmail(), customer.getPassword());
            detailsService.saveCustomer(cust);
        };
    }
    
    @Bean
    public Consumer<Order> receiveOrder() {
        return order -> {
        	System.out.println("\n\nOrder received: " + order.getId());            
        	Order jpaOrder = new Order(order.getOrderNumber(), order.getOrderItems());
        	detailsService.saveOrder(jpaOrder);
        };
    }
}