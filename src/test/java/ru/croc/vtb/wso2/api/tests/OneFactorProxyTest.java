package ru.croc.vtb.wso2.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import ru.croc.vtb.wso2.api.tests.service.ProxyTestHelper;
import ru.croc.vtb.wso2.api.tests.service.ProxyTestServiceImpl;

import java.io.IOException;

/**
 * @author Logvin I. N.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableConfigurationProperties({
        TestsProperties.class
})
//@ContextConfiguration(classes = {
//        Wso2ProxyConfig.class
//})
@TestPropertySource("/one-factor-test.properties")
public class OneFactorProxyTest {

//    @Autowired
//    private ProxyTestServiceImpl client;

    @Autowired
    private TestsProperties testsProperties;

    @Test
    public void oneFactorPositive() throws ProxyTestException {
        OkHttpClient client = new OkHttpClient();
        Request request = new ProxyTestHelper(this.testsProperties).createOkHttpRequest().build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            TokenDto dto = mapper.readValue(responseBody, TokenDto.class);
            Assert.assertNull("Exception type from AC: ", dto.getType());
            Assert.assertNotNull("idToken is null", dto.getIdToken());
        } catch (IOException e) {
            throw new ProxyTestException("Error occurred. ", e);
        }
    }
}
