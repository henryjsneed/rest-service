package com.example.demo;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
  
  @SpringBootApplication
  public class RestServiceApplication {
   
	private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    static final String topicExchangeName = "spring-boot-exchange";

    static final String queueName = "spring-boot";

    @Bean
    Queue queue() {
      return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
      return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
      return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }

//	@Bean
//	public AmqpTemplate template(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//		return rabbitTemplate;
//	}
    
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter) {
    	//allows for Customer objects to be sent as arg
    	listenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames(queueName);
	    container.setMessageListener(listenerAdapter);
	    return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(CustomerConsumer consumer) {
      return new MessageListenerAdapter(consumer, "CustomerServiceConsumer");
    }

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args/*, "--spring.cloud.function.definition=customerConsumer"*/);
//        template = context.getBean(RabbitTemplate.class);
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//    	factory.setMessageConverter(Config.converter());
//          template = Config.template(factory);
//        template.setMessageConverter(Config.converter());
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//    	factory.setMessageConverter(Config.converter());
//        while (true) {
//        	template.convertAndSend("", Config.QUEUE, "hello");
//        	Thread.sleep(10000L);
//        }
      }

  }
  

  // essentially acts as a rest endpoint at /uppercase
//  @Bean
//  public Function<String, String> uppercase() {
//	  return c -> {
//		  System.out.println("Uppercasing " + c);
//		  //String result = c.getFirstName().toUpperCase();
//		 
//		  return c.toUpperCase();
//	  };
//  }
//  
////
//  // This bean does not need to be clarified in spring.cloud.function.definition because
//  // it is not of type Function (and does not follow a functional pattern)
//  @Bean
//  public CommandLineRunner demo(CustomerRepository repository) {
//    return (args) -> {
//      // save a few customers
//      repository.save(new Customer("Jack", "Walker", "jwalker35@gmail.com", "jBaur22"));
//      repository.save(new Customer("Chloe", "Obery", "cobery305@gmail.com", "password305"));
//      repository.save(new Customer("Kim", "Baker", "kimkim@gmail.com", "kim222password"));
//      repository.save(new Customer("Brian", "Smith", "bsmith403@gmail.com", "dpalms44"));
//      repository.save(new Customer("Mathew", "Johnson", "mjohnson33@gmail.com", "11password25"));
//
//      // fetch all customers
//      log.info("Customers found with findAll():");
//      log.info("-------------------------------");
//      for (Customer customer : repository.findAll()) {
//        log.info(customer.toString());
//      }
//      log.info("");
//
//      // fetch an individual customer by ID
//      Optional<Customer> customer = repository.findById(1L);
//      log.info("Customer found with findById(1L):");
//      log.info("--------------------------------");
//      log.info(customer.toString());
//      log.info("");
//
//      // fetch customers by last name
//      log.info("Customer found with findByLastName('Bauer'):");
//      log.info("--------------------------------------------");
//      repository.findByLastName("Bauer").forEach(bauer -> {
//        log.info(bauer.toString());
//      });
//      // for (Customer bauer : repository.findByLastName("Bauer")) {
//      //  log.info(bauer.toString());
//      // }
//      log.info("");
//    };
//  }
//  
//  @Bean
//  public Supplier<DataEvent<String, Customer>> savedMessage() {
//      return () -> {
//          return null;
//      };
//  }


