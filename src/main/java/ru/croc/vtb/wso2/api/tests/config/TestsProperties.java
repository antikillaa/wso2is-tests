package ru.croc.vtb.wso2.api.tests.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "test")
@Component
public class TestsProperties {

    private Wso2Proxy wso2Proxy;

    @Getter
    @Setter
    public static class Wso2Proxy {

        @Value("${test.wso2Proxy.urlToProxy}")
        private String urlToProxy;

        private String clientId;

        private String clientSecret;

        @Value("${test.wso2Proxy.mobileFingerPrint}")
        private String mobileFingerPrint;

        @Value("${test.wso2Proxy.proxyBody}")
        private ProxyBody proxyBody;

        @Getter
        @Setter
        public static class ProxyBody {

            @Value("${test.wso2Proxy.proxyBody.grantType}")
            private String grantType;

            @Value("${test.wso2Proxy.proxyBody.deviceTokenID}")
            private String deviceTokenID;

            @Value("${test.wso2Proxy.proxyBody.secureCode}")
            private String secureCode;

            @Value("${test.wso2Proxy.proxyBody.challenge}")
            private String challenge;

            @Value("${test.wso2Proxy.proxyBody.scope}")
            private String scope;

            private String otp;
        }
    }
}