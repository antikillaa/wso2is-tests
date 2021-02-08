package ru.croc.vtb.wso2.api.tests.services.body;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface WSOBodyService {
    Map<String, Object> getLoginByGrandTypeRequestBody(Map par, TestsProperties testsProperties);

    Map<String, Object> getSecondFactorDeviceTokenRequestBody(String id, String sessionDataKey);

}
