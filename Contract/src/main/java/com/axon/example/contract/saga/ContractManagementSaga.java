package com.axon.example.contract.saga;

import com.axon.example.contract.aggregates.ContractStatus;
import com.axon.example.contract.commands.UpdateContractStatusCommand;
import com.axon.example.contract.events.ContractConfirmEvent;
import com.axon.example.contract.events.ContractCreatedEvent;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
public class ContractManagementSaga {

	private UUID id;
	private ContractStatus contractStatus;

	@Autowired
	private transient CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(ContractCreatedEvent event) {
		this.id = event.getId();
		this.contractStatus = ContractStatus.DRAFT;
		updateContractStatus();	
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "id")
	public void on(ContractConfirmEvent event) {
		this.id = event.getId();
		this.contractStatus = ContractStatus.DONE;
		updateContractStatus();		
	}

	private void updateContractStatus() {
		UpdateContractStatusCommand command = new UpdateContractStatusCommand(id, contractStatus);
		commandGateway.send(command);
	}

}