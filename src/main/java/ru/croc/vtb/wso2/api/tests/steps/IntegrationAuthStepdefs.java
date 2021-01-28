package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.IntegrationAuthRequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.IntegrationAuthRequestService;

import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class IntegrationAuthStepdefs {

    IntegrationAuthRequestService integrationAuthRequestService = new IntegrationAuthRequestServiceImpl();
    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("Send Get user Integration-auth Request")
    public void sendGetUserIntegrationAuthRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        RUN_CONTEXT.put("getSmsOtp", param);
        integrationAuthRequestService.sendGetUserIntegrationAuthRequest(param, testsProperties);
    }

    @Then("Send Get Otp Integration-auth Request")
    public void sendGetOtpIntegrationAuthRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        RUN_CONTEXT.put("getSmsOtp", param);
        integrationAuthRequestService.sendGetOtpIntegrationAuthRequest(param, testsProperties);
    }
}
