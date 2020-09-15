package ru.croc.vtb.wso2.api.tests.impl;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
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
import static org.hamcrest.CoreMatchers.containsString;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class RequestServiceImpl implements RequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);


    BodyService bodyService = new BodyServiceImpl();

    public void sendRestorePasswordRequest(TestsProperties testsProperties) {
        String URL = "http://" + testsProperties.getAc_host() +
                testsProperties.getAcPort() + "/authentication/restorePassword";

        RestorePasswordDTO restorePasswordDTO = bodyService.getRestorePasswordBody(testsProperties);

        ValidatableResponse r = getRestorePasswordResponse(restorePasswordDTO, URL);
        RUN_CONTEXT.put("restorePasswordResponse", r);

        given().log().everything(true)
                .get("http://" + testsProperties.getAc_host() + ":8090/SmsSender/SmsSenderLog?list=1")
                .then().log().all(true)
                .statusCode(200)
                .body(containsString(testsProperties.getPhone()));
    }

    public void sendPasswordRandomPhoneRequest(TestsProperties testsProperties) {
        String URL = "http://" + testsProperties.getAc_host() +
                testsProperties.getAcPort() + "/authentication/restorePassword";

        String randomPhone = RandomStringUtils.randomNumeric(9);

        RestorePasswordDTO restorePasswordDTO = bodyService.getRestorePasswordBody(testsProperties);
        restorePasswordDTO.setMobilePhone(randomPhone);

        ValidatableResponse r = getRestorePasswordResponse(restorePasswordDTO, URL);
        RUN_CONTEXT.put("restorePasswordResponse", r);


        given().log().everything(true)
                .get("http://" + testsProperties.getAc_host() + ":8090/SmsSender/SmsSenderLog?list=1")
                .then().log().all(true)
                .statusCode(200)
                .body(containsString(randomPhone));
    }

    public ValidatableResponse getStaticPasswordResponse(String id, String URL) {
        return given().log().everything(true)
                .body(bodyService.getStaticPasswordBody(id))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL).then().log().all(true);
    }

    public ValidatableResponse getStaticPasswordUserByIdResponse(String id, TestsProperties testsProperties) {
        String URL = "http://" + testsProperties.getAc_host() +
                testsProperties.getAcPortMock() + "/authentication/staticPassword";

        ValidatableResponse r = getStaticPasswordResponse(id, URL);
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
    public void changePasswordRequest(TestsProperties testsProperties) {

        String URL = "http://" + testsProperties.getAc_host() +
                testsProperties.getAcPort() + "/authentication/changePassword";

        String password = testsProperties.getUserPassword();

        for (int i = 1; i <= 4; i++) {
            int q = i - 1;

            ChangePasswordDTO changePasswordDTO = ChangePasswordDTO.builder()
                    .id(testsProperties.getIdPasswordTests())
                    .domain(testsProperties.getDomain())
                    .systemId("91000")
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
    public void changePasswordRequest(String password, TestsProperties testsProperties) {
        String URL = "http://" + testsProperties.getAc_host() +
                testsProperties.getAcPort() + "/authentication/changePassword";

        ChangePasswordDTO changePasswordDTO = ChangePasswordDTO.builder()
                .id(testsProperties.getIdPasswordTests())
                .domain(testsProperties.getDomain())
                .systemId("91000")
                .password(password)
                .newPassword(password + password)
                .build();

        ValidatableResponse r = given().log().everything(true)
                .body(changePasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendGetTokenDTORequest(Map par, TestsProperties testsProperties) {
        RUN_CONTEXT.put("id", par.get("id"));
        RUN_CONTEXT.put("grandType", par.get("grandType"));
        RUN_CONTEXT.put("id_type", par.get("id_type"));
        RUN_CONTEXT.put("scope", par.get("scope"));

        Map<String, Object> body = bodyService.getLoginByGrandTypeRequestBody(par);
        RUN_CONTEXT.put("body", body);

        Map<String, Object> header = getLoginHeaderWithFinger(par, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .headers(header)
                .params(body)
                .contentType(ContentType.URLENC)
                .post(testsProperties.getUrlToProxy() + "/oauth2/token")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);

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
                .post(testsProperties.getUrlToProxy() + "/oauth2/token")
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
    public void GetUcnByAliasAndPhoneAndDomainRequest(String alias, String phone, TestsProperties testsProperties) {
        Map<String, Object> body = bodyService.getUcnByAliasAndPhoneAndDomainRequestBody(alias, phone);
        ValidatableResponse r = given().log().everything(true)
                .params(body)
                .get("http://" + testsProperties.getAc_host() +
                        testsProperties.getAcPort() + "/authentication/getUcnByAliasOrPhone")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void deviceTokenRequest(String arg0, TestsProperties testsProperties) {
        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("http://" + testsProperties.getAc_host() +
                        testsProperties.getAcPort() + "/deviceToken/" +
                        arg0)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void authenticateByClientIdRequest(String id, TestsProperties testsProperties) {
        Map<String, Object> body = bodyService.getAuthenticateByClientIdRequestBody(id);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + testsProperties.getAc_host() + testsProperties.getAcPort() + "/authentication/authenticateByClientId")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    public void getUserDiscreditedRequest(String id, TestsProperties testsProperties) {
        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("id", id)
                .param("domain", "master")
                .get("http://" + testsProperties.getAc_host() +
                        testsProperties.getAcPort() + "/authentication/getUserDiscredited")
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void getSecondFactorGrandTypeRequest(TestsProperties testsProperties) {
        ValidatableResponse firstFactor = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        Map bodyResponse = firstFactor.extract().as(Map.class);
        Map property = (Map) bodyResponse.get("additional_properties");

        Map<String, Object> body = RUN_CONTEXT.get("body", Map.class);
        body.put("sessionDataKey", property.get("sessionDataKey"));
        body.put("transactionId", property.get("transactionId"));
        body.put("otp", "111");

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .contentType(ContentType.URLENC)
                .post(testsProperties.getUrlToProxy() + "/oauth2/token")
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
    public void getCheckRemotePasswordRestoreRequest(String id, TestsProperties testsProperties) {
        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("id", id)
                .param("domain", "master")
                .get("http://" + testsProperties.getAc_host() +
                        testsProperties.getAcPort() + "/authentication/checkRemotePasswordRestore")
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
}
