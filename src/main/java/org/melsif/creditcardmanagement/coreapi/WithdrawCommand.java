package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class WithdrawCommand {
    @TargetAggregateIdentifier
    UUID creditCardId;
    BigDecimal moneyWithdrawn;
}
