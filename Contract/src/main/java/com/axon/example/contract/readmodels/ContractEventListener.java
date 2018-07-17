package com.axon.example.contract.readmodels;

import java.util.Optional;
import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axon.example.contract.events.ContractConfirmEvent;
import com.axon.example.contract.events.ContractCreatedEvent;
import com.axon.example.contract.events.ContractStatusUpdatedEvent;

@Component
public class ContractEventListener {

	@Autowired
	private ContractRepository contractRepositary;

	@EventHandler
	public void on(ContractCreatedEvent event) {
		ContractEntry contractEntry = new ContractEntry();
		contractEntry.setId(event.getId());
		contractEntry.setContractNumber(event.getContractNumber());
		contractEntry.setApplicationId(event.getApplicationId());
		contractRepositary.save(contractEntry);
	}

	@EventHandler
	public void on(ContractConfirmEvent event) {
		Optional<ContractEntry> maybeContractEntry = findContractEntryById(event.getId());
		if (maybeContractEntry.isPresent()) {
			ContractEntry contractEntry = maybeContractEntry.get();
			contractEntry.setConfirmBy(event.getConfirmedBy());
			contractRepositary.save(contractEntry);
		}
	}

	@EventHandler
	public void on(ContractStatusUpdatedEvent event) {
		Optional<ContractEntry> maybeContractEntry = findContractEntryById(event.getId());
		if (maybeContractEntry.isPresent()) {
			ContractEntry contractEntry = maybeContractEntry.get();
			contractEntry.setContractStatus(event.getContractStatus());
			contractRepositary.save(contractEntry);
		}
	}

	private Optional<ContractEntry> findContractEntryById(UUID id) {
		return Optional.ofNullable(contractRepositary.findOne(id));
	}
	
}