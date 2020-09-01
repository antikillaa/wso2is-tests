package ru.croc.vtb.wso2.api.tests.ac.stub;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StaticPasswordStubTest extends ProxyTest {

    private Map<String, Object> getStaticPasswordBody(String id) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("domain", "master");
        body.put("password", "99999");
        return body;
    }

    private ValidatableResponse getStaticPasswordResponse(String id, String URL) {
        return given()
                .body(getStaticPasswordBody(id))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post(URL).then();
    }

    @Test
    public void staticPasswordSuccess1Stub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/staticPassword";

        getStaticPasswordResponse("11121", URL)
                .statusCode(200)
                .body("id", equalTo("11122"))
                .body("domain", equalTo("master"))
                .body("username", equalTo("onetwothree"))
                .body("homePhone", equalTo("5551234567"))
                .body("mobile", equalTo("9163742359"))
                .body("accountLock", equalTo(false))
                .body("disable_smsotp", equalTo(false))
                .body("passwordTemporary", equalTo(false));
    }

    @Test
    public void staticPasswordSuccess2Stub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/staticPassword";

        getStaticPasswordResponse("11122", URL)
                .statusCode(200)
                .body("id", equalTo("11122"))
                .body("domain", equalTo("master"))
                .body("username", equalTo("onetwothree"))
                .body("homePhone", equalTo("5551234567"))
                .body("mobile", equalTo("9163742359"))
                .body("accountLock", equalTo(false))
                .body("disable_smsotp", equalTo(false))
                .body("passwordTemporary", equalTo(false));
    }

    @Test
    public void staticPasswordUserNotFoundExceptionStub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/staticPassword";

        getStaticPasswordResponse("11123", URL)
                .statusCode(404)
                .body("exception", equalTo("UserNotFoundException"))
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void staticPasswordAuthenticationExceptionStub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/staticPassword";

        getStaticPasswordResponse("11124", URL)
                .statusCode(401)
                .body("exception", equalTo("AuthenticationException"))
                .body("message", equalTo("Ошибка проверки ОТП"));
    }

    @Test
    public void staticPasswordExceptionStub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/staticPassword";

        getStaticPasswordResponse("11125", URL)
                .statusCode(500)
                .body("exception", equalTo("Exception"))
                .body("message", equalTo("Ошибка"));
    }

    @Test
    public void staticPasswordUserLockedExceptionStub() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPortMock() + "/authentication/staticPassword";

        getStaticPasswordResponse("11126", URL)
                .statusCode(403)
                .body("exception", equalTo("UserLockedException"))
                .body("message", equalTo("USER_LOCKED"));
    }
}
