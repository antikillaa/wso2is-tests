package ru.croc.vtb.wso2.api.tests.ac.stub;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class ACGetOtpSmsOtpStubTest extends ProxyTest {



    @Test
    public void testAcGetSmsOtpSuccess1() {
        Map body = getGetSmsOtpRequestBody("111222");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(200)
                .body("transactionId", equalTo("1111111111111"));
    }

    @Test
    public void testAcGetSmsOtpSuccess2() {
        Map body = getGetSmsOtpRequestBody("333444");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(200)
                .body("transactionId", equalTo("2222222222222"));
    }

    @Test
    public void testAcGetSmsOtpUserNotFoundException() {
        Map body = getGetSmsOtpRequestBody("444555");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"))
                .body("exception", equalTo("UserNotFoundException"));
    }

    @Test
    public void testAcGetSmsOtpUserLockedException() {
        Map body = getGetSmsOtpRequestBody("555666");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(403)
                .body("message", equalTo("USER_LOCKED"))
                .body("exception", equalTo("UserLockedException"));
    }

    @Test
    public void testAcGetSmsOtpAuthenticationException() {
        Map body = getGetSmsOtpRequestBody("666777");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(401)
                .body("message", equalTo("Ошибка проверки ОТП"))
                .body("exception", equalTo("AuthenticationException"));
    }

    @Test
    public void testAcGetSmsOtpImsiVerificationException() {
        Map body = getGetSmsOtpRequestBody("777888");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(403)
                .body("message", equalTo("Ошибка проверки IMSI"))
                .body("exception", equalTo("ImsiVerificationException"));
    }

    @Test
    public void testAcGetSmsOtpException() {
        Map body = getGetSmsOtpRequestBody("888999");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/getSmsOtp")
                .then()
                .statusCode(500)
                .body("message", equalTo("Ошибка"))
                .body("exception", equalTo("Exception"));
    }

    @Test
    public void testAcSmsOtpSuccess1() {
        Map body = getSmsOtpRequestBody("111222");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(200)
                .body("transactionId", equalTo("1111111111111"));
    }

    @Test
    public void testAcSmsOtpSuccess2() {
        Map body = getSmsOtpRequestBody("333444");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(200)
                .body("transactionId", equalTo("2222222222222"));
    }

    @Test
    public void testAcSmsOtpImsiVerificationException() {
        Map body = getSmsOtpRequestBody("777888");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(403)
                .body("message", equalTo("Ошибка проверки IMSI"))
                .body("exception", equalTo("ImsiVerificationException"));
    }

    @Test
    public void testAcSmsOtpUserNotFoundException() {
        Map body = getSmsOtpRequestBody("444555");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"))
                .body("exception", equalTo("UserNotFoundException"));
    }

    @Test
    public void testAcSmsOtpUserLockedException() {
        Map body = getSmsOtpRequestBody("555666");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(403)
                .body("message", equalTo("USER_LOCKED"))
                .body("exception", equalTo("UserLockedException"));
    }

    @Test
    public void testAcSmsOtpAuthenticationException() {
        Map body = getSmsOtpRequestBody("666777");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(401)
                .body("message", equalTo("Ошибка проверки ОТП"))
                .body("exception", equalTo("AuthenticationException"));
    }

    @Test
    public void testAcSmsOtpException() {
        Map body = getSmsOtpRequestBody("888999");

        given()
                .body(body)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .post("http://" + getTestsProperties().getAc_host() + getTestsProperties().getAcPortMock() + "/authentication/smsOtp")
                .then()
                .statusCode(500)
                .body("message", equalTo("Ошибка"))
                .body("exception", equalTo("Exception"));
    }
}
