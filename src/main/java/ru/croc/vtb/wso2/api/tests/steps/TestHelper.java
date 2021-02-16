package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.*;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;
import static ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl.RESPONSE_BODY;

public class TestHelper {

    @Before
    public void beforeTest() {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @And("Status code response is: {string}")
    public void statusCodeResponseIs(String arg0) {
        ValidatableResponse r = RUN_CONTEXT.get(RESPONSE_BODY, ValidatableResponse.class);
        r.statusCode(Integer.parseInt(arg0));
    }

    @And("Response Body contains {string} equals {string}")
    public void bodyContainsEquals(String arg0, String arg1) {
        ValidatableResponse r = RUN_CONTEXT.get(RESPONSE_BODY, ValidatableResponse.class);

        if (arg1.equals("true") || arg1.equals("false")) {
            Boolean arg2 = Boolean.valueOf(arg1);
            r.body(arg0, equalTo(arg2));
        } else if (arg1.equals("null")) {
            r.body(arg0, equalTo(null));
        } else r.body(arg0, equalTo(arg1));
    }

    @And("Response Body contains {string} not equals {string}")
    public void bodyNotContainsEquals(String arg0, String arg1) {
        ValidatableResponse r = RUN_CONTEXT.get(RESPONSE_BODY, ValidatableResponse.class);
        r.body(arg0, not(arg1));
    }

    @And("Response Body contains key: {string}")
    public void bodyContainsKey(String arg0) {
        ValidatableResponse r = RUN_CONTEXT.get(RESPONSE_BODY, ValidatableResponse.class);
        r.body(arg0, notNullValue());
    }

    @And("Response Body don't contains key: {string}")
    public void bodyNotContainsKey(String arg0) {
        ValidatableResponse r = RUN_CONTEXT.get(RESPONSE_BODY, ValidatableResponse.class);
        r.body(arg0, not("ucn"));
    }
}
