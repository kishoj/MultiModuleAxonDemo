package com.axon.example.contract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.axon.example.contract.integration.MessagingChannel;

@SpringBootApplication
@EnableBinding(MessagingChannel.class)
public class ContractMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContractMainApplication.class, args);
	}
}
