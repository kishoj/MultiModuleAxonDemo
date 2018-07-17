package com.axon.example.loan.integration;

import lombok.Value;
import java.util.UUID;

@Value
public class MessageSentEvent {
    private UUID applicationId;
    private String sender;
}