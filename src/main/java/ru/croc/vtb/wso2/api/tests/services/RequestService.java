package ru.croc.vtb.wso2.api.tests.services;

import io.restassured.response.ValidatableResponse;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface RequestService {
    void sendPasswordRandomPhoneRequest(TestsProperties testsProperties);

    void sendRestorePasswordRequest(TestsProperties testsProperties);

    ValidatableResponse getStaticPasswordResponse(String id, String URL);

    ValidatableResponse getStaticPasswordUserByIdResponse(String id, TestsProperties testsProperties);

    void sendGetTokenDTORequest(Map dataTable, TestsProperties testsProperties);

    void sendGetSmsOtpRequest(String arg0, TestsProperties testsProperties);

    void sendSecondFactorRequest(TestsProperties testsProperties);

    void sendSmsOtpRequest(String arg0, TestsProperties testsProperties);

    void changePasswordRequest(TestsProperties testsProperties);

    void changePasswordRequest(String password, TestsProperties testsProperties);

    void GetUcnByAliasAndPhoneAndDomainRequest(String arg0, String arg1, TestsProperties testsProperties);

    void deviceTokenRequest(String arg0, TestsProperties testsProperties);

    void authenticateByClientIdRequest(String arg0, TestsProperties testsProperties);

    void getUserDiscreditedRequest(String arg0, TestsProperties testsProperties);

    void getSecondFactorGrandTypeRequest(TestsProperties testsProperties);

    void sendOtpRestorePasswordRequest(String arg0, String arg1, TestsProperties testsProperties);
}
