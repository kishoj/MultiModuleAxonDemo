package com.axon.example.contract;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.axon.example.contract.commands.CreateContractCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ContractMainApplication.class })
@ActiveProfiles("default")
public class CreateContractTests {

	private static Logger logger = LoggerFactory.getLogger(CreateContractTests.class);
	
	@Autowired
	private CommandGateway commandGateway;

	@Test
	public void testCreateContract() {
		logger.info("Starting Unit Test for Creating Contract ...");
		commandGateway.send(new CreateContractCommand(
				UUID.randomUUID(), 
				UUID.randomUUID(), 
				"Kishoj Bajracharya",
				UUID.randomUUID().toString()
				));
		logger.info("End of Unit Test for Creating Contract ...");
	}
	
}