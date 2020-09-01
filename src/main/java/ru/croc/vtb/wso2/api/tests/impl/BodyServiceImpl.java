package ru.croc.vtb.wso2.api.tests.impl;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;
import ru.croc.vtb.wso2.api.tests.services.BodyService;

import java.util.HashMap;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class BodyServiceImpl implements BodyService {

    public RestorePasswordDTO getRestorePasswordBody(TestsProperties testsProperties) {
        return RestorePasswordDTO.builder()
                .id(testsProperties.getUserId())
                .domain(testsProperties.getDomain())
                .systemId("98000")
                .mobilePhone(testsProperties.getPhone())
                .build();
    }

    public Map<String, Object> getStaticPasswordBody(String id) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("domain", "master");
        body.put("password", "99999");
        return body;
    }

    public Map<String, Object> getLoginByGrandTypeRequestBody(String grandType, String id, String id_type) {

        Map<String, Object> body = new HashMap<>();
        body.put(id_type, id);
        body.put("password", "99999");
        body.put("grant_type", grandType);
        if (RUN_CONTEXT.get("scope", String.class).equals("true")) {
            body.put("scope", "openid");
        }
        return body;
    }

    public Map<String, Object> getLoginByGrandTypeRequestBody(Map par) {
        Map<String, Object> body = new HashMap<>();
        if (par.get("grandType").equals("device_token")) {
            body.put("deviceTokenID", par.get("id"));
            body.put("secureCode", "123123");
            body.put("challenge", "123123");
            body.put("grant_type", "device_token");
            if (RUN_CONTEXT.get("scope", String.class).equals("true")) {
                body.put("scope", "openid");
            }
        } else
            body.put((String) par.get("id_type"), par.get("id"));
        if (par.get("password") == null || par.get("password").equals("true")) {
            body.put("password", "99999");
        }
        body.put("grant_type", par.get("grandType"));
        if (par.get("scope").equals("true")) {
            body.put("scope", "openid");
        }
        return body;
    }

    public Map<String, Object> getGetSmsOtpRequestBody(String id) {
        Map<String, Object> body = new HashMap();
        body.put("id", id);
        body.put("domain", "master");
        body.put("systemId", "98000");
        body.put("transactionType", "1");
        body.put("reuseIfActive", "true");
        return body;
    }

    public Map<String, Object> getSmsOtpRequestBody(String id) {
        Map<String, Object> body = new HashMap();
        body.put("id", id);
        body.put("domain", "master");
        body.put("systemId", "98000");
        body.put("secureCode", "285175");
        body.put("transactionId", "810981548596861820");
        body.put("transactionType", "1");
        return body;
    }

    public Map<String, Object> getSecondFactorDeviceTokenRequestBody(String id, String sessionDataKey) {
        Map<String, Object> body = new HashMap<>();
        body.put("deviceTokenID", id);
        body.put("secureCode", "123123");
        body.put("challenge", "123123");
        body.put("grant_type", "device_token");
        body.put("scope", "openid");
        body.put("otp", "123");
        body.put("sessionDataKey", sessionDataKey);
        return body;
    }

    public Map<String, Object> getUcnByAliasAndPhoneAndDomainRequestBody(String alias, String phone) {
        Map<String, Object> ucn = new HashMap<>();
        ucn.put("phone", phone);
        ucn.put("alias", alias);
        return ucn;
    }

    public Map<String, Object> getAuthenticateByClientIdRequestBody(String id) {
        Map<String, Object> ucn = new HashMap<>();
        ucn.put("id", id);
        ucn.put("domain", "master");
        return ucn;
    }
}
