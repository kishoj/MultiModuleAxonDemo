package com.axon.example.contract.integration;

import com.axon.example.contract.events.ContractConfirmEvent;
import com.axon.example.contract.readmodels.ContractEntry;
import com.axon.example.contract.readmodels.ContractRepository;

import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ContractMessenger {

	@Autowired
	private ContractRepository contractRepositary;

	@Autowired
	private MessagingChannel messagingChannel;

	@EventHandler
	public void on(ContractConfirmEvent event) {
		Optional<ContractEntry> maybeContractEntry = Optional.ofNullable(contractRepositary.findOne(event.getId()));
		if (maybeContractEntry.isPresent()) {
			ContractEntry contractEntry = maybeContractEntry.get();
			messagingChannel.loanOutput()
					.send(MessageBuilder.withPayload(new ContractConfirmedMessage(contractEntry.getApplicationId(),
							event.getConfirmedBy(), contractEntry.getContractNumber())).build());
		}
	}

}