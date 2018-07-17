package com.axon.example.loan.command;

import com.axon.example.loan.readmodel.LoanApplicationStatus;
import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class UpdateLoanApplicationStatusCommand {
    @TargetAggregateIdentifier
    private UUID applicationId;

    private LoanApplicationStatus status;
}