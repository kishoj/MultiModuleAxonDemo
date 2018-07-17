package com.axon.example.loan.aggregate;

import com.axon.example.loan.command.CreateLoanApplicationCommand;
import com.axon.example.loan.command.UpdateLoanApplicationCommand;
import com.axon.example.loan.command.UpdateLoanApplicationStatusCommand;
import com.axon.example.loan.event.LoanApplicationCreatedEvent;
import com.axon.example.loan.event.LoanApplicationStatusUpdatedEvent;
import com.axon.example.loan.event.LoanApplicationUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class LoanApplicationAggregate {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationAggregate.class);

	@AggregateIdentifier
	private UUID id;

	public LoanApplicationAggregate() {
	}

	@CommandHandler
	public LoanApplicationAggregate(CreateLoanApplicationCommand command) {
		logger.debug("transform CreateLoanCommand to LoanCreatedEvent.");
		this.id = command.getApplicationId();
		LoanApplicationCreatedEvent event = new LoanApplicationCreatedEvent(command.getApplicationId(),
				command.getCustomerName(), command.getEmail(), command.getAmount());
		apply(event);
	}

	@EventSourcingHandler
	public void on(LoanApplicationCreatedEvent event) {
		this.id = event.getApplicationId();
	}

	@CommandHandler
	public void updateStatus(UpdateLoanApplicationStatusCommand command) {
		logger.debug("transform UpdateLoanStatusCommand to LoanStatusUpdatedEvent.");
		this.id = command.getApplicationId();
		LoanApplicationStatusUpdatedEvent event = new LoanApplicationStatusUpdatedEvent(command.getApplicationId(),
				command.getStatus());
		apply(event);
	}

	@EventSourcingHandler
	public void on(LoanApplicationStatusUpdatedEvent event) {
		logger.debug("keep loanId from EventSourcingHandler");
		this.id = event.getApplicationId();
	}

	@CommandHandler
	public void on(UpdateLoanApplicationCommand command) {
		logger.debug("transform UpdateLoanCommand to LoanUpdatedEvent.");
		this.id = command.getApplicationId();
		LoanApplicationUpdatedEvent event = new LoanApplicationUpdatedEvent(command.getApplicationId(),
				command.getContractApprovedBy(), command.getContractNumber());
		apply(event);
	}

	@EventSourcingHandler
	public void on(LoanApplicationUpdatedEvent event) {
		logger.debug("keep applicationId from EventSourcingHandler");
		this.id = event.getApplicationId();
	}

}