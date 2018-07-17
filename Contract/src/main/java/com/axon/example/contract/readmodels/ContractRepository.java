package com.axon.example.contract.readmodels;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContractRepository extends CrudRepository<ContractEntry, UUID> {
}