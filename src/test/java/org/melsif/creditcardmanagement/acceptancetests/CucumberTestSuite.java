package org.melsif.creditcardmanagement.acceptancetests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    plugin = {"pretty"},
    glue = {"org.melsif.creditcardmanagement.acceptancetests.commons"}
)
class CucumberTestSuite {}
