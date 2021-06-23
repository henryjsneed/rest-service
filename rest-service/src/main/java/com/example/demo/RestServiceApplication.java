package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableBinding(Sink.class)
public class RestServiceApplication {

  private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

//  @StreamListener(Sink.INPUT)
//  public void orderDispatched(List<Product> products) {
//      products.forEach(product -> {
//          log.info("Order dispatched to your mailing address : " + product);
//      });
//  }

  public static void main(String[] args) {
    SpringApplication.run(RestServiceApplication.class);
  }

//  @Bean
//  public Function<Customer, String> uppercase() {
//	  return c -> {
//		  System.out.println("Uppercasing " + c);
//		  String result = c.getFirstName().toUpperCase();
//		  return result;
//	  };
//  }
  
  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      // save a few customers
      repository.save(new Customer("Jack", "Walker", "jwalker35@gmail.com", "jBaur22"));
      repository.save(new Customer("Chloe", "Obery", "cobery305@gmail.com", "password305"));
      repository.save(new Customer("Kim", "Baker", "kimkim@gmail.com", "kim222password"));
      repository.save(new Customer("Brian", "Smith", "bsmith403@gmail.com", "dpalms44"));
      repository.save(new Customer("Mathew", "Johnson", "mjohnson33@gmail.com", "11password25"));

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (Customer customer : repository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      Optional<Customer> customer = repository.findById(1L);
      log.info("Customer found with findById(1L):");
      log.info("--------------------------------");
      log.info(customer.toString());
      log.info("");

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      repository.findByLastName("Bauer").forEach(bauer -> {
        log.info(bauer.toString());
      });
      // for (Customer bauer : repository.findByLastName("Bauer")) {
      //  log.info(bauer.toString());
      // }
      log.info("");
    };
  }

}
