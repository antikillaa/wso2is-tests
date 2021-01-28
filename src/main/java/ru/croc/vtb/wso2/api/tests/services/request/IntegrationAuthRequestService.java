package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface IntegrationAuthRequestService {

    void sendGetUserIntegrationAuthRequest(Map<Object, Object> par, TestsProperties testsProperties);
}
