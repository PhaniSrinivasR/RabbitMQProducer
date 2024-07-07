package com.rabbitMQ.RabbitMQProducer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.queue.register.name}")
	private String registerQueue;

	@Value("${rabbitmq.routing.register.key}")
	private String registerRoutingKey;

	@Value("${rabbitmq.queue.login.name}")
	private String loginQueue;

	@Value("${rabbitmq.routing.login.key}")
	private String loginRoutingKey;

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	@Bean
	public Queue registerQueue() {
		return new Queue(registerQueue);
	}

	@Bean
	public Queue loginQueue() {
		return new Queue(loginQueue);
	}

	@Bean
	public Binding registerBinding() {
		return BindingBuilder.bind(registerQueue()).to(exchange()).with(registerRoutingKey);
	}

	@Bean
	public Binding loginBinding() {
		return BindingBuilder.bind(loginQueue()).to(exchange()).with(loginRoutingKey);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

}
