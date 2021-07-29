package org.melsif.creditcardmanagement.command;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.melsif.creditcardmanagement.coreapi.AssignLimitCommand;
import org.melsif.creditcardmanagement.coreapi.LimitAssignedEvent;

import java.math.BigDecimal;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreditCardTest {

    private FixtureConfiguration<CreditCard> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(CreditCard.class);
    }

    @Test
    public void can_assign_limit() {
        final String creditCardId = "1234";
        final BigDecimal initialAmount = new BigDecimal(1500);
        fixture
            .givenNoPriorActivity()
            .when(new AssignLimitCommand(creditCardId, initialAmount))
            .expectEvents(new LimitAssignedEvent(creditCardId, initialAmount));
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
    void cannot_assign_limit_when_it_was_already_assigned() {

    }

    @Test
    public void can_repay() {

    }
}