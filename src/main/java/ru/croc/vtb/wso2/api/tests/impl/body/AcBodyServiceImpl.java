package ru.croc.vtb.wso2.api.tests.impl.body;

import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;
import ru.croc.vtb.wso2.api.tests.services.body.AcBodyService;

import java.util.HashMap;
import java.util.Map;

public class AcBodyServiceImpl implements AcBodyService {

    public RestorePasswordDTO getRestorePasswordBody(TestsProperties testsProperties, String id) {
        return RestorePasswordDTO.builder()
                .id(id)
                .domain(testsProperties.getDomain())
                .systemId("98000")
                .mobilePhone(testsProperties.getPhone())
                .build();
    }

    public Map<String, Object> getStaticPasswordBody(String id, TestsProperties testsProperties) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("domain", "master");
        body.put("password", testsProperties.getUserPassword());
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

    public Map<String, Object> getAuthenticateByClientIdRequestBody(Map par) {
        Map<String, Object> ucn = new HashMap<>();
        ucn.put("id", par.get("id"));
        ucn.put("domain", par.get("domain"));
        return ucn;
    }
}
