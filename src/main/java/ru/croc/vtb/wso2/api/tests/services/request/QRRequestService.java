package ru.croc.vtb.wso2.api.tests.services.request;

import io.restassured.response.ValidatableResponse;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

public interface QRRequestService {

    ValidatableResponse generateQR(String env, TestsProperties testsProperties);

    ValidatableResponse verifyQR(String env, TestsProperties testsProperties);

    ValidatableResponse approveQR(String env, TestsProperties testsProperties);
}
