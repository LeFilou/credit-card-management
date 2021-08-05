package org.melsif.creditcardmanagement.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.melsif.creditcardmanagement.coreapi.AssignLimitCommand;
import org.melsif.creditcardmanagement.coreapi.CreateCreditCardCommand;
import org.melsif.creditcardmanagement.coreapi.CreditCardSummary;
import org.melsif.creditcardmanagement.query.FindCreditCardSummaryQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping(path = "/credit-cards", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class CreditCardController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping(path = "/{creditCardId}",
        produces = { "application/json" },
        consumes = { "application/json" })
    public ResponseEntity<CreditCardSummary> getCreditCardSummary(@PathVariable String creditCardId) throws ExecutionException, InterruptedException {
        var creditCardSummary = queryGateway
            .query(new FindCreditCardSummaryQuery(creditCardId), CreditCardSummary.class).get();
        log.debug("Credit card : {}", creditCardSummary);
        return ResponseEntity.ok(creditCardSummary);
    }

    @PostMapping(produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Void> newCreditCard(@RequestBody CreateCreditCardRequest createCreditCardRequest) {
        commandGateway.sendAndWait(new CreateCreditCardCommand(createCreditCardRequest.getCreditCardId()));
        return ResponseEntity.status(201).build();
    }

    @PostMapping(path = "/{creditCardId}/limit",
        produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Void> assignLimit(@PathVariable String creditCardId,
                                            @RequestBody AssignLimitRequest assignLimitRequest) {

        final BigDecimal limitAssigned = assignLimitRequest.getLimitAssigned();
        commandGateway.sendAndWait(new AssignLimitCommand(creditCardId, limitAssigned));
        return ResponseEntity.status(204).build();
    }
}
