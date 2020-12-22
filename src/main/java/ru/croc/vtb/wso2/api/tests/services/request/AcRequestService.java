package ru.croc.vtb.wso2.api.tests.services.request;

import io.restassured.response.ValidatableResponse;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface AcRequestService {

    void sendRestorePasswordRequest(TestsProperties testsProperties, String env, String id);

    ValidatableResponse getStaticPasswordResponse(String id, String URL, TestsProperties testsProperties);

    ValidatableResponse getStaticPasswordUserByIdResponse(String id, TestsProperties testsProperties);

    void sendSmsOtpRequest(String arg0, TestsProperties testsProperties);

    void changePasswordRequest(String env, String id, String password, TestsProperties testsProperties);

    void GetUcnByAliasAndPhoneAndDomainRequest(String arg0, String arg1, String env, TestsProperties testsProperties);

    void deviceTokenRequest(String arg0, String env, TestsProperties testsProperties);

    void authenticateByClientIdRequest(String arg0, String env, TestsProperties testsProperties);

    void getUserDiscreditedRequest(String arg0, String env, TestsProperties testsProperties);

    void getCheckRemotePasswordRestoreRequest(String arg0, String env, TestsProperties testsProperties);

    void sendAddGuestRequest(String env, TestsProperties testsProperties);

    void sendUserActivateOrDeactivateRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendActivatedRequest(String env, TestsProperties testsProperties);
}
