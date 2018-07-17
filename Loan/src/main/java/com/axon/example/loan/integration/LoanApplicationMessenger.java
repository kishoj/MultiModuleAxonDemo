package com.axon.example.loan.integration;

import com.axon.example.loan.event.LoanApplicationCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class LoanApplicationMessenger {
	private final static Logger LOGGER = LoggerFactory.getLogger(LoanApplicationMessenger.class);

	@Autowired
	private MessagingChannel messagingChannel;

	@EventHandler
	public void on(LoanApplicationCreatedEvent event) {
		LOGGER.info("Message prepare to keep in messaging queue : {}", event);
		messagingChannel.contractOutput().send(MessageBuilder
				.withPayload(new MessageSentEvent(event.getApplicationId(), event.getCustomerName())).build());
		LOGGER.info("Message is kept in messaging queue");
	}

}
