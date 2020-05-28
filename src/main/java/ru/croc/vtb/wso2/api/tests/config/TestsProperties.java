package ru.croc.vtb.wso2.api.tests.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <b>Компонент конфигурации приложения</b>
 *
 * @author Logvin I. N.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "test")
@PropertySource("classpath:/application.properties")
@Component
public class TestsProperties {

    @Value("${test.urlToProxy}")
    private String urlToProxy;

    @Value("${test.client-id}")
    private String clientId;

    @Value("${test.client-secret}")
    private String clientSecret;

    @Value("${test.mobileFingerprint}")
    private String mobileFingerPrint;

    @Value("${test.grantType}")
    private String grantType;

    @Value("${test.deviceTokenID}")
    private String deviceTokenID;

    @Value("${test.secureCode}")
    private String secureCode;

    @Value("${test.challenge}")
    private String challenge;

    @Value("${test.scope}")
    private String scope;

    @Value("${test.otp:not-implemented}")
    private String otp;
}
