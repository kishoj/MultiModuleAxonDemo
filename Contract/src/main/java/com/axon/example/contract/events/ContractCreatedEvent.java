package com.axon.example.contract.events;

import lombok.Value;

import java.util.UUID;

@Value
public class ContractCreatedEvent {
    private UUID id;
    private String contractNumber;
    private UUID applicationId;
}
