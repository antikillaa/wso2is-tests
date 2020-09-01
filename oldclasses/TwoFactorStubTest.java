package ru.croc.vtb.wso2.api.tests.ac.stub;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.SpmActionCodes;
import ru.croc.vtb.wso2.api.tests.model.stubs.service.MockController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@EnableConfigurationProperties({
        TestsProperties.class
})
public class TwoFactorStubTest extends ProxyTest {
    @Autowired
    private MockController mockController;

    @Before
    public void beforeTest() {
        super.setUp();
    }

    @Test
    public void twoFactorIncorrectPinException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223402");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "incorrect_pin");
        assertEquals(dto.getMessage(), "Неверный код доступа.");
        assertEquals(dto.getHttpCode(), 401);
    }

    @Test
    public void twoFactorDeviceTokenNotActiveException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223403");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "device_token_not_active");
        assertEquals(dto.getMessage(), "Произошел сбой. В целях безопасности авторизуйтесь повторно в ВТБ Онлайн.");
        assertEquals(dto.getHttpCode(), 403);
    }

    @Test
    public void twoFactorNoMorePinAttemptsException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223404");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "no_more_pin_attempts");
        assertEquals(dto.getMessage(), "Вы сбросили код доступа.");
        assertEquals(dto.getHttpCode(), 403);
    }

    @Test
    public void twoFactorGenericBusinessLogicException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223405");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "generic_business_logic");
        assertEquals(dto.getMessage(), "Возникла непредвиденная ошибка. Обратитесь в техподдержку Банка.");
        assertEquals(dto.getHttpCode(), 403);
    }

    @Test
    public void twoFactorUserLockedException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223406");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "user_locked");
        assertEquals(dto.getMessage(), "Для обеспечения безопасности Вам заблокирован вход в ВТБ Онлайн. Для восстановления доступа позвоните в техподдержку Банка или обратитесь в ближайшее  отделение банка.");
        assertEquals(dto.getHttpCode(), 403);
    }

    @Test
    public void twoFactorDeviceNotFoundException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223407");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "device_not_found");
        assertEquals(dto.getMessage(), "Произошел сбой. В целях безопасности авторизуйтесь повторно в ВТБ Онлайн.");
        assertEquals(dto.getHttpCode(), 404);
    }

    @Test
    public void twoFactorUserNotFoundException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223408");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "user_not_found");
        assertEquals(dto.getMessage(), "Возникла непредвиденная ошибка. Обратитесь в техподдержку Банка.");
        assertEquals(dto.getHttpCode(), 404);
    }

    @Test
    public void twoFactorDirectoryAccessException() throws ProxyTestException {
        mockController.setSoapResponse(getTestHelper().buildSpmResponseMock(SpmActionCodes.REVIEW));
        getTestHelper().getTestsProperties().setDeviceTokenID("0000900100000000223409");

        TokenDto dto = getTestHelper().getTokenDto(null);

        assertNull("idToken not null", dto.getIdToken());
        assertEquals(dto.getType(), "directory_access");
        assertEquals(dto.getMessage(), "Приложение временно недоступно. Попробуйте повторить попытку позже.");
        assertEquals(dto.getHttpCode(), 500);
    }

}
