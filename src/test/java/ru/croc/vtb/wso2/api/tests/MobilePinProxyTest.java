package ru.croc.vtb.wso2.api.tests;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.SpmActionCodes;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.service.MockController;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * <b>Тестирование прокси-сервера</b>
 *
 * @author Logvin I. N.
 */
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
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.ALLOW));
        TokenDto dto = getTestHelper().executeRequest(null);
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
        TokenDto dto = getTestHelper().executeRequest(null);
        Map<String, String> additionalProperties = dto.getAdditionalProperties();

        assertNull("idToken not null", dto.getIdToken());
        // ожидаем ошибку second_factor_required и sessionDataKey
        assertThat(dto.getType(), CoreMatchers.is("second_factor_required"));
        assertNotNull("Additional properties is null", additionalProperties);
        // передаем в новом запросе sessionDataKey
        String sessionDataKey = additionalProperties.get("sessionDataKey");
        assertNotNull("sessionDataKey is null", sessionDataKey);
        dto = getTestHelper().executeRequest("&sessionDataKey=" + sessionDataKey);
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
        TokenDto dto = getTestHelper().executeRequest(null);
        Assert.assertThat(dto.getType(), CoreMatchers.is("spm_access_deny"));
        Assert.assertNull("idToken is not null when error", dto.getIdToken());
    }
}
