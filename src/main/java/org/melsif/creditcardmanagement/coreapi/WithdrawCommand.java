package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Value
public class WithdrawCommand {
    @TargetAggregateIdentifier
    String creditCardId;
    BigDecimal moneyWithdrawn;
}