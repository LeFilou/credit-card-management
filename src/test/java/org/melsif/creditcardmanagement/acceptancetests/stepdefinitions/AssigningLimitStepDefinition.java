package org.melsif.creditcardmanagement.acceptancetests.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.melsif.creditcardmanagement.acceptancetests.BaseIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class AssigningLimitStepDefinition extends BaseIntegrationTest {

    @Given("a credit card of id {string} that just have been created")
    public void aCreditCardOfIdThatJustHaveBeenCreated(String creditCardId) {

    }

    @When("assigning a limit to it")
    public void assigningALimitToIt() {
    }

    @Then("The credit card has a limit")
    public void theCreditCardHasALimit() {
    }
}
