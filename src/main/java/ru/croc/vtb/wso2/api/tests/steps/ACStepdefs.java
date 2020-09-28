package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.RequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.RequestService;

@CucumberContextConfiguration
@EnableConfigurationProperties(value = TestsProperties.class)
public class ACStepdefs {
    RequestService requestService = new RequestServiceImpl();

    @Autowired
    @Getter
    private TestsProperties testsProperties;

/*    @Before
    public void before() {
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);
    }*/

    @Then("Send Static password request with id: {string}")
    public void sendStaticPasswordRequestWithId(String arg0) {
        requestService.getStaticPasswordUserByIdResponse(arg0, testsProperties);
    }

    @Then("Send Restore Password Request id: {string}")
    public void sendRestorePasswordSuccess(String arg0) {
        requestService.sendRestorePasswordRequest(testsProperties, arg0);
    }

    @Then("Send GetSmsOtp Request id: {string}")
    public void sendGetSmsOtpRequestId(String arg0) {
        requestService.sendGetSmsOtpRequest(arg0, testsProperties);
    }

    @Then("Send SmsOtp Request id: {string}")
    public void sendSmsOtpRequestId(String arg0) {
        requestService.sendSmsOtpRequest(arg0, testsProperties);
    }

    @Then("Send Change Password Success Request")
    public void sendChangePasswordSuccessRequest() {
        requestService.changePasswordRequest(testsProperties);
    }

    @Then("Send Change Password Wrong Password Request")
    public void sendChangePasswordWrongPasswordRequest() {
        String password = "12312312321";
        requestService.changePasswordRequest(password, testsProperties);
    }

    @Then("Send GetUcnByAliasAndPhoneAndDomain Request Alias: {string}, Phone: {string}")
    public void sendGetUcnByAliasAndPhoneAndDomainRequestAliasPhone(String arg0, String arg1) {
        if (arg0.equals("null")) {
            arg0 = null;
        }
        if (arg1.equals("null")) {
            arg1 = null;
        }
        requestService.GetUcnByAliasAndPhoneAndDomainRequest(arg0, arg1, testsProperties);
    }

    @Then("Send DeviceToken Request id: {string}")
    public void sendDeviceTokenRequestId(String arg0) {
        requestService.deviceTokenRequest(arg0, testsProperties);
    }

    @Then("Send authenticateByClientId Request id: {string}")
    public void sendAuthenticateByClientIdRequestId(String arg0) {
        requestService.authenticateByClientIdRequest(arg0, testsProperties);
    }

    @Then("Send GetUserDiscredited Request id: {string}")
    public void sendGetUserDiscreditedRequestId(String arg0) {
        requestService.getUserDiscreditedRequest(arg0, testsProperties);
    }

    @Then("Send CheckRemotePasswordRestore Request id: {string}")
    public void sendCheckRemotePasswordRestoreRequestId(String arg0) {
        requestService.getCheckRemotePasswordRestoreRequest(arg0, testsProperties);
    }
}
