package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.body.WSOBodyServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.body.WSOBodyService;
import ru.croc.vtb.wso2.api.tests.services.request.WsoRequestService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSORequestServiceImpl implements WsoRequestService {
    private static final Logger log = LoggerFactory.getLogger(WSORequestServiceImpl.class);

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
    public void sendGetTokenDTORequest(Map par, TestsProperties testsProperties) {
        String URL = getLoginURL(par, testsProperties);


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
        log.info("Send first factor request: " + r.extract().body().asString());
        RUN_CONTEXT.put("responseBody", r);
        RUN_CONTEXT.put("login", r);
        RUN_CONTEXT.put("firstFactor", r);

        try {
            Map property = (Map) r.extract().as(Map.class).get("additional_properties");
            RUN_CONTEXT.put("x-unc", property.get("username"));
        } catch (NullPointerException e) {
            log.error("Second factor for login not required?" + Arrays.toString(e.getStackTrace()));
            log.error("Response: " + r.extract().asString());
        }
    }

    @Override
    public void sendRefreshTokenRequest(Map env, TestsProperties testsProperties) {
        String URL = getLoginURL(env, testsProperties);

        Map property = RUN_CONTEXT.get("login", ValidatableResponse.class)
                .extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "refresh_token");
        body.put("scope", "openid");
        body.put("refresh_token", property.get("refresh_token"));

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .cookies(RUN_CONTEXT.get("login", ValidatableResponse.class).extract().cookies())
                .contentType(ContentType.URLENC)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendTokenExchangeRequest(Map env, TestsProperties testsProperties) {
        String URL = getLoginURL(env, testsProperties);

        Map par = RUN_CONTEXT.get("par", Map.class);

        Map property = RUN_CONTEXT.get("login", ValidatableResponse.class)
                .extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();

        if (env.get("exchangeGuest").equals(true)) {
            body.put("grant_type", "token_exchange_guest");
        } else {
            body.put("grant_type", "token_exchange");
        }

        body.put("scope", "openid");
        body.put("jwt", property.get("id_token"));
        body.put("redirectUri", "vtbinvest://PortfelHome");

        if (par.get("grandType").equals("guest_auth")) {
            body.put("grant_type", "token_exchange_guest");
        }

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(par, testsProperties))
                .params(body)
                .cookies(RUN_CONTEXT.get("login", ValidatableResponse.class).extract().cookies())
                .contentType(ContentType.URLENC)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendLogoutRequest(Map env, TestsProperties testsProperties) {
        String URLtemp = getLoginURL(env, testsProperties);
        String URL = StringUtils.remove(URLtemp, "oauth2/token") + "oidc/logout";

        Map property = RUN_CONTEXT.get("login", ValidatableResponse.class)
                .extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();
        body.put("id_token_hint", property.get("id_token"));

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .contentType(ContentType.URLENC)
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendLoginByGrantTypeQRAuthRequest(Map env, TestsProperties testsProperties) {
        String URL = getLoginURL(env, testsProperties);

        Map property = RUN_CONTEXT.get("login", ValidatableResponse.class)
                .extract().as(Map.class);

        Map<String, Object> body = new HashMap<>();
        body.put("grant_type", "qr_auth");
        body.put("jwt", property.get("id_token"));
        body.put("scope", "openid");
        body.put("User-Agent", "test");

        ValidatableResponse r = given().log().everything(true)
                .headers(getLoginHeaderWithFinger(RUN_CONTEXT.get("par", Map.class), testsProperties))
                .params(body)
                .cookies(RUN_CONTEXT.get("login", ValidatableResponse.class).extract().cookies())
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
            log.error("Authorization is missing");

        setFingerPrint(par, testsProperties, header);

        return header;
    }

    private void setFingerPrint(Map par, TestsProperties testsProperties, Map<String, Object> header) {
        try {
            if (par.get("finger_print").equals("true") || !par.get("finger_print").equals("false")) {
                if (par.get("finger_print").equals("true")) {
                    header.put("X-Device-FingerPrint", testsProperties.getMobileFingerPrint());
                }
                if (par.get("finger_print").equals("k3")) {
                    header.put("X-Device-FingerPrint", testsProperties.getMobileFingerprintK3());
                }
                if (par.get("finger_print").equals("no")) {
                    log.info("No X Device Finger Print!");
                }
                if (par.get("finger_print").equals("null")) {
                    header.put("X-Device-FingerPrint", null);
                }
            }
        } catch (NullPointerException e) {
            log.error("finger_print is missing");
        }

        if ((par.get("x_finger_print") == null)) {
            header.put("x-finger-print", "123123");
        }
    }

    private String getLoginURL(Map par, TestsProperties testsProperties) {
        String URL = null;
        if (par.get("env") != null) {
            switch (par.get("env").toString()) {
                case "k3":
                    URL = testsProperties.getUrlToProxyK3();
                    break;
                case "k4":
                    URL = testsProperties.getUrlToProxyK4();
                    break;
                case "k5":
                    URL = testsProperties.getUrlToProxyK5();
                    break;
                case "test":
                    URL = testsProperties.getUrlToProxyTest();
                    break;
            }
        }
        return URL + ":8989/oauth2/token";
    }

}
