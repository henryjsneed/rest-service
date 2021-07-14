package com.example.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.CustomerDetails;
import com.example.model.Customer;
import com.example.model.Order;
import com.example.repository.CustomerDataRepo;
import com.example.repository.OrderRepository;


@Service
public class CustomerService implements UserDetailsService {
	
	@Autowired
    private CustomerDataRepo customerRepo;
    
	@Autowired
	private OrderRepository orderRepo;
	    
    @Override
    public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerDetails(customer);
    }
    
    public void saveCustomer(Customer c) {
         customerRepo.save(c);
    }
    
    public void saveOrder(Order o) {
    	orderRepo.save(o);
    }
    
    public void deleteCustomer(Customer c) {
    //	DataEvent<String, Customer> event = new DataEvent<String, Customer>(type.DELETE, "Customer removed", c);
    //    boolean sent = stream.send(PRODUCER_BINDING_NAME, event);
    	customerRepo.delete(c);
    }
    
    public List<Customer> findAll() {
    	return customerRepo.findAll();
    }
    
    public Optional<Customer> findById(long id) {
    	return customerRepo.findById(id);
    }
    
    public Customer findByEmail(String username) {
    	return customerRepo.findByEmail(username);
    }
}
