package com.axon.example.contract.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessagingChannel {
	
	String SUBSCRIBED_CHANNEL = "contract.topic";
	String PUBLISHING_CHANNEL = "loan.topic";

	@Input(SUBSCRIBED_CHANNEL)
	SubscribableChannel subscriber();

	@Output(PUBLISHING_CHANNEL)
	MessageChannel loanOutput();
}
