package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class LimitAssignedEvent {
    UUID creditCardId;
    BigDecimal initialAmount;
}