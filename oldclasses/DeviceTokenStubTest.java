package ru.croc.vtb.wso2.api.tests.ac.stub;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeviceTokenStubTest extends ProxyTest {

    private Map<String, Object> getDeviceTokenBody(String id) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("domain", "master");
        body.put("password", "99999");
        return body;
    }

    private ValidatableResponse getDeviceTokenResponse(String id, String URL) {
        return given()
                .param("id", id)
                .get(URL).then();
    }

    @Test
    public void deviceTokenUserNotFoundExceptionStub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/deviceToken";

        getDeviceTokenResponse("11123", URL)
                .statusCode(404)
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("Учетная запись не найдена"));
    }

}
