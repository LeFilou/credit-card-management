package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;

import java.util.UUID;

@Value
public class CreditCardCreatedEvent {
    UUID creditCardId;
}
