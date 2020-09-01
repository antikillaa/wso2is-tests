package ru.croc.vtb.wso2.api.tests.ac.real;

import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class GetUcnByAliasAndPhoneAndDomainRealACTest extends ProxyTest {


    private Map<String, Object> getGetUcnBody() {
        Map<String, Object> ucn = new HashMap<>();
        ucn.put("phone", getTestsProperties().getPhone());
        ucn.put("alias", getTestsProperties().getAlias());
        return ucn;
    }

    private ValidatableResponse getGetUcnResponse(String URL, Map<String, Object> ucnBody) {
        return given()
                .params(ucnBody)
                .get(URL).then();
    }

    @Test
    public void getUcnByAliasAndPhoneAndDomain() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        String id = getTestsProperties().getUserId();
        Map<String, Object> ucnBody = getGetUcnBody();

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", equalTo(id));
    }

    @Test
    public void getUcnByPhoneAndDomain() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.replace("alias", null);

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);

        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnByAliasAndDomain() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.replace("phone", null);

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);

        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnByAliasAndPhone() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.replace("domain", null);

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);

        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnByAlias() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = new HashMap<>();
        ucnBody.put("alias", getTestsProperties().getAlias());

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnByPhone() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = new HashMap<>();
        ucnBody.put("phone", getTestsProperties().getPhone());

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnAnotherAlias() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = new HashMap<>();
        ucnBody.put("alias", 123);

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", not(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnAnotherPhone() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.replace("phone", "8962147487");

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnAliasNotExist() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = new HashMap<>();
        ucnBody.put("alias", "qwqer123123");

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(404);
        r.body("exception", equalTo("UserNotFoundException"));
        r.body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void getUcnPhoneNotExist() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.put("phone", "123456789456123");

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnPhoneNonUniqueNoAlias() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.put("phone", "8962147485");
        ucnBody.put("alias", null);

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(403);
        r.body("exception", equalTo("NonUniqueEntryFoundException"));
        r.body("message", equalTo("Найдено более 1 пользователя по условиям поиска"));
    }

    @Test
    public void getUcnPhoneNonUniqueAlias() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = getGetUcnBody();
        ucnBody.put("phone", "8962147485");

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
        r.body("id", equalTo(getTestsProperties().getUserId()));
        r.body("domain", equalTo(getTestsProperties().getDomain()));
    }

    @Test
    public void getUcnNoMobile() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = new HashMap<>();
        ucnBody.put("alias", "111");

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
    }

    @Test
    public void getUcnNoAlias() {
        String URL = "http://" + getTestsProperties().getAc_host() +
                getTestsProperties().getAcPort() + "/authentication/getUcnByAliasOrPhone";

        Map<String, Object> ucnBody = new HashMap<>();
        ucnBody.put("phone", "8962147483");

        ValidatableResponse r = getGetUcnResponse(URL, ucnBody);
        r.statusCode(200);
    }
}



