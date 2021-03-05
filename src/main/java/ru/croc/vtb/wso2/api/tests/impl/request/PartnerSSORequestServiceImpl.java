package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.services.request.PartnerSSORequestService;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class PartnerSSORequestServiceImpl implements PartnerSSORequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerSSORequestServiceImpl.class);

    @Override
    public void sendPartnerSSOAuthenticateRequest(Map<String, String> param, TestsProperties testsProperties) {
        String URL = getLoginURL(param, testsProperties);
        Map<String, Object> body = getAuthenticateBody(param, testsProperties);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendPartnerSSOChallengeRequest(Map<String, String> param, TestsProperties testsProperties) {
        String URL = getLoginURL(param, testsProperties);
        Map<String, Object> body = getChallengeBody(param);

        ValidatableResponse AuthenticateRequest = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .cookies(AuthenticateRequest.extract().cookies())
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendPartnerSSOInitRequest(Map<String, String> param, TestsProperties testsProperties) {
        String URL = getLoginURL(param, testsProperties);
        Map<String, Object> body = getInitBody(param);

        ValidatableResponse response = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .cookies(response.extract().cookies())
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    private Map<String, Object> getInitBody(Map<String, String> param) {
        Map<String, Object> body = new HashMap();
        body.put("stage", "INIT");

        Map<String, Object> oidc = new HashMap();
        oidc.put("clientId", param.get("clientId"));
        oidc.put("responseType", "code");
        oidc.put("redirectUri", param.get("redirectUri"));
        oidc.put("scope", "openid");
        oidc.put("state", "fnnvjvn");
        body.put("oidc", oidc);

        return body;
    }

    private Map<String, Object> getChallengeBody(Map<String, String> param) {
        Map<String, Object> body = new HashMap();
        body.put("stage", "CHALLENGE");
        Map<String, Object> params = new HashMap();
        params.put("secureCode", param.get("secureCode"));
        body.put("params", params);
        return body;
    }

    private Map<String, Object> getAuthenticateBody(Map<String, String> param, TestsProperties testsProperties) {
        Map<String, Object> body = new HashMap();
        body.put("stage", "AUTHENTICATE");

        Map<String, Object> params = new HashMap();
        params.put("type", param.get("type"));
        params.put("login", param.get("id"));
        params.put("password", testsProperties.getUserPassword());
        body.put("params", params);

        Map<String, Object> oidc = new HashMap();
        oidc.put("clientId", param.get("clientId"));
        oidc.put("responseType", "code");
        oidc.put("redirectUri", param.get("redirectUri"));
        oidc.put("scope", "openid");
        oidc.put("state", "fnnvjvn");
        body.put("oidc", oidc);

        return body;
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
        return URL + ":9090/oauth2/authorize";
    }
}
