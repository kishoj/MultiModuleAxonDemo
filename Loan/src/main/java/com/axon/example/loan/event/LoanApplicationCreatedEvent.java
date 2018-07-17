package com.axon.example.loan.event;

import lombok.Value;

import java.util.UUID;

@Value
public class LoanApplicationCreatedEvent {
    private UUID applicationId;
    private String customerName;
    private String email;
    private Double amount;
}