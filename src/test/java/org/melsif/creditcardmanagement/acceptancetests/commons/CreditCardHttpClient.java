package org.melsif.creditcardmanagement.acceptancetests.commons;

import org.melsif.creditcardmanagement.ui.CreateCreditCardRequest;
import org.melsif.creditcardmanagement.ui.AssignLimitRequest;
import org.melsif.creditcardmanagement.coreapi.CreditCardSummary;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class CreditCardHttpClient {

    @LocalServerPort
    private int port;

    private String creditCardEndpoint() {
        return "http://localhost" + ":" + port + "/credit-cards";
    }
    private final RestTemplate restTemplate = new RestTemplate();

    public int createNewCreditCard(final String creditCardId) {
        return restTemplate.postForEntity(creditCardEndpoint(),
                new CreateCreditCardRequest(creditCardId), Void.class)
            .getStatusCodeValue();
    }

    public int assignALimit(String creditCardId, int limit) {
        return restTemplate.postForEntity(withdrawEndpoint(creditCardId),
            new AssignLimitRequest(new BigDecimal(limit)), Void.class)
            .getStatusCodeValue();
    }

    private String withdrawEndpoint(String creditCardId) {
        return creditCardEndpoint() + "/" + creditCardId + "/limit";
    }

    private String getEndpoint(String creditCardId) {
        return creditCardEndpoint() + "/" + creditCardId;
    }

    public CreditCardSummary getCreditCardSummary(String creditCardId) {
        return restTemplate.getForObject(getEndpoint(creditCardId), CreditCardSummary.class);
    }
}
