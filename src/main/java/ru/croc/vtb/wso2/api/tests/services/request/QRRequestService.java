package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

public interface QRRequestService {

    void generateQR(String env, TestsProperties testsProperties);

    void verifyQR(String env, TestsProperties testsProperties);

    void approveQR(String env, TestsProperties testsProperties);

    void getQRStatusRequest(String env, TestsProperties testsProperties);
}
