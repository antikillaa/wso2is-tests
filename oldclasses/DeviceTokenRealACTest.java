package ru.croc.vtb.wso2.api.tests.ac.real;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class DeviceTokenRealACTest extends ProxyTest {
    @Test
    public void deviceTokenSuccess() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/deviceToken/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL + getTestsProperties().getDeviceTokenID())
                .then()
                .statusCode(200)
                .body("userId", equalTo(getTestsProperties().getUserId()))
                .body("domain", equalTo(getTestsProperties().getDomain()))
                .body("deviceTokenId", equalTo(getTestsProperties().getDeviceTokenID()));
    }

    @Test
    public void deviceTokenDeviceNotFound() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/deviceToken/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get(URL + "1111111")
                .then()
                .statusCode(404)
                .body("exception", equalTo("DeviceNotFoundException"))
                .body("message", equalTo("Устройство двухфакторной аутентификации не найдено"));
    }
}
