package com.example.demo;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CustomerDetailsService implements UserDetailsService {
 
	// StreamBridge provides a send method that takes a name binding and message object as parameters
	// In config, set property spring.cloud.stream.source with the name 
	// that is used as a prefix of the generated binding name
	@Autowired
	private final StreamBridge stream;
	   
//    @Autowired
//    private final RabbitTemplate template;
    
    @Autowired
    private CustomerDataRepo customerRepo;
    
    private EventType type;
    
 // Stores the topic/binding name as the supplier name
  //  private static final String PRODUCER_BINDING_NAME = "CustomerServiceSupplier-out-0";
    
    public CustomerDetailsService(StreamBridge stream) {
    	   this.stream = stream;

    }
    
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
    	
    	 // Encrypts customer password
//    	 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//         String encodedPassword = passwordEncoder.encode(c.getPassword());
//         // Sets customer password as encrypted password
//         c.setPassword(encodedPassword); 
         // Saves customer to H2 database
         customerRepo.save(c);
          
    	 //check if user is saved or not null
        //create your message event
        // Mono vs Flux?
     //  DataEvent<String, Customer> event = new DataEvent<String, Customer>(type.CREATE, "Customer saved", c);
       // RestServiceApplication.template.convertAndSend("", Config.QUEUE, event);
       // RestServiceApplication.template.convertAndSend(Config.QUEUE,"", c);
      //  template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, event);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        template.convertAndSend(RestServiceApplication.topicExchangeName, "foo.bar.baz", c);
        //stream.send(PRODUCER_BINDING_NAME, event); // alternate method, returns a boolean

    }
    
    public void deleteCustomer(Customer c) {
    	DataEvent<String, Customer> event = new DataEvent<String, Customer>(type.DELETE, "Customer removed", c);
    //    boolean sent = stream.send(PRODUCER_BINDING_NAME, event);
    	customerRepo.delete(c);
    }
    
    public List<Customer> findAll() {
    	return customerRepo.findAll();
    }
  // Check the repo above for proper implementation.
}
