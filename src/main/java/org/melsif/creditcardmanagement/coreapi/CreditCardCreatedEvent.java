package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;

@Value
public class CreditCardCreatedEvent {
    String creditCardId;
}