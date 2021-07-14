package com.example.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.CustomerDetails;
import com.example.model.Customer;
import com.example.model.Order;
import com.example.repository.CustomerDataRepo;
import com.example.repository.OrderRepository;
import com.example.util.DataEvent;
import com.example.util.EventType;


@Service
public class CustomerDetailsService implements UserDetailsService {
 
	// StreamBridge provides a send method that takes a name binding and message object as parameters
	// In config, set property spring.cloud.stream.source with the name 
	// that is used as a prefix of the generated binding name
//	@Autowired
//	private final StreamBridge stream;
	
	@Autowired
    private CustomerDataRepo customerRepo;
    
	@Autowired
	private OrderRepository orderRepo;
	
    private EventType type;
    
 // Stores the topic/binding name as the supplier name
  //  private static final String PRODUCER_BINDING_NAME = "CustomerServiceSupplier-out-0";
    
    
    @Override
    public CustomerDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByEmail(username);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomerDetails(customer);
    }
    
    /**
     * Saves customers to H2 database and encrypts customer password
     * @param c Customer
     * @return "register_success" on success
     * @throws InterruptedException 
     */
    public void saveCustomer(Customer c) {
         customerRepo.save(c);
    }
    
    public void saveOrder(Order o) {
    	orderRepo.save(o);
    }
    
    public void deleteCustomer(Customer c) {
    	DataEvent<String, Customer> event = new DataEvent<String, Customer>(type.DELETE, "Customer removed", c);
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
  // Check the repo above for proper implementation.
}
