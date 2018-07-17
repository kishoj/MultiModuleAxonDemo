package com.axon.example.loan.readmodel;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
public class LoanApplicationEntry {
    @Id
    private UUID applicationId;
    @Column
    private String customerName;
    @Column
    private String email;
    @Column
    private Double amount;
    @Column
    private String contractApprovedBy;
    @Column
    private String contractNumber;
    @Column
    @Enumerated(EnumType.STRING)
    private LoanApplicationStatus status;
}