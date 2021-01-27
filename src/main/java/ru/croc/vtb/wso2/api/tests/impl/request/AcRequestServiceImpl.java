package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.body.AcBodyServiceImpl;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.ChangePasswordDTO;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;
import ru.croc.vtb.wso2.api.tests.services.body.AcBodyService;
import ru.croc.vtb.wso2.api.tests.services.request.AcRequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class AcRequestServiceImpl implements AcRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AcRequestServiceImpl.class);


    AcBodyService acBodyService = new AcBodyServiceImpl();

    public void sendRestorePasswordRequest(TestsProperties testsProperties, String env, String id) {
        String requestPath = "/authentication/restorePassword";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        RestorePasswordDTO restorePasswordDTO = acBodyService.getRestorePasswordBody(testsProperties, id);

        ValidatableResponse r = getRestorePasswordResponse(restorePasswordDTO, URL);
        RUN_CONTEXT.put("responseBody", r);
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
    public void GetUcnByAliasAndPhoneAndDomainRequest(String alias, String phone, String env, TestsProperties testsProperties) {
        String requestPath = "/authentication/getUcnByAliasOrPhone";
        Map<String, Object> body = acBodyService.getUcnByAliasAndPhoneAndDomainRequestBody(alias, phone);
        String URL = getAcRequestUrl(requestPath, env, testsProperties);
        ValidatableResponse r = given().log().everything(true)
                .params(body)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }



    @Override
    public void authenticateByClientIdRequest(Map par, TestsProperties testsProperties) {
        String requestPath = "/authentication/authenticateByClientId";
        String URL = getAcRequestUrl(requestPath, par.get("env").toString(), testsProperties);

        Map<String, Object> body = acBodyService.getAuthenticateByClientIdRequestBody(par);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    public void getUserDiscreditedRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/authentication/getUserDiscredited";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("id", param.get("id"))
                .param("domain", param.get("domain"))
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
    public void getCheckRemotePasswordRestoreRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/authentication/checkRemotePasswordRestore";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .param("id", param.get("id"))
                .param("domain", param.get("domain"))
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendAddGuestRequest(String env, TestsProperties testsProperties) {
        String requestPath = "/user/guest/add";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);
        String phone = "7" + RandomStringUtils.randomNumeric(9);
        RUN_CONTEXT.put("guestPhone", phone);

        Map<String, String> body = new HashMap<>();
        body.put("mobilePhone", phone);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("guestLogin", r);
    }

    @Override
    public void sendUserActivateOrDeactivateRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/user/activateOrDeactivate";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);
        String ucn;

        if (param.get("ucn") == null) {
            ucn = (String) RUN_CONTEXT.get("responseBody", ValidatableResponse.class).extract().as(Map.class).get("ucn");
        } else if (param.get("ucn").equals("guest")) {
            ucn = (String) RUN_CONTEXT.get("guestLogin", ValidatableResponse.class).extract().as(Map.class).get("ucn");
        } else {
            ucn = param.get("ucn");
        }

        Map<String, Object> body = new HashMap<>();
        body.put("ucn", ucn);
        body.put("domain", "guest");
        body.put("activated", param.get("activateOrDeactivate"));

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendActivatedRequest(String env, TestsProperties testsProperties) {
        String requestPath = "/user/activated";
        String URL = getAcRequestUrl(requestPath, env, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL + "/"
                        + RUN_CONTEXT.get("guestPhone", String.class))
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendGetUserRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/user";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);
        String UCN = param.get("ucn").equals("guest") ?
                (String) RUN_CONTEXT.get("guestLogin", ValidatableResponse.class)
                        .extract().body().as(Map.class).get("ucn") :
                param.get("ucn");
        URL = URL
                + "/" + param.get("domain")
                + "/" + UCN;

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendGetSmsOtpRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/authentication/getSmsOtp";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);

        Map<String, Object> body = new HashMap<>();
        body.put("id", param.get("ucn"));
        body.put("domain", param.get("domain"));
        body.put("systemId", "98000");
        body.put("transactionType", 1);
        body.put("reuseIfActive", Boolean.TRUE);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendSmsOtpRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/authentication/smsOtp";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);

        Map getSmsOtp = RUN_CONTEXT.get("getSmsOtp", Map.class);
        String transactionId =
                RUN_CONTEXT.get("responseBody", ValidatableResponse.class)
                        .extract().body().as(Map.class).get("transactionId").toString();

        Map<String, Object> body = new HashMap<>();
        body.put("id", getSmsOtp.get("ucn"));
        body.put("domain", getSmsOtp.get("domain"));
        body.put("secureCode", "000000");
        body.put("systemId", "98000");
        body.put("transactionId", transactionId);
        body.put("transactionType", 1);

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendStaticPasswordRequest(Map<String, String> param, TestsProperties testsProperties) {
        String requestPath = "/authentication/staticPassword";
        String URL = getAcRequestUrl(requestPath, param.get("env"), testsProperties);

        Map<String, Object> body = new HashMap<>();
        body.put("id", param.get("ucn"));
        body.put("domain", param.get("domain"));
        body.put("password", testsProperties.getUserPassword());

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(body)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
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
