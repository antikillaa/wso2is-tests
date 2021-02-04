package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.services.request.GuestOnboardingService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class GuestOnboardingServiceImpl implements GuestOnboardingService {
    private static final Logger log = LoggerFactory.getLogger(GuestOnboardingServiceImpl.class);


    @Override
    public void sendNonClientCardsRequest(Map<String, String> par, TestsProperties testsProperties) {
        String URL = getCpkURL(par, testsProperties) + "/user";

        Map<String, Object> body = new HashMap<>();
        body.put("phoneNumber", RUN_CONTEXT.get("guestPhone", String.class));

        Map property = (Map) RUN_CONTEXT.get("firstFactor", ValidatableResponse.class)
                .extract().as(Map.class).get("additional_properties");
        RUN_CONTEXT.put("x-unc", property.get("username"));

        log.info("getGuestParam firstFactor: " + property.toString() + property);

        ValidatableResponse r = given().log().everything(true)
                .header("X-UNC", property.get("username"))
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        log.info("sendNonClientCardsRequest: " + r.extract().body().asString());
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("nonClient", r);
    }

    @Override
    public void sendActivateNonClientRequest(Map<String, String> par, TestsProperties testsProperties) {
        String URL = getGuestOnboardingURL(par, testsProperties) + "/non-client/activate";

        Map nonClient = RUN_CONTEXT.get("nonClient", ValidatableResponse.class)
                .extract().as(Map.class);
        String ucn = RUN_CONTEXT.get("x-unc", String.class);

        Map<String, Object> body = getActivateNonClientBody(nonClient);

        ValidatableResponse r = given().log().everything(true)
                .header("X-UNC", ucn)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        log.info("sendActivateNonClientRequest: " + r.extract().body().asString());
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("activateNonClient", r);
    }

    @Override
    public void sendIsRegisteredRequest(Map par, TestsProperties testsProperties) {
        String ucn = RUN_CONTEXT.get("x-unc", String.class);

        String URL = getGuestOnboardingURL(par, testsProperties) + "/internal/" + ucn + "/is-registered";

        ValidatableResponse r = given().log().everything(true)
                .get(URL)
                .then().log().all(true);
        log.info("sendIsRegisteredRequest: " + r.extract().body().asString());
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendDeactivateRequest(Map param, TestsProperties testsProperties) {
        String ucn = RUN_CONTEXT.get("x-unc", String.class);

        String URL = getGuestOnboardingURL(param, testsProperties) + "/internal/" + ucn;

        ValidatableResponse r = given().log().everything(true)
                .delete(URL)
                .then().log().all(true);
        log.info("sendDeactivateRequest: " + r.extract().body().asString());
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendDeleteRequest(Map param, TestsProperties testsProperties) {
        String ucn = RUN_CONTEXT.get("x-unc", String.class);

        Map secondFactor = RUN_CONTEXT.get("login", ValidatableResponse.class).extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();
        body.put("token", secondFactor.get("id_token"));

        String URL = getGuestOnboardingURL(param, testsProperties) + "/non-client";

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .header("X-UNC", ucn)
                .body(body)
                .delete(URL)
                .then().log().all(true);
        log.info("sendDeleteRequest: " + r.extract().body().asString());
        RUN_CONTEXT.put("responseBody", r);
    }

    private Map<String, Object> getActivateNonClientBody(Map nonClient) {
        Map<String, Object> body = new HashMap<>();
        body.put("guid", nonClient.get("id"));
        body.put("phoneNumber", RUN_CONTEXT.get("guestPhone", String.class));
        body.put("firstName", "AutoUser_firstName");
        body.put("middleName", "AutoUser_middleName");
        body.put("lastName", "AutoUser_lastName");
        body.put("birthday", "2000-03-01");
        body.put("agree", true);
        return body;
    }


    private String getCpkURL(Map par, TestsProperties testsProperties) {
        String URL = null;
        if (par.get("env") != null) {
            switch (par.get("env").toString()) {
                case "k3":
                    URL = testsProperties.getCpkURLK3();
                    break;
                case "k4":
                    URL = null;
                    break;
                case "k5":
                    URL = null;
                    break;
            }
        }
        return URL;
    }

    private String getGuestOnboardingURL(Map par, TestsProperties testsProperties) {
        String URL = null;
        if (par.get("env") != null) {
            switch (par.get("env").toString()) {
                case "k3":
                    URL = testsProperties.getGuestOnboardingURLK3();
                    break;
                case "k4":
                    URL = null;
                    break;
                case "k5":
                    URL = null;
                    break;
            }
        }
        return URL;
    }
}
