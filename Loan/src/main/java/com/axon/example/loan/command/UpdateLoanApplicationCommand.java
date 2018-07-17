package com.axon.example.loan.command;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class UpdateLoanApplicationCommand {
    @TargetAggregateIdentifier
    private UUID applicationId;
    private String contractApprovedBy;
    private String contractNumber;
}
