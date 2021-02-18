package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface PartnerSSORequestService {
    void sendPartnerSSOInitializationRequest(Map<String, String> param, TestsProperties testsProperties);
}
