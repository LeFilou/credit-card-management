package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Value
public class AssignLimitCommand {
    @TargetAggregateIdentifier
    String creditCardId;
    BigDecimal initialLimit;
}