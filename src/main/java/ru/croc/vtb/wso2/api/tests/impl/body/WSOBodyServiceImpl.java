package ru.croc.vtb.wso2.api.tests.impl.body;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.services.body.WSOBodyService;

import java.util.HashMap;
import java.util.Map;

import static ru.croc.vtb.wso2.api.tests.context.RunContext.RUN_CONTEXT;

public class WSOBodyServiceImpl implements WSOBodyService {
    private static final Logger log = LoggerFactory.getLogger(WSOBodyServiceImpl.class);

    public Map<String, Object> getLoginByGrandTypeRequestBody(Map par, TestsProperties testsProperties) {
        Map<String, Object> body = new HashMap<>();
        setXFingerPrint(body);

        setPassword(par, testsProperties, body);
        setGrandType(par, body);
        setId(par, body);
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

    private void setXFingerPrint(Map<String, Object> body) {
        body.put("x-finger-print", "123456");
    }

    private void setPassword(Map par, TestsProperties testsProperties, Map<String, Object> body) {
        if (par.get("password") == null || par.get("password").equals("true")) {
            body.put("password", testsProperties.getUserPassword());
        }
    }

    private void setGrandType(Map par, Map<String, Object> body) {
        if (par.get("grandType").equals("device_token")) {
            body.put("deviceTokenID", par.get("id"));
            body.put("secureCode", "123123");
            body.put("challenge", "123123");
            body.put("grant_type", "device_token");
            if (RUN_CONTEXT.get("scope", String.class).equals("true")) {
                body.put("scope", "openid");
            }
        }

        body.put("grant_type", par.get("grandType"));
        if (par.get("scope").equals("true")) {
            body.put("scope", "openid");
        }
    }

    private void setId(Map par, Map<String, Object> body) {
        if (par.get("id") == null) {
            String phone = "79800" + RandomStringUtils.randomNumeric(6);
            log.info("Guest Phone number: " + phone);
            RUN_CONTEXT.put("guestPhone", phone);
            RUN_CONTEXT.put("guestId", phone);
            body.put((String) par.get("id_type"), phone);
        } else
            body.put((String) par.get("id_type"), par.get("id"));
    }
}
