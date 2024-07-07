package com.rabbitMQ.RabbitMQProducer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitMQ.RabbitMQProducer.dto.LoginRequest;
import com.rabbitMQ.RabbitMQProducer.dto.RegisterRequest;
import com.rabbitMQ.RabbitMQProducer.service.ProducerService;

@RestController
@RequestMapping("/api/v1")
public class RabbitMQProducerController {

	private ProducerService producerService;

	public RabbitMQProducerController(ProducerService producerService) {
		this.producerService = producerService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		producerService.registerUser(registerRequest);
		return ResponseEntity.ok("User registered and message sent to RabbitMQ");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
		producerService.loginUser(loginRequest);
		return ResponseEntity.ok("User login message sent to RabbitMQ");
	}

}
