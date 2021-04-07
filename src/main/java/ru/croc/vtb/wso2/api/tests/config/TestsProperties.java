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

    @Value("${test.urlToProxyTest}")
    private String urlToProxyTest;

    @Value("${test.urlToProxyK3}")
    private String urlToProxyK3;

    @Value("${test.urlToProxyK4}")
    private String urlToProxyK4;

    @Value("${test.urlToProxyK5}")
    private String urlToProxyK5;

    @Value("${test.urlToPartnerK3}")
    private String urlToPartnerK3;

    @Value("${test.restorePasswordServiceUrlK3}")
    private String restorePasswordServiceUrlK3;

    @Value("${test.restorePasswordServiceUrlK4}")
    private String restorePasswordServiceUrlK4;

    @Value("${test.restorePasswordServiceUrlK5}")
    private String restorePasswordServiceUrlK5;

    @Value("${test.qrServicePrivateUrlK3}")
    private String qrServicePrivateUrlK3;

    @Value("${test.qrServicePrivateUrlK4}")
    private String qrServicePrivateUrlK4;

    @Value("${test.qrServicePrivateUrlK3}")
    private String qrServicePrivateUrlK5;

    @Value("${test.qrServiceUrlTest}")
    private String qrServiceUrlTest;

    @Value("${test.qrServiceUrlK3}")
    private String qrServiceUrlK3;

    @Value("${test.qrServiceUrlK4}")
    private String qrServiceUrlK4;

    @Value("${test.qrServiceUrlK5}")
    private String qrServiceUrlK5;

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

    @Value("${test.authorizationAutoTest}")
    private String authorizationAutoTest;

    @Value("${test.authorizationIB}")
    private String authorizationIB;

    @Value("${test.authorizationIB}")
    private String authorizationMB;

    @Value("${test.authorizationSMB}")
    private String authorizationSMB;

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

    @Value("${test.acTestHostTest}")
    private String acTestHostTest;

    @Value("${test.acTestHostK3}")
    private String acTestHostK3;

    @Value("${test.acTestHostK4}")
    private String acTestHostK4;

    @Value("${test.acTestHostK5}")
    private String acTestHostK5;

    @Value("${test.iacTestHostK3}")
    private String iacTestHostK3;

    @Value("${test.iacTestHostK4}")
    private String iacTestHostK4;

    @Value("${test.iacTestHostK5}")
    private String iacTestHostK5;

    @Value("${test.iacTestHostTest}")
    private String iacTestHostTest;

    @Value("${test.cpkURLK3}")
    private String cpkURLK3;

    @Value("${test.guestOnboardingURLK3}")
    private String guestOnboardingURLK3;

    @Value("${test.ac.portMock}")
    private String acPortMock;

    @Value("${test.ac.port}")
    private String acPort;

    @Value("${test.wso.port}")
    private String wsoPort;

    @Value("${test.id}")
    private String userId;

    @Value("${test.id.passwordTests}")
    private String IdPasswordTests;

    @Value("${test.userPassword}")
    private String userPassword;


}
