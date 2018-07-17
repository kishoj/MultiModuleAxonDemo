package com.axon.example.loan.event;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class LoanApplicationUpdatedEvent {
    @TargetAggregateIdentifier
    private UUID applicationId;
    private String contractApprovedBy;
    private String contractNumber;
}
