package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.PartnerSSORequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.PartnerSSORequestService;

import java.util.Map;

public class PartnerSSOStepdefs {
    PartnerSSORequestService partnerSSORequestService = new PartnerSSORequestServiceImpl();
    @Autowired
    @Getter
    private TestsProperties testsProperties;



    @Then("Send Partner SSO AUTHENTICATE Request")
    public void sendPartnerSSOInitializationRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        partnerSSORequestService.sendPartnerSSOAuthenticateRequest(param, testsProperties);
    }

    @Then("Send Partner SSO CHALLENGE Request")
    public void sendPartnerSSOCHALLENGERequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        partnerSSORequestService.sendPartnerSSOChallengeRequest(param, testsProperties);
    }

    @Then("Send Partner SSO INIT Request")
    public void sendPartnerSSOINITRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        partnerSSORequestService.sendPartnerSSOInitRequest(param, testsProperties);
    }
}
