package org.melsif.creditcardmanagement.coreapi;

import lombok.*;
import org.axonframework.eventhandling.EventMessage;

import java.math.BigDecimal;

@Value
public class LimitAssignedEvent {
    String creditCardId;
    BigDecimal initialAmount;
}
