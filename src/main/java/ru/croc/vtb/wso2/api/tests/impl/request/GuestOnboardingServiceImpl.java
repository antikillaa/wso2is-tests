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
        String URL = getURL(par, testsProperties) + "/user";

        Map<String, Object> body = new HashMap<>();
        body.put("phoneNumber", RUN_CONTEXT.get("guestPhone", String.class));

        Map property = (Map) RUN_CONTEXT.get("firstFactor", ValidatableResponse.class)
                .extract().as(Map.class).get("additional_properties");
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
    }

    private String getURL(Map par, TestsProperties testsProperties) {
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
}
