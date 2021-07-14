package com.example.demo;

import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.dto.OrderDto;
import com.example.model.Customer;
import com.example.model.Order;
import com.example.model.OrderItems;
import com.example.service.CustomerDetailsService;
import com.example.util.DataEvent;

//Sink
// Subscribe to the "total" channel and log the results

@Component
public class CustomerConsumer {

   // private final Logger LOG = LoggerFactory.getLogger(CustomerConsumer.class);
	
    private CustomerDetailsService detailsService;

    @Autowired
    public CustomerConsumer(CustomerDetailsService service) {
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
        //	OrderItems item = new OrderItems(order.getOrderItems().g);
            
        	Order jpaOrder = new Order(order.getOrderNumber(), order.getOrderItems());
        	detailsService.saveOrder(jpaOrder);
        };
    }
}
    
//  @Bean
//  public Consumer<DataEvent<String, Customer>> receive() {
//      return event -> {
//      	System.out.println("\n\nCustomer received: " + event.getData().getFirstName());
//          detailsService.saveCustomer(event.getData());
//      };
//  }
    //consume customer sent from customer service and return customer to queue if verify is successful
//    @Bean
//    public Function<String, Customer> verify() {
//    	    return username -> {
//    	    	System.out.println("testing");
//	        	Customer customerInDatabase = detailsService.findByEmail(username);
//	            if (customerInDatabase == null) {
//	                throw new UsernameNotFoundException("User not found");
//	            }
//	            return customerInDatabase;
//        };
//    }

    
    
//    @Bean
//    public Function<Customer, List<Customer>> () {
//        return customer -> {
//        	System.out.println("\n\nCustomer received: " + customer.getFirstName());
//            detailsService.saveCustomer(customer);
//        };
//    }
   // private final CustomerDetailsService customerService;


    
//    public void CustomerServiceConsumer(Customer customer) {
//    	System.out.println("\n\nCustomer received: " + customer.getFirstName());
//    	detailsService.saveCustomer(customer);
//    }

//    @Bean
//    //@RabbitListener(queues = Config.QUEUE)
//    public Consumer<Customer> CustomerServiceConsumer() {
//          return (customer) -> {
//              LOG.info("Consuming creation of customer {}", customer.getFirstName());
//          };
//    }
//    @Bean
//  //  @RabbitListener(queues = Config.QUEUE)
//    public Consumer<DataEvent<String, Customer>> CustomerServiceConsumer() {
//        return event -> {
//            LOG.info("Consuming message event created at {}", event.getEventCreatedAt());
//            switch (event.getEventType()) {
//                case CREATE:
//                	System.out.println("\nthis is working\n");
//                	LOG.info("Creating customer of the following {}", event.getData().getId());
//                	customerService.saveCustomer(event.getData());
//                case DELETE:
//                	LOG.info("Deleting customer with the following {}", event.getData().getId());
//                	break;
//				case READ:
//					LOG.info("Customer first name: {}\nCustomer last name: {}", event.getData().getFirstName(), event.getData().getLastName());
//					break;
//				case UPDATE:
//					break;
//				default:
//					break;
//            }
//        };
//    }
//}
    
    
    
//            switch (event.getEventType()) {
//
//                case CREATE:
////                    UserPayload userPayload = event.getData();
////                    LOG.info("Creating customer of the following {}", Customer.class);
////                    User user = userMapper.userPayloadToUserService(userPayload);
//                    customerService.saveCustomer(customer)
////                            .onErrorMap(
////                                    DuplicateKeyException.class,
////                                    ex -> new BadRequestException("Duplicate key, username " + user.getUsername() +
////                                            " or email address " + user.getEmail() + " had already been used.")
////                            )
//                            .subscribe(u -> LOG.info("Customer Created {}", u));
//                    break;
//
//                case DELETE:
//                    userPayload = event.getData();
//                    user = userMapper.userPayloadToUserService(userPayload);
//                    String userId = event.getKey() != null ? event.getKey() : user.getId();
//                    LOG.info("Deleting user with the following {}", userId);
//                    userService.deleteUserById(userId)
//                            .subscribe(x -> LOG.info("User with id {} deleted successfully", userId));
//                    break;
//            }
//        };
