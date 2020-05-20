package ru.croc.vtb.wso2.api.tests;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;
import ru.croc.vtb.wso2.api.tests.service.ProxyTestHelper;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Logvin I. N.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties({
        TestsProperties.class
})
@TestPropertySource("/two-factor-test.properties")
public class TwoFactorProxyTest {

    @Autowired
    private TestsProperties testsProperties;

    private ProxyTestHelper testHelper;

    @Before
    public void beforeTest() {
        this.testHelper = new ProxyTestHelper(this.testsProperties);
    }

    /**
     * <b>Проверяет, что прокся отдаёт ошибку, когда нужен второй фактор</b>
     *
     * @throws ProxyTestException
     */
    @Test
    public void twoFactorNegative() throws ProxyTestException {
        TokenDto dto = this.testHelper.executeRequest(null);
        assertThat(dto.getType(), CoreMatchers.is("second_factor_required"));
        assertNull("idToken is not null when error", dto.getIdToken());
    }

    /**
     * <b>Выполняет проверку кейса входа со вторым фактором</b>
     * @throws ProxyTestException
     */
    @Test
    public void twoFactorPositive() throws ProxyTestException {
        TokenDto dto = this.testHelper.executeRequest(null);
        Map<String, String> additionalProperties = dto.getAdditionalProperties();

        assertNull("idToken not null", dto.getIdToken());
        // ожидаем ошибку second_factor_required и sessionDataKey
        assertThat(dto.getType(), CoreMatchers.is("second_factor_required"));
        assertNotNull("Additional properties is null", additionalProperties);
        // передаем в новом запросе sessionDataKey
        String sessionDataKey = additionalProperties.get("sessionDataKey");
        assertNotNull("sessionDataKey is null", sessionDataKey);
        dto = this.testHelper.executeRequest("&sessionDataKey=" + sessionDataKey);
        assertNull("Has exception type in request: ", dto.getType());
        assertNotNull("idToken is not null when error", dto.getIdToken());
    }
}
