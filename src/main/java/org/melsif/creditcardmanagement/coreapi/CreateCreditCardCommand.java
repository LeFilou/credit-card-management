package org.melsif.creditcardmanagement.coreapi;

import lombok.Value;
import org.axonframework.commandhandling.RoutingKey;

import java.util.UUID;

@Value
public class CreateCreditCardCommand {
    @RoutingKey
    UUID creditCardId;
}
