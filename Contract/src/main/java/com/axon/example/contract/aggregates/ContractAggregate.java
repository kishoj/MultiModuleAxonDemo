package com.axon.example.contract.aggregates;

import com.axon.example.contract.commands.ConfirmContractCommand;
import com.axon.example.contract.commands.CreateContractCommand;
import com.axon.example.contract.commands.UpdateContractStatusCommand;
import com.axon.example.contract.events.ContractConfirmEvent;
import com.axon.example.contract.events.ContractCreatedEvent;
import com.axon.example.contract.events.ContractStatusUpdatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class ContractAggregate {

	@AggregateIdentifier
	private UUID id;

	private String confirmedBy;
	private ContractStatus contractStatus;

	public ContractAggregate() {
	}

	@CommandHandler
	ContractAggregate(CreateContractCommand command) {
		apply(new ContractCreatedEvent(command.getId(), command.getContractNumber(), command.getApplicationId()));
	}

	@EventSourcingHandler
	public void on(ContractCreatedEvent event) {
		this.id = event.getId();
	}

	@CommandHandler
	public void on(ConfirmContractCommand command) {
		apply(new ContractConfirmEvent(command.getId(), command.getApprovedBy()));
	}

	@EventSourcingHandler
	public void on(ContractConfirmEvent event) {
		this.id = event.getId();
		this.confirmedBy = event.getConfirmedBy();
	}

	@CommandHandler
	public void on(UpdateContractStatusCommand command) {
		apply(new ContractStatusUpdatedEvent(command.getId(), command.getContractStatus()));
	}

	@EventSourcingHandler
	public void on(ContractStatusUpdatedEvent event) {
		this.id = event.getId();
		this.contractStatus = event.getContractStatus();
	}

}