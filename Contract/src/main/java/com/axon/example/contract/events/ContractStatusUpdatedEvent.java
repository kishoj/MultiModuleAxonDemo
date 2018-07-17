package com.axon.example.contract.events;

import lombok.Value;

import java.util.UUID;

import com.axon.example.contract.aggregates.ContractStatus;

@Value
public class ContractStatusUpdatedEvent {
    private UUID id;
    private ContractStatus contractStatus;
}
