package com.axon.example.contract.integration;

import com.axon.example.contract.commands.CreateContractCommand;
import com.axon.example.loan.integration.MessageSentEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LoanApplicationConfirmedListener {

	@Autowired
	private CommandGateway commandGateway;

	@StreamListener(target = MessagingChannel.SUBSCRIBED_CHANNEL)
	public void onReceivedMessage(MessageSentEvent message) {
		// Get Message from subscribed channel
		CreateContractCommand command = new CreateContractCommand(UUID.randomUUID(), message.getApplicationId(),
				message.getSender(), UUID.randomUUID().toString());
		commandGateway.send(command);
	}
}
