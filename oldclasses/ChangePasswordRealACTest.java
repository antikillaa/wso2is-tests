package ru.croc.vtb.wso2.api.tests.ac.real;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.rest.ac.ChangePasswordDTO;

import static io.restassured.RestAssured.given;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class ChangePasswordRealACTest extends ProxyTest {

    @Test
    public void changePassword() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/changePassword";

        String password = getTestsProperties().getUserPassword();

        for (int i = 1; i <= 4; i++) {
            int q = i - 1;

            ChangePasswordDTO changePasswordDTO = ChangePasswordDTO.builder()
                    .id(getTestsProperties().getIdPasswordTests())
                    .domain(getTestsProperties().getDomain())
                    .systemId("91000")
                    .password(i == 1 ? password : password + q)
                    .newPassword(i < 4 ? password + i : password)
                    .build();

            given()
                    .body(changePasswordDTO)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .post(URL)
                    .then()
                    .statusCode(204);
        }
    }

    @Test
    public void changePasswordWrongPassword() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/changePassword";

        String password = "12312321312321";

        ChangePasswordDTO changePasswordDTO = ChangePasswordDTO.builder()
                .id(getTestsProperties().getIdPasswordTests())
                .domain(getTestsProperties().getDomain())
                .systemId("91000")
                .password(password)
                .newPassword(password + password)
                .build();

        given()
                .body(changePasswordDTO)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL)
                .then()
                .statusCode(403);
    }

}
