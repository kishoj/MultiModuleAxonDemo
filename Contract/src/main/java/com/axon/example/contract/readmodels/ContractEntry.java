package com.axon.example.contract.readmodels;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.axon.example.contract.aggregates.ContractStatus;

import java.util.UUID;

@Data
@Entity
public class ContractEntry {
    @Id
    private UUID id;
    private String contractNumber;
    private UUID applicationId;
    private String confirmBy;
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;
}