package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.body.AcBodyServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.body.AcBodyService;
import ru.croc.vtb.wso2.api.tests.services.request.RestorePasswordRequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;
import static ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl.RESPONSE_BODY;

public class RestorePasswordRequestServiceImpl implements RestorePasswordRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestorePasswordRequestServiceImpl.class);

    AcBodyService acBodyService = new AcBodyServiceImpl();

    @Override
    public void sendOtpRestorePasswordRequest(String arg0, String arg1, String env, TestsProperties testsProperties) {
        String requestPath = "/otp";
        String URL = getRestorePasswordUrlURL(env, requestPath, testsProperties);

        Map<String, Object> body = new HashMap<>();
        body.put("cred", arg0);
        body.put("type", arg1);

        Map<String, Object> header = new HashMap<>();
        header.put("User-Agent", "1");
        header.put("X-Forwarded-For", "localhost");

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .headers(header)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }

    @Override
    public void sendOtpRestorePasswordRequest(String env, TestsProperties testsProperties) {
        String requestPath = "/pwd";
        String URL = getRestorePasswordUrlURL(env, requestPath, testsProperties);

        ValidatableResponse otpResponse = RUN_CONTEXT.get(RESPONSE_BODY, ValidatableResponse.class);
        Map responseBody = otpResponse.extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();
        body.put("otp", "000000");
        body.put("transactionId", responseBody.get("transactionId"));
        body.put("ucn", responseBody.get("ucn"));

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put(RESPONSE_BODY, r);
    }


    private String getRestorePasswordUrlURL(String env, String requestPath, TestsProperties testsProperties) {
        String url = null;
        switch (env) {
            case "k3":
                url = testsProperties.getRestorePasswordServiceUrlK3();
                break;
            case "k4":
                url = testsProperties.getRestorePasswordServiceUrlK4();
                break;
            case "k5":
                url = testsProperties.getRestorePasswordServiceUrlK5();
                break;
        }
        return url + requestPath;
    }
}
