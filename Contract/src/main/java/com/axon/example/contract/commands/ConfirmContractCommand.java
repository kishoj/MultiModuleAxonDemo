package com.axon.example.contract.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Value
public class ConfirmContractCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private String approvedBy;
}
