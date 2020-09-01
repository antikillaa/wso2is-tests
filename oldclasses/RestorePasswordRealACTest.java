package ru.croc.vtb.wso2.api.tests.ac.real;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.RestorePasswordDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class RestorePasswordRealACTest extends ProxyTest {

    @Test
    public void restorePasswordSuccess() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/restorePassword";

        RestorePasswordDTO restorePasswordDTO = RestorePasswordDTO.builder()
                .id(getTestsProperties().getUserId())
                .domain(getTestsProperties().getDomain())
                .systemId("98000")
                .mobilePhone(getTestsProperties().getPhone())
                .build();

        given()
                .body(restorePasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then()
                .statusCode(204);

        given()
                .get("http://" + getTestsProperties().getAc_host() + ":8090/SmsSender/SmsSenderLog?list=1")
                .then()
                .statusCode(200)
                .body(containsString(getTestsProperties().getPhone()));
    }

    @Test
    public void restorePasswordWrongPhone() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/restorePassword";

        String randomPhone = RandomStringUtils.randomNumeric(9);

        RestorePasswordDTO restorePasswordDTO = RestorePasswordDTO.builder()
                .id(getTestsProperties().getUserId())
                .domain(getTestsProperties().getDomain())
                .systemId("98000")
                .mobilePhone(randomPhone)
                .build();

        given()
                .body(restorePasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then().log().everything()
                .statusCode(204);

        given()
                .get("http://" + getTestsProperties().getAc_host() + ":8090/SmsSender/SmsSenderLog?list=1")
                .then()
                .statusCode(200)
                .body(containsString(randomPhone));
    }
}
