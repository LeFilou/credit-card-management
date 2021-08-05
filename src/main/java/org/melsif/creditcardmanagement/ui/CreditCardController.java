package org.melsif.creditcardmanagement.ui;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.melsif.creditcardmanagement.coreapi.AssignLimitCommand;
import org.melsif.creditcardmanagement.coreapi.CreateCreditCardCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(path = "/credit-cards", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CreditCardController {

    private final CommandGateway commandGateway;

    @PostMapping(produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Void> newCreditCard(@RequestBody CreateCreditCardRequest createCreditCardRequest) {
        commandGateway.sendAndWait(new CreateCreditCardCommand(UUID.fromString(createCreditCardRequest.getCreditCardId())));
        return ResponseEntity.status(201).build();
    }

    @PostMapping(path = "/{creditCardId}/limit",
        produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Void> assignLimit(@PathVariable String creditCardId,
                                            @RequestBody AssignLimitRequest assignLimitRequest) {

        final BigDecimal limitAssigned = assignLimitRequest.getLimitAssigned();
        commandGateway.sendAndWait(new AssignLimitCommand(UUID.fromString(creditCardId), limitAssigned));
        return ResponseEntity.status(204).build();
    }
}
