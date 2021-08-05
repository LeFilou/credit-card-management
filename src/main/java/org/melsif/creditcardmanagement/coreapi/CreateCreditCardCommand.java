package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;
import org.axonframework.commandhandling.RoutingKey;

@Value
public class CreateCreditCardCommand {
    @RoutingKey
    String creditCardId;
}