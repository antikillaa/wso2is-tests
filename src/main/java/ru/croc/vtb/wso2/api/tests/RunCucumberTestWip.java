package ru.croc.vtb.wso2.api.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/scenario",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@wip"
)
public class RunCucumberTestWip {
}
