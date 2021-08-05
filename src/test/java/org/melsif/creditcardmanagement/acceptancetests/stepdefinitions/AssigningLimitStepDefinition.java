package org.melsif.creditcardmanagement.acceptancetests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.melsif.creditcardmanagement.acceptancetests.BaseIntegrationTest;
import org.melsif.creditcardmanagement.acceptancetests.commons.CreditCardHttpClient;
import org.melsif.creditcardmanagement.coreapi.CreditCardSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AssigningLimitStepDefinition extends BaseIntegrationTest {

    @Autowired
    private CreditCardHttpClient creditCardHttpClient;
    private String creditCardId;

    @Given("a new credit card with the id {string}")
    public void anExistingCreditCardWithTheId(String creditCardId) {
        this.creditCardId = creditCardId;
        final int responseCode = creditCardHttpClient.createNewCreditCard(creditCardId);
        assertThat(responseCode).isEqualTo(201);
    }

    @When("assigning a limit of {double} to it")
    public void assigningALimitOfToIt(double limit) {
        final int responseCode = creditCardHttpClient.assignALimit(creditCardId, new BigDecimal(limit));
        assertThat(responseCode).isEqualTo(204);
    }

    @Then("The credit card has a limit of {double}")
    public void theCreditCardHasALimitOf(double limit) {
        final CreditCardSummary creditCardSummary = creditCardHttpClient.getCreditCardSummary(creditCardId);
        assertThat(creditCardSummary).isNotNull();
        assertThat(creditCardSummary.getLimitAssigned()).isEqualByComparingTo(new BigDecimal(limit));
    }
}
