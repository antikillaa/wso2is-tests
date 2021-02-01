package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.WsoRequestService;

import java.util.HashMap;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSOStepdefs {
    WsoRequestService acRequestService = new WSORequestServiceImpl();
    @Autowired
    @Getter
    private TestsProperties testsProperties;

private void sendSecondFactorLoginMBRequest() {
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
        sendSecondFactorLoginMBRequest();
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
}
