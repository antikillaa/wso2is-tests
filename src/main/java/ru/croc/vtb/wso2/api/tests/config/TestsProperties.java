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
@PropertySource("classpath:/applicationK3.properties")
@Component
public class TestsProperties {

    @Value("${test.urlToProxyK3}")
    private String urlToProxyK3;

    @Value("${test.urlToProxyK4}")
    private String urlToProxyK4;

    @Value("${test.urlToProxyK5}")
    private String urlToProxyK5;

    @Value("${test.restorePasswordServiceUrl}")
    private String restorePasswordServiceUrl;

    @Value("${test.client-id}")
    private String clientId;

    @Value("${test.client-secret}")
    private String clientSecret;

    @Value("${test.mobileFingerprint}")
    private String mobileFingerPrint;

    @Value("${test.mobileFingerprintK3}")
    private String mobileFingerprintK3;

    @Value("${test.authorization}")
    private String authorization;

    @Value("${test.authorizationK3}")
    private String authorizationK3;

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

    @Value("${test.ac.host}")
    private String ac_host;

    @Value("${test.alias}")
    private String alias;

    @Value("${test.phone}")
    private String phone;

    @Value("${test.domain}")
    private String domain;

    @Value("${test.acTestHost}")
    private String acTestHost;

    @Value("${test.acTestHostK3}")
    private String acTestHostK3;

    @Value("${test.acTestHostK4}")
    private String acTestHostK4;

    @Value("${test.ac.portMock}")
    private String acPortMock;

    @Value("${test.ac.port}")
    private String acPort;

    @Value("${test.id}")
    private String userId;

    @Value("${test.id.passwordTests}")
    private String IdPasswordTests;

    @Value("${test.userPassword}")
    private String userPassword;
}
