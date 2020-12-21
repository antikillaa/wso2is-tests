package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.body.WSOBodyServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.body.WSOBodyService;
import ru.croc.vtb.wso2.api.tests.services.request.WsoRequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSORequestServiceImpl implements WsoRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WSORequestServiceImpl.class);

    WSOBodyService WSOBodyService = new WSOBodyServiceImpl();

    @Override
    public void getSecondFactorGrandTypeRequest(TestsProperties testsProperties) {
        ValidatableResponse firstFactor = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        Map bodyResponse = firstFactor.extract().as(Map.class);
        Map property = (Map) bodyResponse.get("additional_properties");

        String URL = getLoginURL((Map) RUN_CONTEXT.get("par"), testsProperties);

        Map<String, Object> body = RUN_CONTEXT.get("body", Map.class);
        body.put("sessionDataKey", property.get("sessionDataKey"));
        body.put("transactionId", property.get("transactionId"));
        body.put("x-finger-print", "123456");
        body.put("otp", "000000");

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .contentType(ContentType.URLENC)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("login", r);
    }

    @Override
    public void sendSecondFactorRequest(TestsProperties testsProperties) {
        String id = RUN_CONTEXT.get("id", String.class);
        ValidatableResponse firstFactor = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        Map bodyResponse = firstFactor.extract().as(Map.class);
        Map property = (Map) bodyResponse.get("additional_properties");

        Map<String, Object> body = WSOBodyService.getSecondFactorDeviceTokenRequestBody(id, (String) property.get("sessionDataKey"));

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .contentType(ContentType.URLENC)
                .post(testsProperties.getUrlToProxyK3() + "/oauth2/token")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("login", r);
    }

    @Override
    public void sendGetTokenDTORequest(Map par, TestsProperties testsProperties) {
        String URL = getLoginURL(par, testsProperties);

        RUN_CONTEXT.put("id", par.get("id"));
        RUN_CONTEXT.put("grandType", par.get("grandType"));
        RUN_CONTEXT.put("id_type", par.get("id_type"));
        RUN_CONTEXT.put("scope", par.get("scope"));

        Map<String, Object> body = WSOBodyService.getLoginByGrandTypeRequestBody(par, testsProperties);
        RUN_CONTEXT.put("body", body);

        Map<String, Object> header = getLoginHeaderWithFinger(par, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .headers(header)
                .params(body)
                .contentType(ContentType.URLENC)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("login", r);

    }

    private Map<String, Object> getLoginHeaderWithFinger(Map par, TestsProperties testsProperties) {
        Map<String, Object> header = new HashMap<>();
        header.put("Content-Type", "application/x-www-form-urlencoded");

        if (par.get("Authorization") == null) {
            header.put("Authorization", testsProperties.getAuthorization());
        } else if (par.get("Authorization").equals("k3")) {
            header.put("Authorization", testsProperties.getAuthorizationK3());
        } else if (par.get("Authorization").toString().contains("Basic")) {
            header.put("Authorization", par.get("Authorization"));
        } else
            LOGGER.error("Authorization is missing");

        try {
            if (par.get("finger_print").equals("true") || !par.get("finger_print").equals("false")) {
                if (par.get("finger_print").equals("true")) {
                    header.put("X-Device-FingerPrint", testsProperties.getMobileFingerPrint());
                }
                if (par.get("finger_print").equals("k3")) {
                    header.put("X-Device-FingerPrint", testsProperties.getMobileFingerprintK3());
                }
            }
        } catch (NullPointerException e) {
            LOGGER.error("finger_print is missing");
        }

        return header;
    }

    private String getLoginURL(Map par, TestsProperties testsProperties) {
        if (par.get("env") != null) {
            switch (par.get("env").toString()) {
                case "k4":
                    return testsProperties.getUrlToProxyK4();
                case "k5":
                    return testsProperties.getUrlToProxyK5();
                case "test":
                    return testsProperties.getUrlToProxyTest();
            }
        }
        return testsProperties.getUrlToProxyK3();
    }

}
