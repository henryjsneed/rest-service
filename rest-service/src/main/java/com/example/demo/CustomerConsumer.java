package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Customer;
import com.example.service.CustomerDetailsService;

@Component
public class CustomerConsumer {

   // private final Logger LOG = LoggerFactory.getLogger(CustomerConsumer.class);
	
    private CustomerDetailsService detailsService;

    @Autowired
    public CustomerConsumer(CustomerDetailsService service) {
    	this.detailsService = service;
    }
   // private final CustomerDetailsService customerService;


    
    public void CustomerServiceConsumer(Customer customer) {
    	System.out.println("\n\nCustomer received: " + customer.getFirstName());
    	detailsService.saveCustomer(customer);
    }

//    @Bean
//    //@RabbitListener(queues = Config.QUEUE)
//    public Consumer<Customer> CustomerServiceConsumer() {
//          return (customer) -> {
//              LOG.info("Consuming creation of customer {}", customer.getFirstName());
//          };
//    }
  }
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
