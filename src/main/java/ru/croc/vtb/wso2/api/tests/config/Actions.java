package ru.croc.vtb.wso2.api.tests.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * <b>ДТО пропертей application.yaml</b>
 *
 * @author LogvinIN
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "actions")
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class Actions {

    private final List<DeviceToken> deviceTokens;

    private final List<GetSmsOtp> getSmsOtps;

    private final List<SmsOtp> smsOtps;

    public Actions(List<DeviceToken> deviceTokens, List<GetSmsOtp> getSmsOtps, List<SmsOtp> smsOtps) {
        this.deviceTokens = deviceTokens;
        this.getSmsOtps = getSmsOtps;
        this.smsOtps = smsOtps;
    }

    @Getter
    @Setter
    public static class DeviceToken {

        private String deviceTokenId;

        private Response response;
    }

    @Getter
    @Setter
    public static class GetSmsOtp {

        private String ucn;

        private Response response;
    }

    @Getter
    @Setter
    public static class SmsOtp {

        private String ucn;

        private Response response;
    }

    @Getter
    @Setter
    public static class Response {

        private String httpCode;

        private Body body;

        private ExceptionObject exceptionObject;
    }

    @Getter
    @Setter
    public static class Body {

        //devicetoken props
        private String id;

        private String domain;

        private String mobile;

        private boolean accountLock;

        private boolean disableSmsotp;

        private String username;

        private String homePhone;

        private String email;

        //getsmsotp props
        private String transactionId;
    }

    @Getter
    @Setter
    public static class ExceptionObject {

        private String exception;

        private String message;

        private int remainingPinAttempts;
    }
}
