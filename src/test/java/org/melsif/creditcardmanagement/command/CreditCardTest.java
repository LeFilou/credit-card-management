package org.melsif.creditcardmanagement.command;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.melsif.creditcardmanagement.coreapi.AssignLimitCommand;
import org.melsif.creditcardmanagement.coreapi.CreateCreditCardCommand;
import org.melsif.creditcardmanagement.coreapi.CreditCardCreatedEvent;
import org.melsif.creditcardmanagement.coreapi.LimitAssignedEvent;

import java.math.BigDecimal;
import java.util.UUID;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreditCardTest {

    private FixtureConfiguration<CreditCard> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(CreditCard.class);
    }

    @Test
    void can_create_credit_card() {
        final UUID creditCardId = UUID.randomUUID();
        fixture.givenNoPriorActivity()
            .when(new CreateCreditCardCommand(creditCardId))
            .expectEvents(new CreditCardCreatedEvent(creditCardId));
    }

    @Test
    void can_assign_limit() {
        final UUID creditCardId = UUID.randomUUID();
        final BigDecimal limit = new BigDecimal(1500);
        fixture
            .given(new CreditCardCreatedEvent(creditCardId))
            .when(new AssignLimitCommand(creditCardId, limit))
            .expectEvents(new LimitAssignedEvent(creditCardId, limit));
    }

    @Test
    void cannot_assign_limit_when_it_was_already_assigned() {
        final UUID creditCardId = UUID.randomUUID();
        final BigDecimal initialLimit = new BigDecimal(1500);
        fixture
            .given(new LimitAssignedEvent(creditCardId, initialLimit))
            .when(new AssignLimitCommand(creditCardId, new BigDecimal(2000)))
            .expectException(IllegalArgumentException.class);
    }

    @Test
    void can_withdraw() {

    }

    @Test
    void cannot_withdraw_when_a_limit_is_not_assigned() {

    }

    @Test
    void cannot_withdraw_when_there_is_not_enough_money() {

    }

    @Test
    void cannot_withdraw_when_there_was_withdrawal_within_the_last_hour() {

    }

    @Test
    public void can_repay() {

    }
}