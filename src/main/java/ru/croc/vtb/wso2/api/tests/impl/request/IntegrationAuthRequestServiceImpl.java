package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.services.request.IntegrationAuthRequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;
import static ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl.RESPONSE_BODY;

public class IntegrationAuthRequestServiceImpl implements IntegrationAuthRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationAuthRequestServiceImpl.class);

    @Override
    public void sendGetUserIntegrationAuthRequest(Map<String, String> par, TestsProperties testsProperties) {
        String requestPath = "/user";
        String URL = getIntegrationAuthRequestUrl(requestPath, par.get("env"), testsProperties);

        Map<String, Object> body = new HashMap<>();
        body.put("ucn", par.get("ucn"));
        body.put("domain", par.get("domain"));
        body.put("system", par.get("system"));

        ValidatableResponse r = given().log().everything(true)
                .params(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }

    @Override
    public void sendGetOtpIntegrationAuthRequest(Map<String, String> par, TestsProperties testsProperties) {
        String requestPath = "/authentication/" + par.get("url");
        String URL = getIntegrationAuthRequestUrl(requestPath, par.get("env"), testsProperties);

        Map<String, Object> body = new HashMap<>();
        body.put("id", par.get("id"));

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }

    @Override
    public void sendGetEntryIntegrationAuthRequest(Map<String, String> par, TestsProperties testsProperties) {
        String requestPath = "/get-entry";
        String URL = getIntegrationAuthRequestUrl(requestPath, par.get("env"), testsProperties);

        Map<String, Object> body = new HashMap<>();
        body.put("ucn", par.get("ucn"));
        body.put("domain", par.get("domain"));
        body.put("system", par.get("system"));
        body.put("type", 1);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }

    @Override
    public void sendGetDevicesIntegrationAuthRequest(Map<String, String> par, TestsProperties testsProperties) {
        String requestPath = "/devices/";
        String URL = getIntegrationAuthRequestUrl(requestPath, par.get("env"), testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL+par.get("device"))
                .then().log().all(true);
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }

    private String getIntegrationAuthRequestUrl(String requestPath, String env, TestsProperties testsProperties) {
        String url = null;
        switch (env) {
            case "k3":
                url = testsProperties.getIacTestHostK3();
                break;
            case "k4":
                url = testsProperties.getIacTestHostK4();
                break;
            case "k5":
                url = testsProperties.getIacTestHostK5();
                break;
            case "test":
                url = testsProperties.getIacTestHostTest();
                break;
        }
        return url + requestPath;
    }
}
