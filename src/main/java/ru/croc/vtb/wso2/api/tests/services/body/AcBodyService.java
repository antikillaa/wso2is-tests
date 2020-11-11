package ru.croc.vtb.wso2.api.tests.services.body;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;

import java.util.Map;

public interface AcBodyService {
    RestorePasswordDTO getRestorePasswordBody(TestsProperties testsProperties, String id);

    Map<String, Object> getStaticPasswordBody(String id, TestsProperties testsProperties);

    Map<String, Object> getGetSmsOtpRequestBody(String id);

    Map<String, Object> getSmsOtpRequestBody(String id);

    Map<String, Object> getUcnByAliasAndPhoneAndDomainRequestBody(String alias, String phone);

    Map<String, Object> getAuthenticateByClientIdRequestBody(String id);

}
