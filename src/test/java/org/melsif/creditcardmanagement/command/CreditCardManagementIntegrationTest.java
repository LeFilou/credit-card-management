package org.melsif.creditcardmanagement.command;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features/Withdrawal",
    plugin = {"pretty", "html:target/cucumber/Withdrawal"})
public class CreditCardManagementIntegrationTest {
}
