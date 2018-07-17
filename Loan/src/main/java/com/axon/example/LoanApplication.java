package com.axon.example;

import com.axon.example.loan.integration.MessagingChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(MessagingChannel.class)
public class LoanApplication {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplication.class);

	public static void main(String[] args) {
		logger.info("Starting application ....");
		SpringApplication.run(LoanApplication.class, args);
	}
}
