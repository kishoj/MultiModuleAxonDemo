package com.axon.example.loan.api;

import com.axon.example.loan.command.CreateLoanApplicationCommand;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
public class LoanApi {

	@Autowired
	private CommandGateway commandGateway;

	@GetMapping
	public ResponseEntity<Void> createLoanApplication() {
		try {
			commandGateway.send(new CreateLoanApplicationCommand(
					UUID.randomUUID(), 
					"Kishoj Bajracharya",
					"kishoj@gmail.com", 
					Double.valueOf(1000.00)));
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}

}