package com.axon.example.loan.readmodel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanApplicationRepository extends CrudRepository<LoanApplicationEntry, UUID> {
}
