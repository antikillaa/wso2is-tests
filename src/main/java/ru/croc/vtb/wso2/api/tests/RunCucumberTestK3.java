package ru.croc.vtb.wso2.api.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        publish = true,
        features = "classpath:features",
        plugin = {"pretty", "html:test-report/cucumber-report.html"},
        tags = "@k3 or @k4 or @k5 and not @ac"
)
public class RunCucumberTestK3 {
}
