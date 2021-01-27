package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface AcRequestService {

    void sendRestorePasswordRequest(TestsProperties testsProperties, String env, String id);

    void changePasswordRequest(String env, String id, String password, TestsProperties testsProperties);

    void GetUcnByAliasAndPhoneAndDomainRequest(Map<String, String> par, TestsProperties testsProperties);

    void deviceTokenRequest(String arg0, String env, TestsProperties testsProperties);

    void authenticateByClientIdRequest(Map par, TestsProperties testsProperties);

    void getUserDiscreditedRequest(Map<String, String> param, TestsProperties testsProperties);

    void getCheckRemotePasswordRestoreRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendAddGuestRequest(String env, TestsProperties testsProperties);

    void sendUserActivateOrDeactivateRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendActivatedRequest(String env, TestsProperties testsProperties);

    void sendGetUserRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendGetSmsOtpRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendSmsOtpRequest(Map<String, String> arg0, TestsProperties testsProperties);

    void sendStaticPasswordRequest(Map<String, String> param, TestsProperties testsProperties);
}
