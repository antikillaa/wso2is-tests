package ru.croc.vtb.wso2.api.tests;

import org.junit.Assert;
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

/**
 * @author Logvin I. N.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties({
        TestsProperties.class
})
@TestPropertySource("/one-factor-test.properties")
public class OneFactorProxyTest {

    @Autowired
    private TestsProperties testsProperties;

    private ProxyTestHelper testHelper;

    @Before
    public void beforeTest() {
        this.testHelper = new ProxyTestHelper(this.testsProperties);
    }

    /**
     * <b>Проверяет кейс входа с одним фактором</b>
     *
     * @throws ProxyTestException
     */
    @Test
    public void oneFactorPositive() throws ProxyTestException {
        TokenDto dto = this.testHelper.executeRequest(null);
        // убедимся, что нет ошибок
        Assert.assertNull("Exception type from AC: ", dto.getType());
        Assert.assertNotNull("idToken is null", dto.getIdToken());
    }
}
