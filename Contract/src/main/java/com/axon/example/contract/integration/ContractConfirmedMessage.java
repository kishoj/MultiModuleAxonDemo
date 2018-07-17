package com.axon.example.contract.integration;

import lombok.Value;

import java.util.UUID;

@Value
public class ContractConfirmedMessage {
    private UUID applicationId;
    private String contractApprovedBy;
    private String contractNumber;
}