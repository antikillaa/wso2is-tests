package ru.croc.vtb.wso2.api.tests.services;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

public interface RestorePasswordRequestService {

    void sendOtpRestorePasswordRequest(String arg0, String arg1, String env, TestsProperties testsProperties);

    void sendOtpRestorePasswordRequest(String arg0, TestsProperties testsProperties);

}
