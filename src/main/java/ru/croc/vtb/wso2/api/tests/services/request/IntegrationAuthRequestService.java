package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface IntegrationAuthRequestService {

    void sendGetUserIntegrationAuthRequest(Map<String, String> par, TestsProperties testsProperties);

    void sendGetOtpIntegrationAuthRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendGetEntryIntegrationAuthRequest(Map<String, String> param, TestsProperties testsProperties);
}
