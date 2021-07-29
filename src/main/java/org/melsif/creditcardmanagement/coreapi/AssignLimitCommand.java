package org.melsif.creditcardmanagement.coreapi;

import lombok.*;
import org.axonframework.commandhandling.RoutingKey;

import java.math.BigDecimal;

@Value
public class AssignLimitCommand {
    @RoutingKey
    String creditCardId;
    BigDecimal initialLimit;
}
