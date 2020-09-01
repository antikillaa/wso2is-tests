package ru.croc.vtb.wso2.api.tests.services;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;

import java.util.Map;

public interface BodyService {
    RestorePasswordDTO getRestorePasswordBody(TestsProperties testsProperties);

    Map<String, Object> getStaticPasswordBody(String id);

    Map<String, Object> getLoginByGrandTypeRequestBody(String grandType, String id, String id_type);

    Map<String, Object> getLoginByGrandTypeRequestBody(Map par);

    Map<String, Object> getGetSmsOtpRequestBody(String id);

    Map<String, Object> getSmsOtpRequestBody(String id);

    Map<String, Object> getSecondFactorDeviceTokenRequestBody(String id, String sessionDataKey);

    Map<String, Object> getUcnByAliasAndPhoneAndDomainRequestBody(String alias, String phone);

    Map<String, Object> getAuthenticateByClientIdRequestBody(String id);

}
