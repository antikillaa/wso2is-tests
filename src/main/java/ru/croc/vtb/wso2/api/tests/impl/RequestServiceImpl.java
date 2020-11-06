package ru.croc.vtb.wso2.api.tests.impl;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.ChangePasswordDTO;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;
import ru.croc.vtb.wso2.api.tests.services.BodyService;
import ru.croc.vtb.wso2.api.tests.services.RequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class RequestServiceImpl implements RequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);


    BodyService bodyService = new BodyServiceImpl();

    public void sendRestorePasswordRequest(TestsProperties testsProperties, String env, String id) {
        String requestPath = "/authentication/restorePassword";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        RestorePasswordDTO restorePasswordDTO = bodyService.getRestorePasswordBody(testsProperties, id);

        ValidatableResponse r = getRestorePasswordResponse(restorePasswordDTO, URL);
        RUN_CONTEXT.put("responseBody", r);
    }

    public ValidatableResponse getStaticPasswordResponse(String id, String URL, TestsProperties testsProperties) {
        return given().log().everything(true)
                .body(bodyService.getStaticPasswordBody(id, testsProperties))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL).then().log().all(true);
    }

    public ValidatableResponse getStaticPasswordUserByIdResponse(String id, TestsProperties testsProperties) {
        String URL = "http://" + testsProperties.getAc_host() +
                testsProperties.getAcPortMock() + "/authentication/staticPassword";

        ValidatableResponse r = getStaticPasswordResponse(id, URL, testsProperties);
        RUN_CONTEXT.put("responseBody", r);
        return r;
    }

    private ValidatableResponse getRestorePasswordResponse(RestorePasswordDTO restorePasswordDTO, String URL) {
        return given().log().everything(true)
                .body(restorePasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL).then().log().all(true);
    }

    @Override
    public void changePasswordRequest(String env, String id, String password, TestsProperties testsProperties) {
        String requestPath = "/authentication/changePassword";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        for (int i = 1; i <= 4; i++) {
            int q = i - 1;

            ChangePasswordDTO changePasswordDTO = ChangePasswordDTO.builder()
                    .id(id)
                    .domain(testsProperties.getDomain())
                    .system("91000")
                    .password(i == 1 ? password : password + q)
                    .newPassword(i < 4 ? password + i : password)
                    .build();

            ValidatableResponse r = given().log().everything(true)
                    .body(changePasswordDTO)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .post(URL)
                    .then().log().all(true);
            RUN_CONTEXT.put("responseBody", r);
        }
    }

    @Override
    public void sendGetTokenDTORequest(Map par, TestsProperties testsProperties) {
        String URL = getLoginURL(par, testsProperties);

        RUN_CONTEXT.put("id", par.get("id"));
        RUN_CONTEXT.put("grandType", par.get("grandType"));
        RUN_CONTEXT.put("id_type", par.get("id_type"));
        RUN_CONTEXT.put("scope", par.get("scope"));

        Map<String, Object> body = bodyService.getLoginByGrandTypeRequestBody(par, testsProperties);
        RUN_CONTEXT.put("body", body);

        Map<String, Object> header = getLoginHeaderWithFinger(par, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .headers(header)
                .params(body)
                .contentType(ContentType.URLENC)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);

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

    @Override
    public void sendSecondFactorRequest(TestsProperties testsProperties) {
        String id = RUN_CONTEXT.get("id", String.class);
        ValidatableResponse firstFactor = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        Map bodyResponse = firstFactor.extract().as(Map.class);
        Map property = (Map) bodyResponse.get("additional_properties");

        Map<String, Object> body = bodyService.getSecondFactorDeviceTokenRequestBody(id, (String) property.get("sessionDataKey"));

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .contentType(ContentType.URLENC)
                .post(testsProperties.getUrlToProxyK3() + "/oauth2/token")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    public void sendGetSmsOtpRequest(String id, TestsProperties testsProperties) {
        Map<String, Object> body = bodyService.getGetSmsOtpRequestBody(id);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://172.31.0.90:8053/auth-service/authentication/getSmsOtp/authentication/getSmsOtp")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendSmsOtpRequest(String id, TestsProperties testsProperties) {
        Map<String, Object> body = bodyService.getSmsOtpRequestBody(id);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + testsProperties.getAc_host() + testsProperties.getAcPortMock() + "/authentication/smsOtp")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void GetUcnByAliasAndPhoneAndDomainRequest(String alias, String phone, String env, TestsProperties testsProperties) {
        String requestPath = "/authentication/getUcnByAliasOrPhone";
        Map<String, Object> body = bodyService.getUcnByAliasAndPhoneAndDomainRequestBody(alias, phone);
        String URL = getAcRequestUrl(requestPath, env, testsProperties);
        ValidatableResponse r = given().log().everything(true)
                .params(body)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void deviceTokenRequest(String arg0, String env, TestsProperties testsProperties) {
        String requestPath = "/deviceToken/";
        String URL = getAcRequestUrl(requestPath, env, testsProperties) + arg0;
        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void authenticateByClientIdRequest(String id, String env, TestsProperties testsProperties) {
        String requestPath = "/authentication/authenticateByClientId";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        Map<String, Object> body = bodyService.getAuthenticateByClientIdRequestBody(id);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    public void getUserDiscreditedRequest(String id, String env, TestsProperties testsProperties) {
        String requestPath = "/authentication/getUserDiscredited";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("id", id)
                .param("domain", "master")
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

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
    }

    @Override
    public void sendOtpRestorePasswordRequest(String arg0, String arg1, TestsProperties testsProperties) {
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
                .post(testsProperties.getRestorePasswordServiceUrl() + "/otp")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendOtpRestorePasswordRequest(TestsProperties testsProperties) {
        ValidatableResponse otpResponse = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        Map responseBody = otpResponse.extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();
        body.put("otp", "000000");
        body.put("transactionId", responseBody.get("transactionId"));
        body.put("ucn", responseBody.get("ucn"));

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(testsProperties.getRestorePasswordServiceUrl() + "/pwd")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void getCheckRemotePasswordRestoreRequest(String id, String env, TestsProperties testsProperties) {
        String requestPath = "/authentication/checkRemotePasswordRestore";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("id", id)
                .param("domain", "master")
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    private Map<String, Object> getLoginHeaderWithFinger(Map par, TestsProperties testsProperties) {
        Map<String, Object> body = new HashMap<>();
        body.put("Content-Type", "application/x-www-form-urlencoded");

        if (par.get("Authorization") == null) {
            body.put("Authorization", testsProperties.getAuthorization());
        } else if (par.get("Authorization").equals("k3")) {
            body.put("Authorization", testsProperties.getAuthorizationK3());
        } else if (par.get("Authorization").toString().contains("Basic")) {
            body.put("Authorization", par.get("Authorization"));
        } else
            LOGGER.error("Authorization is missing");

        try {
            if (par.get("finger_print").equals("true") || !par.get("finger_print").equals("false")) {
                if (par.get("finger_print").equals("true")) {
                    body.put("X-Device-FingerPrint", testsProperties.getMobileFingerPrint());
                }
                if (par.get("finger_print").equals("k3")) {
                    body.put("X-Device-FingerPrint", testsProperties.getMobileFingerprintK3());
                }
            }
        } catch (NullPointerException e) {
            LOGGER.error("finger_print is missing");
        }

        return body;
    }

    private String getAcRequestUrl(String requestPath, String env, TestsProperties testsProperties) {
        String url = null;
        switch (env) {
            case "k3":
                url = testsProperties.getAcTestHostK3();
                break;
            case "k4":
                url = testsProperties.getAcTestHostK4();
                break;
            case "k5":
                url = testsProperties.getAcTestHostK5();
                break;
            case "test":
                url = testsProperties.getAcTestHostTest();
                break;
        }
        return url + requestPath;
    }
}
