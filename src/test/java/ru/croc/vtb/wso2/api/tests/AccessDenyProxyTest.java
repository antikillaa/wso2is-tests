package ru.croc.vtb.wso2.api.tests;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.config.Wso2ProxyConfig;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;
import ru.croc.vtb.wso2.api.tests.service.ProxyTestServiceImpl;

/**
 * @author Logvin I. N.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties({
        TestsProperties.class
})
@ContextConfiguration(classes = {
        Wso2ProxyConfig.class
})
@TestPropertySource("/deny-test.properties")
@Ignore
public class AccessDenyProxyTest {

//    @Autowired
//    private ProxyTestServiceImpl client;

//    @Test
//    public void oneFactorPositive() throws ProxyTestException {
//        TokenDto dto = client.oneFactorPositive();
//        Assert.assertNull("Exception type from AC: ", dto.getType());
//        Assert.assertNotNull("idToken is null", dto.getIdToken());
//    }
}
