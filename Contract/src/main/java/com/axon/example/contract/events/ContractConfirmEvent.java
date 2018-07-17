package com.axon.example.contract.events;

import lombok.Value;

import java.util.UUID;

@Value
public class ContractConfirmEvent {
    private UUID id;
    private String confirmedBy;
}
