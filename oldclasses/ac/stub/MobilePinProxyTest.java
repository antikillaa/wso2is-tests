package ru.croc.vtb.wso2.api.tests.ac.stub;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.restassured.http.ContentType;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.SpmActionCodes;
import ru.croc.vtb.wso2.api.tests.model.stubs.service.MockController;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

/**
 * <b>Тестирование прокси-сервера</b>
 *
 * @author Logvin I. N.
 */
@EnableConfigurationProperties({
        TestsProperties.class
})
public class MobilePinProxyTest extends ProxyTest {

    @Autowired
    private MockController mockController;

    @Before
    public void beforeTest() {
        super.setUp();
    }

    /**
     * <b>Проверяет кейс входа с одним фактором</b>
     * <p>Меняет ответ СПМ на {@link SpmActionCodes#ALLOW}</p>
     *
     * @throws ProxyTestException
     */
    @Test
    public void oneFactorPositive() throws Exception {
/*        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.ALLOW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223400");
        TokenDto dto = getTestHelper().getTokenDto(null);*/

        Map<String, Object> body = new HashMap<>();
        body.put("deviceTokenID", "0000900100000000223400");
        body.put("secureCode", "123123");
        body.put("challenge", "123123");
        body.put("grant_type", "device_token");
        body.put("scope", "openid");

        TokenDto dto = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic SGdMR1BzNmxOX2tORFplRERKWXFsODZmWTdzYTpEQldCem1nZDhzZVBDSldrZWRnZUV4ZmZXSklh")
                .params(body)
                .contentType(ContentType.URLENC)
                .post("http://172.31.0.104:8989/oauth2/token").as(TokenDto.class);

        // убедимся, что нет ошибок
        Assert.assertNull("Exception type from AC: ", dto.getType());
        Assert.assertNotNull("idToken is null", dto.getIdToken());
    }

    @Test
    public void oneFactorRefreshToken() throws Exception {
        Map<String, Object> bodyLogin = getTestHelper().getLoginByDeviceTokenBody("0000900100000000223400");

        TokenDto dto = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic SGdMR1BzNmxOX2tORFplRERKWXFsODZmWTdzYTpEQldCem1nZDhzZVBDSldrZWRnZUV4ZmZXSklh")
                .params(bodyLogin)
                .contentType(ContentType.URLENC)
                .post("http://172.31.0.104:8989/oauth2/token")
                .then().statusCode(200).extract()
                .as(TokenDto.class);

        // убедимся, что нет ошибок
        Assert.assertNull("Exception type from AC: ", dto.getType());
        Assert.assertNotNull("idToken is null", dto.getIdToken());

        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "refresh_token");
        body.put("refresh_token", dto.getRefreshToken());

        dto = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic SGdMR1BzNmxOX2tORFplRERKWXFsODZmWTdzYTpEQldCem1nZDhzZVBDSldrZWRnZUV4ZmZXSklh")
                .params(body)
                .contentType(ContentType.URLENC)
                .post(getTestsProperties().getUrlToProxy() + "/oauth2/token")
                .then().statusCode(200).extract()
                .as(TokenDto.class);
        // убедимся, что нет ошибок
        Assert.assertNull("Exception type from AC: ", dto.getType());
        Assert.assertNotNull("idToken is null", dto.getIdToken());
    }

    /**
     * <b>Выполняет проверку кейса входа со вторым фактором</b>
     * <p>Меняет ответ СПМ на {@link SpmActionCodes#REVIEW}</p>
     *
     * @throws ProxyTestException
     */
    @Test
    public void twoFactorPositive() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223400");

        TokenDto dto = getTestHelper().getTokenDto(null);
        Map<String, String> additionalProperties = dto.getAdditionalProperties();

        assertNull("idToken not null", dto.getIdToken());
        // ожидаем ошибку second_factor_required и sessionDataKey
        assertThat(dto.getType(), CoreMatchers.is("second_factor_required"));
        assertNotNull("Additional properties is null", additionalProperties);
        // передаем в новом запросе sessionDataKey
        String sessionDataKey = additionalProperties.get("sessionDataKey");
        assertNotNull("sessionDataKey is null", sessionDataKey);
        dto = getTestHelper().getTokenDto("&sessionDataKey=" + sessionDataKey);
        assertNull("Has exception type in request: ", dto.getType());
        assertNotNull("idToken is not null when error", dto.getIdToken());
    }

    /**
     * <b>Проверяет кейс отказа входа</b>
     * <p>Меняет ответ СПМ на {@link SpmActionCodes#DENY}</p>
     *
     * @throws ProxyTestException
     */
    @Test
    public void accessDenyPositive() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.DENY));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223401");

        TokenDto dto = getTestHelper().getTokenDto(null);
        Assert.assertThat(dto.getType(), CoreMatchers.is("spm_access_deny"));
        Assert.assertNull("idToken is not null when error", dto.getIdToken());
    }

    @Test
    public void getLog() throws JSchException, SftpException, IOException {
        SSHClient client = new SSHClient();
        client.addHostKeyVerifier(new PromiscuousVerifier());
        client.connect("172.31.0.104");
        client.authPassword("root", "P@ssw0rdvtb0");
        SFTPClient sftpClient = client.newSFTPClient();

        sftpClient.get("/usr/lib64/wso2/wso2is/5.10.0/repository/logs/wso2carbon.log", "src/main/resources/");

        sftpClient.close();
        client.disconnect();

        LocalDateTime now = LocalDateTime.now();
        String time = now.getHour() + ":" + now.getMinute();

        try {
            PrintWriter requests = new PrintWriter("src/main/resources/log.log");
            //           PrintWriter responses = new PrintWriter("wso2carbon2.log");
            PrintWriter currentLog = null;

            Scanner s = new Scanner(new File("src/main/resources/wso2carbon.log"));
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (line.startsWith("TID:") && line.contains("ERROR") && line.contains(time))
                    currentLog = requests;
                else if (currentLog != null)
                    currentLog.println(line);
            }

            requests.close();
            s.close();
        } catch (IOException ioex) {
            // handle exception...
        }
    }

    private Map<String, Object> getLoginHeader() {
        Map<String, Object> body = new HashMap<>();
        body.put("Content-Type", "application/x-www-form-urlencoded");
        body.put("Authorization", "Basic SGdMR1BzNmxOX2tORFplRERKWXFsODZmWTdzYTpEQldCem1nZDhzZVBDSldrZWRnZUV4ZmZXSklh");
        return body;
    }

    public Map<String, Object> getUcnLoginRequestBody(String id) {
        Map<String, Object> body = new HashMap<>();
        body.put("login", id);
        body.put("password", "99999");
        body.put("grant_type", "login");
        body.put("scope", "openid");
        return body;
    }
}
