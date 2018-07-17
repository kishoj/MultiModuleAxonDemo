package com.axon.example.contract.commands;

import lombok.Value;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import com.axon.example.contract.aggregates.ContractStatus;

import java.util.UUID;

@Value
public class UpdateContractStatusCommand {
    @TargetAggregateIdentifier
    private UUID id;
    private ContractStatus contractStatus;
}
