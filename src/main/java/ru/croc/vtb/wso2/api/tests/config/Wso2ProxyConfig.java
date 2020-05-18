package ru.croc.vtb.wso2.api.tests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.croc.vtb.wso2.api.tests.service.ProxyTestService;
import ru.croc.vtb.wso2.api.tests.service.ProxyTestServiceImpl;

/**
 * @author Logvin I. N.
 */
@Configuration
public class Wso2ProxyConfig {

    private ApplicationProperties applicationProperties;

    public Wso2ProxyConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public ProxyTestService wso2Client() {
        ProxyTestServiceImpl client = new ProxyTestServiceImpl();
        client.setUrlToProxy(applicationProperties.getWso2Proxy().getUrlToProxy());
        client.setMobileFingerPrint(applicationProperties.getWso2Proxy().getMobileFingerPrint());
        client.setClientId(applicationProperties.getWso2Proxy().getClientId());
        client.setClientSecret(applicationProperties.getWso2Proxy().getClientSecret());
        client.setGrantType(applicationProperties.getWso2Proxy().getProxyBody().getGrantType());
        client.setDeviceTokenID(applicationProperties.getWso2Proxy().getProxyBody().getDeviceTokenID());
        client.setChallenge(applicationProperties.getWso2Proxy().getProxyBody().getChallenge());
        client.setSecureCode(applicationProperties.getWso2Proxy().getProxyBody().getSecureCode());
        client.setScope(applicationProperties.getWso2Proxy().getProxyBody().getScope());
        return client;
    }

}
