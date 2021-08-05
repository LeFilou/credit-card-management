package org.melsif.creditcardmanagement.query;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.melsif.creditcardmanagement.coreapi.CreditCardCreatedEvent;
import org.melsif.creditcardmanagement.coreapi.CreditCardSummary;
import org.melsif.creditcardmanagement.coreapi.LimitAssignedEvent;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
public class CreditCardProjection {

    private final CreditCardSummaryRepository creditCardSummaryRepository;

    @EventHandler
    public void on(CreditCardCreatedEvent event) {
        creditCardSummaryRepository.save(new CreditCardSummary(event.getCreditCardId()));
    }

    @EventHandler
    public void on(LimitAssignedEvent event) {
        creditCardSummaryRepository.findById(event.getCreditCardId())
            .ifPresent(creditCardSummary -> creditCardSummary.assignLimit(event.getInitialAmount()));
    }

    @QueryHandler
    public CreditCardSummary handle(FindCreditCardSummaryQuery query) {
        return creditCardSummaryRepository
            .findById(query.getCreditCardId())
            .orElseThrow(EntityNotFoundException::new);
    }
}
