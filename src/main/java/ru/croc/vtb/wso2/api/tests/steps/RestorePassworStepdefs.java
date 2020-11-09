package ru.croc.vtb.wso2.api.tests.steps;

import io.cucumber.java.en.Then;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.impl.RequestServiceImpl;
import ru.croc.vtb.wso2.api.tests.services.RequestService;

public class RestorePassworStepdefs {
    RequestService requestService = new RequestServiceImpl();

    @Autowired
    @Getter
    private TestsProperties testsProperties;

    @Then("{string} Send otpRestorePasswordRequest: cred: {string}, type: {string}")
    public void sendOtpRestorePasswordRequestCredType(String env, String arg0, String arg1) {
        requestService.sendOtpRestorePasswordRequest(arg0, arg1, env, testsProperties);
    }

    @Then("{string} Send pwdRestorePasswordRequest")
    public void sendPwdRestorePasswordRequest(String env) {
        requestService.sendOtpRestorePasswordRequest(env, testsProperties);
    }
}
