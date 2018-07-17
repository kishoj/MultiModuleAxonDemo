package com.axon.example.loan.integration;

import com.axon.example.contract.integration.ContractConfirmedMessage;
import com.axon.example.loan.command.UpdateLoanApplicationCommand;
import com.axon.example.loan.command.UpdateLoanApplicationStatusCommand;
import com.axon.example.loan.readmodel.LoanApplicationStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ContractConfirmedListener {

	private final static Logger LOGGER = LoggerFactory.getLogger(ContractConfirmedListener.class);

	@Autowired
	private CommandGateway commandGateway;

	@StreamListener(target = MessagingChannel.SUBSCRIBED_CHANNEL)
	private void onReceived(ContractConfirmedMessage event) {
		LOGGER.info("Received ContractConfirmEventAsMessage from RabbitMQ.topic " + event.toString());
		LOGGER.info(event.toString());
		UpdateLoanApplicationCommand command1 = new UpdateLoanApplicationCommand(event.getApplicationId(), event.getContractApprovedBy(),
				event.getContractNumber());
		commandGateway.send(command1);
		UpdateLoanApplicationStatusCommand command2 = new UpdateLoanApplicationStatusCommand(event.getApplicationId(),
				LoanApplicationStatus.APPROVED);
		commandGateway.send(command2);
	}

}