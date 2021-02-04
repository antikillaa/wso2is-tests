package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.GuestOnboardingServiceImpl;
import ru.croc.vtb.wso2.api.tests.impl.request.WSORequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.GuestOnboardingService;

import java.util.Map;

public class GuestOnboardingMyStepdefs {
    private static final Logger log = LoggerFactory.getLogger(WSORequestServiceImpl.class);
    GuestOnboardingService guestOnboardingService = new GuestOnboardingServiceImpl();
    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("Send non-client-cards Request")
    public void sendNonClientCardsRequest(DataTable par) {
        Map param = par.asMaps().get(0);
        guestOnboardingService.sendNonClientCardsRequest(param, testsProperties);
    }

    @Then("Activate Non Client Request")
    public void activateNonClientRequest(DataTable par) {
        Map param = par.asMaps().get(0);
        guestOnboardingService.sendActivateNonClientRequest(param, testsProperties);
    }

    @Then("Send is-registered request")
    public void sendIsRegisteredRequest(DataTable par) {
        Map param = par.asMaps().get(0);
        guestOnboardingService.sendIsRegisteredRequest(param, testsProperties);
    }

    @Then("Send deactivate request")
    public void sendDeactivateRequest(DataTable par) {
        Map param = par.asMaps().get(0);
        guestOnboardingService.sendDeactivateRequest(param, testsProperties);
    }
}
