package ru.croc.vtb.wso2.api.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        features = "src/main/resources/features",
        plugin = {"pretty", "json:target/cucumber-report/cucumber.json"},
        tags = "not @wip and not @k3 and not @skip"
)
public class RunCucumberTest {
}
