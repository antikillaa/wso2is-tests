package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.body.QRBodyServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.body.QRBodyService;
import ru.croc.vtb.wso2.api.tests.services.request.QRRequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class QRRequestServiceImpl implements QRRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QRRequestServiceImpl.class);

    QRBodyService acBodyService = new QRBodyServiceImpl();

    @Override
    public ValidatableResponse generateQR(String env, TestsProperties testsProperties) {
        String requestPath = "/generate-qr";
        String URL = getQRRequestUrl(requestPath, env, testsProperties);
        Map<String, Object> header = getQRHeaderWithFinger(testsProperties);
        header.put("Referer", URL);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .headers(header)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("qr", r);
        return r;
    }

    @Override
    public ValidatableResponse verifyQR(String env, TestsProperties testsProperties) {
        String requestPath = "/verify-qr";
        String URL = getQRRequestUrlPrivate(requestPath, env, testsProperties);

        Map qrResponse = RUN_CONTEXT.get("qr", ValidatableResponse.class).extract().as(Map.class);
        LOGGER.info(qrResponse.toString());

        Map loginResponse = RUN_CONTEXT.get("login", ValidatableResponse.class).extract().as(Map.class);
        LOGGER.info(loginResponse.toString());

        Map<String, Object> header = new HashMap<>();
        header.put("Authorization", "Bearer " + loginResponse.get("id_token"));
        header.put("Referer", URL);

        Map<String, Object> body = new HashMap<>();
        body.put("id", qrResponse.get("id"));

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .headers(header)
                .body(body)
                .cookies(RUN_CONTEXT.get("login", ValidatableResponse.class).extract().cookies())
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("qr", r);
        return r;
    }

    @Override
    public ValidatableResponse approveQR(String env, TestsProperties testsProperties) {
        String requestPath = "/approve-consent";
        String URL = getQRRequestUrlPrivate(requestPath, env, testsProperties);

        Map qrResponse = RUN_CONTEXT.get("qr", ValidatableResponse.class).extract().as(Map.class);
        LOGGER.info(qrResponse.toString());

        Map loginResponse = RUN_CONTEXT.get("login", ValidatableResponse.class).extract().as(Map.class);
        LOGGER.info(loginResponse.toString());

        Map<String, Object> header = new HashMap<>();
        header.put("Authorization", "Bearer " + loginResponse.get("id_token"));
        header.put("Referer", URL);

        Map<String, Object> body = new HashMap<>();
        body.put("id", qrResponse.get("id"));
        body.put("mobileJwt", loginResponse.get("id_token"));
        body.put("approved", Boolean.TRUE);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookies(RUN_CONTEXT.get("login", ValidatableResponse.class).extract().cookies())
                .headers(header)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("qr", r);
        return r;
    }

    private String getQRRequestUrl(String requestPath, String env, TestsProperties testsProperties) {
        String url = null;
        switch (env) {
            case "k3":
                url = testsProperties.getQrServiceUrlK3();
                break;
            case "k4":
                url = testsProperties.getQrServiceUrlK4();
                break;
            case "k5":
                url = testsProperties.getQrServiceUrlK5();
                break;
            case "test":
                url = testsProperties.getQrServiceUrlTest();
                break;
        }
        return url + requestPath;
    }

    private String getQRRequestUrlPrivate(String requestPath, String env, TestsProperties testsProperties) {
        String url = null;
        switch (env) {
            case "k3":
                url = testsProperties.getQrServicePrivateUrlK3();
                break;
            case "k4":
                url = testsProperties.getQrServicePrivateUrlK4();
                break;
            case "k5":
                url = testsProperties.getQrServicePrivateUrlK5();
                break;
            case "test":
                url = testsProperties.getQrServiceUrlTest();
                break;
        }
        return url + requestPath;
    }

    private Map<String, Object> getQRHeaderWithFinger(TestsProperties testsProperties) {
        Map<String, Object> header = new HashMap<>();
        header.put("Authorization", testsProperties.getAuthorization());
        header.put("x-finger-print", "123456");
        header.put("X-Device-FingerPrint", testsProperties.getMobileFingerPrint());
        header.put("Referer", "http://k3-vol-cch103v.msk.vtb24.ru/login");
        return header;
    }
}
