package com.rabbitMQ.RabbitMQProducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitMQ.RabbitMQProducer.dto.LoginRequest;
import com.rabbitMQ.RabbitMQProducer.dto.RegisterRequest;

@Service
public class ProducerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.register.key}")
	private String registerRoutingKey;

	@Value("${rabbitmq.routing.login.key}")
	private String loginRoutingKey;

	private RabbitTemplate rabbitTemplate;

	public ProducerService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void registerUser(RegisterRequest registerDetails) {
		LOGGER.info(String.format("Registered user details and sent -> %s", registerDetails.toString()));
		rabbitTemplate.convertAndSend(exchange, registerRoutingKey, registerDetails);
	}

	public void loginUser(LoginRequest loginDetails) {
		LOGGER.info(String.format("Login details of user sent -> %s", loginDetails.toString()));
		rabbitTemplate.convertAndSend(exchange, loginRoutingKey, loginDetails);
	}

}
