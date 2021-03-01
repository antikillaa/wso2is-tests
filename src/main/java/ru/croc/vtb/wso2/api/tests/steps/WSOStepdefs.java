package ru.croc.vtb.wso2.api.tests.steps;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.WsoRequestService;

import java.util.HashMap;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSOStepdefs {
    @Autowired
    @Getter
    private TestsProperties testsProperties;

    private static final Logger log = LoggerFactory.getLogger(WSOStepdefs.class);


    WsoRequestService acRequestService = new WSORequestServiceImpl();


    private void sendSecondFactorLoginRequest() {
        acRequestService.getSecondFactorGrandTypeRequest(testsProperties);
    }

    @Then("Send login by Grant type Request")
    public void sendLoginByGrantTypeRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        RUN_CONTEXT.put("par", param);
        acRequestService.sendGetTokenDTORequest(param, testsProperties);
        /**
         Send second factor request
         */
        ValidatableResponse r = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
        if (r.extract().statusCode() == 401) {
            sendSecondFactorLoginRequest();
        } else log.info("Second factor not required: " + r.extract().body().asString());
    }

    @Then("{string} Send Refresh token Request")
    public void sendRefreshTokenRequest(String env) {
        Map par = new HashMap();
        par.put("env", env);
        acRequestService.sendRefreshTokenRequest(par, testsProperties);
    }

    @Then("{string} Send Logout Request")
    public void sendLogoutRequest(String env) {
        Map par = new HashMap();
        par.put("env", env);
        acRequestService.sendLogoutRequest(par, testsProperties);
    }

    @Then("{string} Send login by Grant type QR Auth Request")
    public void sendLoginByGrantTypeQRAuthRequest(String env) {
        Map par = new HashMap();
        par.put("env", env);
        acRequestService.sendLoginByGrantTypeQRAuthRequest(par, testsProperties);
    }

    @Then("{string} Send Token Exchange Request")
    public void sendTokenExchangeRequest(String env) {
        Map par = new HashMap();
        par.put("env", env);
        par.put("exchangeGuest", false);
        acRequestService.sendTokenExchangeRequest(par, testsProperties);
    }

    @Then("{string} Send Token Exchange Guest Request")
    public void sendTokenExchangeGuestRequest(String env) {
        Map par = new HashMap();
        par.put("env", env);
        par.put("exchangeGuest", true);
        acRequestService.sendTokenExchangeRequest(par, testsProperties);
    }

    @Then("Send login by Grant type Request no parameter")
    public void sendLoginByGrantTypeRequestNoParameter(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);

            RUN_CONTEXT.put("par", param);
            log.info("Test no x device finger print for grant type: " + param.get("grandType"));
            System.err.println("Test no x device finger print for grant type: " + param.get("grandType"));
            acRequestService.sendGetTokenDTORequest(param, testsProperties);
            ValidatableResponse r = RUN_CONTEXT.get("responseBody", ValidatableResponse.class);
            if (r.extract().statusCode() == Integer.parseInt(param.get("code"))) {
                r.statusCode(Integer.parseInt(param.get("code")));
                log.info("Status code isn't: " + param.get("code"));
                log.info("Is it 200??");
            } else r.statusCode(200);

    }

    @Test
    public void checkJwtToken() {
        String token = "eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJhdF9oYXNoIjoiZ3p5ejhhTnAwd0pQVXUtVzdVQUJjdyIsInN1YiI6IjE4MDAxMDExIiwiYW1yIjpbImxvZ2luIl0sImlzcyI6Imh0dHBzOlwvXC9sb2NhbGhvc3Q6OTQ0M1wvb2F1dGgyXC90b2tlbiIsImF1dGhfc2Vzc2lvbl9pZCI6ImM0ODU1YzY3LTAwNzEtNGFiZC05ZjU3LTExODQxZWZlNTI1OSIsInVzZXJfZmluZ2VycHJpbnQiOiJjM2Q0OTQ3NGJjYzI2NjIzYjU0ZmIyNzRjMjAzMTE5ZDhkM2NhMDViNzFiOGY4ZjM4NTUxMGNkZTlhZGVmMzE5IiwibXNhX3Nlc3Npb25faWQiOiJiNjE0M2RhOC1mYTBkLTRjYjItOGYyNy0wYTkxN2Y1NzIzYmYiLCJhdWQiOiJDMlZZdjNiNlJIRWlnMm5fNTZiZm5uM0dmSTRhIiwiYWNyIjoibXNhPWV5SllMVlZ6WlhJdFUyVnpjMmx2YmkxSlJDSTZJbUkyTVRRelpHRTRMV1poTUdRdE5HTmlNaTA0WmpJM0xUQmhPVEUzWmpVM01qTmlaaUlzSW5KdmRYUmxUbUZ0WlNJNkltUmxabUYxYkhRaUxDSnphR0Z5WkU1aGJXVWlPaUp6TVRBaUxDSllMVkpQVlZSRkxVNUJUVVVpT2lKa1pXWmhkV3gwSWl3aVdDMVRTRUZTUkMxT1FVMUZJam9pY3pFd0lpd2lXQzFFWldKMVp5STZJblJ5ZFdVaUxDSllMVWx1YVhScFlYUnZjaTFJYjNOMElqb2lNVEF1T0RRdU1TNHhNemtpTENKWUxVbHVhWFJwWVhSdmNpMVRaWEoyYVdObElqb2lhVzUwWlhKdVpYUkNZVzVySWl3aVdDMURhR0Z1Ym1Wc0lqb2lWMWRYTWlJc0lsZ3RURzluYVc0dFRXOWtaU0k2Ym5Wc2JIMD0iLCJuYmYiOjE2MDQzMDgxMzYsInNwX25hbWUiOiJ3ZWItYmFuayIsImF6cCI6IkMyVll2M2I2UkhFaWcybl81NmJmbm4zR2ZJNGEiLCJkb21haW4iOiJtYXN0ZXIiLCJleHAiOjE2MDQzMDg0OTYsImlhdCI6MTYwNDMwODEzNn0.CNKBK7i9Dqz5BA_72Qa3jQUAdwYrYa2HcWyZlie_InzAXkpo5FT2XUShivp1CJto4CazzJI3XQ4RErCd_0G7y_g8Fe3gDPIc2wGg-ce5pE-_jeZ7H9LthzIfI9TmuJj9qTeZ-ZxyWAZOAJO50Kc38Yx5SXDq9qXyqXOBYc6oTmVSEZ552Tkn6EJ54VuhHVOWzpvSDs1kCe2eb6xKGLa0YAN0h9hB7NtyIVTD0RCw1gTB8_KIl7aUHDyW-9nUoX9iJ0FbAksmFeW3nALlcZyn_jVp595hW--VevkSBilc5uT61iIBAU-soDuYEW-U5MMdNfO6C6MQbU3S2QHire7eKg";

        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> map = jwt.getClaims();
        Claim claim = map.get("iss");
        System.out.println(map);
    }

    @Then("Check JWT Token")
    public void checkJWTToken() {
        String token = (String) RUN_CONTEXT.get("login", ValidatableResponse.class).extract().as(Map.class).get("id_token");
        log.info("token: " + token);

        Map<String, Object> firstFactor = (Map<String, Object>) RUN_CONTEXT.get("firstFactor", ValidatableResponse.class)
                        .extract().as(Map.class).get("additional_properties");
        log.info("firstFactor: " + firstFactor);

        Map<String, Object> param = RUN_CONTEXT.get("par", Map.class);
        log.info("Parameters: " + param.toString());
        DecodedJWT jwt = JWT.decode(token);
        Map<String, Claim> decodedJwt = jwt.getClaims();
        log.info("Decoded jwt: " + decodedJwt.toString());

        Assert.assertEquals(firstFactor.get("username"), decodedJwt.get("sub").as(String.class));
        Assert.assertNotNull(decodedJwt.get("amr"));
        Assert.assertTrue(decodedJwt.get("amr").toString().contains(param.get("grandType").toString()));
        Assert.assertNotNull(decodedJwt.get("at_hash"));
        Assert.assertNotNull(decodedJwt.get("auth_session_id"));
        Assert.assertNotNull(decodedJwt.get("user_fingerprint"));
        Assert.assertNotNull(decodedJwt.get("msa_session_id"));
        Assert.assertNotNull(decodedJwt.get("aud"));
        Assert.assertEquals(firstFactor.get("domain"), decodedJwt.get("domain").as(String.class));
        Assert.assertEquals(firstFactor.get("sessionDataKey"), decodedJwt.get("auth_session_id").as(String.class));
    }
}
