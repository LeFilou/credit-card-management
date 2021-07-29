package org.melsif.creditcardmanagement.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.melsif.creditcardmanagement.coreapi.AssignLimitCommand;
import org.melsif.creditcardmanagement.coreapi.LimitAssignedEvent;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCard {
    @AggregateIdentifier
    private String id;
    private BigDecimal initialLimit;

    @CommandHandler
    public CreditCard(AssignLimitCommand assignLimitCommand) {
        apply(new LimitAssignedEvent(assignLimitCommand.getCreditCardId(), assignLimitCommand.getInitialLimit()));
    }

    @EventSourcingHandler
    public void on(LimitAssignedEvent limitAssignedEvent) {
        this.id = limitAssignedEvent.getCreditCardId();
        this.initialLimit = limitAssignedEvent.getInitialAmount();
    }
}
