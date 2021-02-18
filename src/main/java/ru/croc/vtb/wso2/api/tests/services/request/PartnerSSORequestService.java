package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface PartnerSSORequestService {
    void sendPartnerSSOAuthenticateRequest(Map<String, String> param, TestsProperties testsProperties);

    void sendPartnerSSOChallengeRequest(Map<String, String> param, TestsProperties testsProperties);
}
