package com.axon.example.loan.integration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(Source.class)
public class AMQPConfig {

	@Bean
	@Autowired
	public TopicExchange contractExchange(
			@Value("${spring.cloud.stream.bindings.output-contract.destination}") String exchangeName) {
		return new TopicExchange(exchangeName, true, false);
	}

}