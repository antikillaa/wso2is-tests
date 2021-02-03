package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface GuestOnboardingService {
    void sendNonClientCardsRequest(Map<String, String> par, TestsProperties testsProperties);
}
