package com.axon.example.loan.listener;

import com.axon.example.loan.event.LoanApplicationCreatedEvent;
import com.axon.example.loan.event.LoanApplicationStatusUpdatedEvent;
import com.axon.example.loan.event.LoanApplicationUpdatedEvent;
import com.axon.example.loan.readmodel.LoanApplicationEntry;
import com.axon.example.loan.readmodel.LoanApplicationRepository;

import java.util.Optional;
import java.util.UUID;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanApplicationListener {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationListener.class);

	@Autowired
	private LoanApplicationRepository repository;

	@EventHandler
	public void createLoanEvent(LoanApplicationCreatedEvent event) {
		logger.info("LoanApplicationListener handling event LoanApplicationCreatedEvent " + event.toString());
		LoanApplicationEntry entry = new LoanApplicationEntry();
		BeanUtils.copyProperties(event, entry);
		repository.save(entry);
	}

	@EventHandler
	public void updateStatus(LoanApplicationStatusUpdatedEvent event) {
		logger.info("LoanApplicationListener handling event LoanApplicationStatusUpdatedEvent " + event.toString());
		Optional<LoanApplicationEntry> maybeLoanApplicationEntry = findLoanApplicationEntryById(
				event.getApplicationId());
		if (maybeLoanApplicationEntry.isPresent()) {
			LoanApplicationEntry entry = maybeLoanApplicationEntry.get();
			entry.setStatus(event.getStatus());
			repository.save(entry);
		}
	}

	@EventHandler
	public void updateLoan(LoanApplicationUpdatedEvent event) {
		logger.info("LoanApplicationListener handling LoanApplicationUpdatedEvent event " + event.toString());
		Optional<LoanApplicationEntry> maybeLoanApplicationEntry = findLoanApplicationEntryById(
				event.getApplicationId());
		if (maybeLoanApplicationEntry.isPresent()) {
			LoanApplicationEntry entry = maybeLoanApplicationEntry.get();
			entry.setContractApprovedBy(event.getContractApprovedBy());
			entry.setContractNumber(event.getContractNumber());
			repository.save(entry);
		}
	}

	private Optional<LoanApplicationEntry> findLoanApplicationEntryById(UUID id) {
		return Optional.ofNullable(repository.findOne(id));
	}
}