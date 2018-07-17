package com.axon.example.loan.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessagingChannel {

	String SUBSCRIBED_CHANNEL = "loan.topic";
	String PUBLISHING_CHANNEL = "contract.topic";

	@Input(SUBSCRIBED_CHANNEL)
	SubscribableChannel subscriber();

	@Output(PUBLISHING_CHANNEL)
	MessageChannel contractOutput();

}
