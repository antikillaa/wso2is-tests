package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import lombok.Getter;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.WsoRequestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;
import static ru.croc.vtb.wso2.api.tests.context.RunContext.valueOf;

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
        List<Map<String, String>> params = par.asMaps();

        for (Map<String, String> param : params) {
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
    }
}
