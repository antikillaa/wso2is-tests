package ru.croc.vtb.wso2.api.tests.impl.request;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.services.request.PartnerSSORequestService;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class PartnerSSORequestServiceImpl implements PartnerSSORequestService {
    private static final Logger log = LoggerFactory.getLogger(PartnerSSORequestServiceImpl.class);
    public static final String CLIENT_ID = "clientId";
    public static final String REDIRECT_URI = "redirectUri";
    public static final String RESPONSE_TYPE = "responseType";
    public static final String SCOPE = "scope";
    public static final String STATE = "state";
    public static final String TYPE = "type";
    public static final String LOGIN = "login";
    public static final String ID_TYPE = "id_type";
    public static final String GRANT_TYPE = "grant_type";

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

        ValidatableResponse responseBody = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .cookies(responseBody.extract().cookies())
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendPartnerSSOInitRequest(Map<String, String> param, TestsProperties testsProperties) {
        String URL = getLoginURL(param, testsProperties);
        Map<String, Object> body = getInitBody(param);

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendPartnerSSOAuthCodeRequest(Map<String, String> param, TestsProperties testsProperties) throws URISyntaxException {
        String URL = getLoginURL(param, testsProperties);
        Map<String, Object> body = getAuthCodeBody(param);

        Map<String, Object> header = getAuthCodeBody(param);
        if (param.get("Authorization") != null) {
            header.put("Authorization", param.get("Authorization"));
        }

        ValidatableResponse r = given().log().everything(true)
                .body(body)
                .contentType(ContentType.JSON)
                .headers(header)
                .post(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    @Override
    public void sendPartnerSSOUserInfoRequest(Map<String, String> param, TestsProperties testsProperties) {
        String URL = getLoginURL(param, testsProperties);
        ValidatableResponse responseBody = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);

        Map<String, Object> header = new HashMap<>();
        header.put("x-finger-print", "ey");
        header.put("Authorization", "Bearer " + responseBody
                .extract().body().as(Map.class).get("access_token"));

        ValidatableResponse r = given().log().everything(true)
                .contentType(ContentType.JSON)
                .headers(header)
                .cookies(responseBody.extract().cookies())
                .get(URL)
                .then().log().all(true);
        RUN_CONTEXT.put("responseBody", r);
    }

    private Map<String, Object> getAuthCodeBody(Map<String, String> param) throws URISyntaxException {
        ValidatableResponse responseBody = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        log.info("ResponseBody: " + responseBody.extract().toString());
        String header = responseBody.extract().header("Location");
        log.info("Location header: " + header);
        String code = URLEncodedUtils.parse(new URI(header).getQuery(), StandardCharsets.UTF_8).get(0).getValue();

        Map<String, Object> body = new HashMap();
        getBodyParam(body, param, GRANT_TYPE);

        if (param.get("code") == null) {
            body.put("code", code);
        } else body.put("code", param.get("code"));

        return body;
    }

    private Map<String, Object> getInitBody(Map<String, String> param) {
        Map<String, Object> body = new HashMap();
        body.put("stage", "INIT");
        getOidc(param, body);
        return body;
    }

    private void getOidc(Map<String, String> param, Map<String, Object> body) {
        HashMap oidc = new HashMap();
        getBodyParam(oidc, param, CLIENT_ID);
        getBodyParam(oidc, param, RESPONSE_TYPE);
        getBodyParam(oidc, param, REDIRECT_URI);
        getBodyParam(oidc, param, SCOPE);
        getBodyParam(oidc, param, STATE);
        body.put("oidc", oidc);
    }

    private void getBodyParam(Map<String, Object> oidc, Map<String, String> param, String bodyParam) {
        if (param.get(bodyParam) != null) {
            if (param.get(bodyParam).equals("no")) {
                oidc.put(bodyParam, "");
            } else
                oidc.put(bodyParam, param.get(bodyParam));
        }
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
        getBodyParam(params, param, TYPE);
        getBodyParam(params, param, LOGIN);
        params.put("password", testsProperties.getUserPassword());

        body.put("params", params);
        getOidc(param, body);
        return body;
    }


    private String getLoginURL(Map par, TestsProperties testsProperties) {
        String URL = null;
        String path = (String) par.get("path");
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
        return URL + ":9090/oauth2/" + path;
    }


    @Test
    public void test() throws URISyntaxException, JSONException {
        String url = "http://google.com/?code=123&state=gnreokgb";
        URLEncodedUtils.parse(new URI(url).getQuery(), StandardCharsets.UTF_8)
                .forEach(pair -> log.info("{} {}", pair.getName(), pair.getValue()));
        List<NameValuePair> nameValuePairs = URLEncodedUtils.parse(new URI(url).getQuery(), StandardCharsets.UTF_8);
        System.out.println(url);
    }
}
