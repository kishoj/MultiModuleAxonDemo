package com.axon.example.loan.sagas;

import com.axon.example.loan.command.UpdateLoanApplicationStatusCommand;
import com.axon.example.loan.event.LoanApplicationCreatedEvent;
import com.axon.example.loan.event.LoanApplicationUpdatedEvent;
import com.axon.example.loan.readmodel.LoanApplicationStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.saga.EndSaga;
import org.axonframework.eventhandling.saga.SagaEventHandler;
import org.axonframework.eventhandling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
public class LoanManagementSaga {

	private final Logger LOGGER = LoggerFactory.getLogger(LoanManagementSaga.class);

	@Autowired
	private transient CommandGateway commandGateway;

	@StartSaga
	@SagaEventHandler(associationProperty = "applicationId")
	public void on(LoanApplicationCreatedEvent event) {
		LOGGER.info("Starting Saga with LoanApplicationCreatedEvent " + event.toString());
		UpdateLoanApplicationStatusCommand command = new UpdateLoanApplicationStatusCommand(event.getApplicationId(),
				LoanApplicationStatus.DRAFT);
		commandGateway.send(command);
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "applicationId")
	private void onReceived(LoanApplicationUpdatedEvent event) {
		LOGGER.info("Ending Saga with LoanApplicationUpdatedEvent " + event.toString());
	}
}
