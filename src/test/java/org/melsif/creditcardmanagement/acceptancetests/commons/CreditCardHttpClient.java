package org.melsif.creditcardmanagement.acceptancetests.commons;

import org.melsif.creditcardmanagement.ui.CreateCreditCardRequest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class CreditCardHttpClient {

    private final String SERVER_URL = "http://localhost";

    private String creditCardEndpoint() {
        return SERVER_URL + ":" + port + "/credit-cards";
    }
    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    public int createNewCreditCard(final String creditCardId) {
        return restTemplate.postForEntity(creditCardEndpoint(),
                new CreateCreditCardRequest(creditCardId), Void.class)
            .getStatusCodeValue();
    }
}
