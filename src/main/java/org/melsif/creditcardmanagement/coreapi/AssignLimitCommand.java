package org.melsif.creditcardmanagement.coreapi;

import lombok.*;
import org.axonframework.commandhandling.RoutingKey;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AssignLimitCommand {
    @RoutingKey
    UUID creditCardId;
    BigDecimal initialLimit;
}
