package org.melsif.creditcardmanagement.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.melsif.creditcardmanagement.coreapi.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCard {

    @AggregateIdentifier
    private UUID id;
    private BigDecimal limit;
    private BigDecimal usedLimit = BigDecimal.ZERO;

    @CommandHandler
    public CreditCard(CreateCreditCardCommand createCreditCardCommand) {
        apply(new CreditCardCreatedEvent(createCreditCardCommand.getCreditCardId()));
    }

    @EventSourcingHandler
    public void on(CreditCardCreatedEvent creditCardCreatedEvent) {
        this.id = creditCardCreatedEvent.getCreditCardId();
    }

    @CommandHandler
    public void handle(AssignLimitCommand assignLimitCommand) {
        if (limitAlreadyAssigned()) {
            throw new IllegalArgumentException("Limit already assigned for credit card : " + id);
        }
        apply(new LimitAssignedEvent(assignLimitCommand.getCreditCardId(), assignLimitCommand.getInitialLimit()));
    }

    @EventHandler
    public void on(LimitAssignedEvent limitAssignedEvent) {
        this.limit = limitAssignedEvent.getInitialAmount();
    }

    @CommandHandler
    public void handle(WithdrawCommand withdrawCommand) {
        if (!limitAlreadyAssigned()) {
            throw new IllegalStateException("Cannot withdraw when a limit is not assigned");
        }
        if (!canWithdraw(withdrawCommand.getMoneyWithdrawn())) {
            throw new IllegalStateException("Cannot withdraw when there is not enough money");
        }
        apply(new WithdrawEvent(withdrawCommand.getCreditCardId(), withdrawCommand.getMoneyWithdrawn()));
    }

    @EventHandler
    public void on(WithdrawEvent withdrawEvent, @Timestamp Instant withdrawTime) {
        this.usedLimit = this.usedLimit.add(withdrawEvent.getMoneyWithdrawn());
    }

    private boolean limitAlreadyAssigned() {
        return limit != null;
    }

    private boolean canWithdraw(BigDecimal money) {
        return availableLimit().compareTo(money) >= 0;
    }

    private BigDecimal availableLimit() {
        return limit.subtract(usedLimit);
    }
}
