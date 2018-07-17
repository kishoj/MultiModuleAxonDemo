package com.axon.example.contract.api;

import com.axon.example.contract.commands.ConfirmContractCommand;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/contracts")
public class ContractApi {

	private static final String CONFIRM_BY = "Kishoj Bajracharya";

	@Autowired
	private CommandGateway commandGateway;

	public static final Logger logger = LoggerFactory.getLogger(ContractApi.class);

	@GetMapping("/{id}/confirm")
	public ResponseEntity<Void> confirmContract(@PathVariable("id") UUID id) {
		commandGateway.send(new ConfirmContractCommand(id, CONFIRM_BY));
		return new ResponseEntity<>(HttpStatus.OK);
	}

}