//package com.example.config;
//
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//@Configuration
////@RefreshScope
//public class MessagingConfig {
//	///@Value("${customer.rabbitmq.requestqueue}")
//	public static final String QUEUE="new_queue";
//	//@Value("${customer.rabbitmq.directexchange}")
//	public static final String EXCHANGE="new_exchange";
//	//@Value("${customer.rabbitmq.routingkey}")
//	public static final String ROUTING_KEY="new_routing_key";
//
//	@Bean
//    public Queue queue() {
//    	return new Queue(QUEUE);
//    }
//	@Bean
//	public TopicExchange exchange() {
//		return new TopicExchange(EXCHANGE);
//	}
//	
//	// binds exchange with queues
//	@Bean
//	public Binding binding(Queue queue, TopicExchange exchange) {
//		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
//	}
//	
//	@Bean
//	public MessageConverter converter() {
//		return new Jackson2JsonMessageConverter();
//	}
//	
//	@Bean
//	public AmqpTemplate template(ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(converter());
//		return rabbitTemplate;
//	}
//}
