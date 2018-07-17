package com.axon.example.loan.command;

import lombok.*;

import java.util.UUID;

@Value
public class CreateLoanApplicationCommand {
    private UUID applicationId;
    private String customerName;
    private String email;
    private Double amount;
}
