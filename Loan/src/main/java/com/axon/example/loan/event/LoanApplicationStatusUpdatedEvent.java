package com.axon.example.loan.event;

import com.axon.example.loan.readmodel.LoanApplicationStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class LoanApplicationStatusUpdatedEvent {
    private UUID applicationId;
    private LoanApplicationStatus status;
}