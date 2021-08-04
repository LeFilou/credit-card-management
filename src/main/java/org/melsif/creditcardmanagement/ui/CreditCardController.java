package org.melsif.creditcardmanagement.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.melsif.creditcardmanagement.coreapi.CreateCreditCardCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CreditCardController {

    private final CommandGateway commandGateway;

    @PostMapping(value = "/credit-cards",
        produces = { "application/json" },
        consumes = { "application/json" })
    public ResponseEntity<Void> newCreditCard(@RequestBody CreateCreditCardRequest createCreditCardRequest) {
        commandGateway.sendAndWait(new CreateCreditCardCommand(UUID.fromString(createCreditCardRequest.getCreditCardId())));
        log.debug("Success");
        return ResponseEntity.status(201).build();
    }
}
