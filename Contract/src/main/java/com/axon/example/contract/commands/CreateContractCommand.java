package com.axon.example.contract.commands;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateContractCommand {
    private UUID id;
    private UUID applicationId;
    private String customerName;
    private String contractNumber;
}
