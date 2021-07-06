package org.melsif.creditcardmanagement.command;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.Objects;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CreditCardTest {

    private FixtureConfiguration<CreditCard> fixture;

    @BeforeEach
    void setup() {
        fixture = new AggregateTestFixture<>(CreditCard.class);
    }

    @Test
    public void cannot_withdraw_when_a_limit_is_not_assigned() {
    }

    @Test
    public void cannot_withdraw_when_there_is_not_enough_money() {

    }

    @Test
    public void cannot_withdraw_when_there_was_withdrawal_within_the_last_hour() {

    }

    @Test
    public void can_withdraw() {

    }

    @Test
    public void cannot_assign_limit_when_it_was_already_assigned() {

    }

    @Test
    public void can_assign_limit() {

    }

    @Test
    public void can_repay() {

    }
}