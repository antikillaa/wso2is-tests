package ru.croc.vtb.wso2.api.tests;

import org.hamcrest.CoreMatchers;
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
@TestPropertySource("/deny-test.properties")
public class AccessDenyProxyTest {

    @Autowired
    private TestsProperties testsProperties;

    private ProxyTestHelper testHelper;

    @Before
    public void beforeTest() {
        this.testHelper = new ProxyTestHelper(this.testsProperties);
    }

    @Test
    public void twoFactorNegative() throws ProxyTestException {
        TokenDto dto = this.testHelper.executeRequest(null);
        Assert.assertThat(dto.getType(), CoreMatchers.is("generic_error"));
        Assert.assertNull("idToken is not null when error", dto.getIdToken());
    }
}
