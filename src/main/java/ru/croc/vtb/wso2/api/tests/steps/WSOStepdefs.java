package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.WsoRequestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSOStepdefs {
    WsoRequestService acRequestService = new WSORequestServiceImpl();
    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("Send login by Grant type")
    public void sendLoginByGrantType(DataTable table) {
        System.out.println(table);
    }

    @Then("Send Second Factor login request")
    public void sendSecondFactorLoginRequest() {
        acRequestService.sendSecondFactorRequest(testsProperties);
    }

    @Then("Send Second Factor login by Grant type request")
    public void sendSecondFactorLoginMBRequest() {
        acRequestService.getSecondFactorGrandTypeRequest(testsProperties);
    }

    @Then("Send login by Grant type Request")
    public void sendLoginByGrantTypeRequest(DataTable dataTable) {
        List<Map<Object, Object>> par = dataTable.asMaps(String.class, String.class);
        RUN_CONTEXT.put("par", par.get(0));
        acRequestService.sendGetTokenDTORequest(par.get(0), testsProperties);
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
