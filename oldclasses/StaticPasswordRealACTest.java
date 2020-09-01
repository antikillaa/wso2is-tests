package ru.croc.vtb.wso2.api.tests.ac.real;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.StaticPasswordDTO;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class StaticPasswordRealACTest extends ProxyTest {

    @Test
    public void staticPasswordSuccess() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/staticPassword";

        String id = getTestsProperties().getIdPasswordTests();

        StaticPasswordDTO staticPasswordDTO = StaticPasswordDTO.builder()
                .id(id)
                .domain(getTestsProperties().getDomain())
                .password(getTestsProperties().getUserPassword())
                .build();

        given()
                .body(staticPasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void staticPasswordWrongPasswordRealAC() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/staticPassword";

        String id = getTestsProperties().getIdPasswordTests();

        StaticPasswordDTO staticPasswordDTO = StaticPasswordDTO.builder()
                .id(id)
                .domain(getTestsProperties().getDomain())
                .password("11111111")
                .build();

        given()
                .body(staticPasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then()
                .statusCode(401)
                .body("message", equalTo("Ошибка аутентификации"))
                .body("exception", equalTo("AuthenticationException"));
    }

    @Test
    @Tag("AC")
    public void staticPasswordUserNotFoundrealAC() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/staticPassword";

        StaticPasswordDTO staticPasswordDTO = StaticPasswordDTO.builder()
                .id("11111111111111")
                .domain(getTestsProperties().getDomain())
                .password(getTestsProperties().getUserPassword())
                .build();

        given()
                .body(staticPasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"))
                .body("exception", equalTo("UserNotFoundException"));
    }
}
