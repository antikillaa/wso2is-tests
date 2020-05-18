package ru.croc.vtb.wso2.api.tests.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
@PropertySource("/one-factor.properties")
public class ApplicationProperties {

    private Wso2Proxy wso2Proxy;

    @Getter
    @Setter
    public static class Wso2Proxy {
        private String urlToProxy;
        private String clientId;
        private String clientSecret;
        private String mobileFingerPrint;
        private ProxyBody proxyBody;

        @Getter
        @Setter
        public static class ProxyBody {
            private String grantType;
            private String deviceTokenID;
            private String secureCode;
            private String challenge;
            private String scope;
        }
    }

}