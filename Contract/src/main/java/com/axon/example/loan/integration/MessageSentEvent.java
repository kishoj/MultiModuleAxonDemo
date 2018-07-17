package com.axon.example.loan.integration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class MessageSentEvent{
    private UUID applicationId;
    private String sender;
}