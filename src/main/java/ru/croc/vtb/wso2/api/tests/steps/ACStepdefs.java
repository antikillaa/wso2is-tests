package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.request.AcRequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.request.AcRequestService;

import java.util.Map;

@CucumberContextConfiguration
@EnableConfigurationProperties(value = TestsProperties.class)
public class ACStepdefs {
    AcRequestService acRequestService = new AcRequestServiceImpl();

    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("Send Static password request with id: {string}")
    public void sendStaticPasswordRequestWithId(String arg0) {
        acRequestService.getStaticPasswordUserByIdResponse(arg0, testsProperties);
    }

    @Then("{string} Send Restore Password Request id: {string}")
    public void sendRestorePasswordSuccess(String env, String arg0) {
        acRequestService.sendRestorePasswordRequest(testsProperties, env, arg0);
    }


    @Then("Send SmsOtp Request id: {string}")
    public void sendSmsOtpRequestId(String arg0) {
        acRequestService.sendSmsOtpRequest(arg0, testsProperties);
    }

    @Then("{string} Send Change Password Success Request, id: {string} password: {string}")

    public void sendChangePasswordSuccessRequest(String env, String id, String password) {
        acRequestService.changePasswordRequest(env, id, password, testsProperties);
    }

    @Then("{string} Send GetUcnByAliasAndPhoneAndDomain Request Alias: {string}, Phone: {string}")
    public void sendGetUcnByAliasAndPhoneAndDomainRequestAliasPhone(String env, String arg0, String arg1) {
        if (arg0.equals("null")) {
            arg0 = null;
        }
        if (arg1.equals("null")) {
            arg1 = null;
        }
        acRequestService.GetUcnByAliasAndPhoneAndDomainRequest(arg0, arg1, env, testsProperties);
    }

    @Then("{string} Send DeviceToken Request id: {string}")
    public void sendDeviceTokenRequestId(String env, String arg0) {
        acRequestService.deviceTokenRequest(arg0, env, testsProperties);
    }

    @Then("{string} Send authenticateByClientId Request id: {string}")
    public void sendAuthenticateByClientIdRequestId(String env, String arg0) {
        acRequestService.authenticateByClientIdRequest(arg0, env, testsProperties);
    }

    @Then("{string} Send GetUserDiscredited Request id: {string}")
    public void sendGetUserDiscreditedRequestId(String env, String arg0) {
        acRequestService.getUserDiscreditedRequest(arg0, env, testsProperties);
    }

    @Then("{string} Send CheckRemotePasswordRestore Request id: {string}")
    public void sendCheckRemotePasswordRestoreRequestId(String env, String arg0) {
        acRequestService.getCheckRemotePasswordRestoreRequest(arg0, env, testsProperties);
    }

    @Then("{string} Send Add Guest Request")
    public void sendAddGuestRequest(String env) {
        acRequestService.sendAddGuestRequest(env, testsProperties);
    }

    @Then("Send User activateOrDeactivate request")
    public void sendUserActivateOrDeactivateRequest(DataTable par) {
        Map<String, String> param = par.asMaps().get(0);
        acRequestService.sendUserActivateOrDeactivateRequest(param, testsProperties);
    }
}
