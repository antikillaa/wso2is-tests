package ru.croc.vtb.wso2.api.tests.services.request;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

public interface WsoRequestService {

    void getSecondFactorGrandTypeRequest(TestsProperties testsProperties);

    void sendSecondFactorRequest(TestsProperties testsProperties);

    void sendGetTokenDTORequest(Map dataTable, TestsProperties testsProperties);


}
