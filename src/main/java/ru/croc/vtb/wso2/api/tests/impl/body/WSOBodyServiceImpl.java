package ru.croc.vtb.wso2.api.tests.impl.body;

import org.apache.commons.lang3.RandomStringUtils;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.services.body.WSOBodyService;

import java.util.HashMap;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSOBodyServiceImpl implements WSOBodyService {

    @Override
    public Map<String, Object> getLoginByGrandTypeRequestBody(String grandType, String id, String id_type) {

        Map<String, Object> body = new HashMap<>();
        body.put(id_type, id);
        body.put("password", "99999");
        body.put("grant_type", grandType);
        body.put("x-finger-print", "123456");
        if (RUN_CONTEXT.get("scope", String.class).equals("true")) {
            body.put("scope", "openid");
        }
        return body;
    }

    public Map<String, Object> getLoginByGrandTypeRequestBody(Map par, TestsProperties testsProperties) {
        Map<String, Object> body = new HashMap<>();
        body.put("x-finger-print", "123456");
        if (par.get("grandType").equals("device_token")) {
            body.put("deviceTokenID", par.get("id"));
            body.put("secureCode", "123123");
            body.put("challenge", "123123");
            body.put("grant_type", "device_token");
            if (RUN_CONTEXT.get("scope", String.class).equals("true")) {
                body.put("scope", "openid");
            }
        }
        if (par.get("password") == null || par.get("password").equals("true")) {
            body.put("password", testsProperties.getUserPassword());
        }

        body.put("grant_type", par.get("grandType"));
        if (par.get("scope").equals("true")) {
            body.put("scope", "openid");
        }

        if (par.get("id") == null) {
            String phone = "79800" + RandomStringUtils.randomNumeric(6);
            RUN_CONTEXT.put("guestPhone", phone);
            RUN_CONTEXT.put("guestId", phone);
            body.put((String) par.get("id_type"), phone);
        } else
            body.put((String) par.get("id_type"), par.get("id"));
        return body;
    }

    @Override
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
}
