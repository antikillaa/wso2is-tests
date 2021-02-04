package ru.croc.vtb.wso2.api.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty", "html:test-report/cucumber-report.html"},
        tags = "@wip"
)
public class RunCucumberTest {

    public static void main(String[] args) throws InitializationError {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
