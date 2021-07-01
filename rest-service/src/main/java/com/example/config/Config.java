//package com.example.config;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.QueueBuilder;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Config {
//	
//	public static final String QUEUE = "newtest_queue";
//	public static final String EXCHANGE="new_exchange";
//	public static final String ROUTING_KEY="new_routing_key";
//
//	@Bean
//    public static Queue queue() {
//    	return QueueBuilder.nonDurable(QUEUE).exclusive().build();
//    }
//	
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
//	public static MessageConverter converter() {
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
