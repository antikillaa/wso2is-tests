package ru.croc.vtb.wso2.api.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        features = "classpath:features",
        plugin = {"pretty", "html:test-report/cucumber-report.html"},
        tags = "not @wip and not @k3 and not @skip"
)
public class RunCucumberTest {
}
