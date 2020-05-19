package ru.croc.vtb.wso2.api.tests.service;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author Logvin I. N.
 */
@Getter
@Setter
public class ProxyTestHelper {

    public static final String PROXY_ENDPOINT = "/oauth2/token";

    public static final String X_DEVICE_FINGERPRINT = "X-Device-FingerPrint";

    public static final String BODY_SCOPE = "scope=%s";

    public static final String BODY_GRANT_TYPE = "grantType=%s";

    public static final String BODY_DEVICE_TOKEN_ID = "deviceTokenID=%s";

    public static final String BODY_SECURE_CODE = "secureCode=%s";

    public static final String BODY_CHALLENGE = "challenge=%s";

    private TestsProperties testsProperties;

    public ProxyTestHelper(TestsProperties testsProperties) {
        this.testsProperties = testsProperties;
    }

    public Request.Builder createOkHttpRequest() {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, buildBodyContent());
        return new Request.Builder()
                .url(testsProperties.getWso2Proxy().getUrlToProxy() + PROXY_ENDPOINT + "?scope=" + testsProperties.getWso2Proxy().getProxyBody().getScope())
                .method(HttpMethod.POST.name(), body)
                .addHeader(CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .addHeader(X_DEVICE_FINGERPRINT, testsProperties.getWso2Proxy().getMobileFingerPrint())
                .addHeader(AUTHORIZATION, createBasicAuthHeader());
    }

    private String buildBodyContent() {
        return String.format(BODY_GRANT_TYPE, testsProperties.getWso2Proxy().getProxyBody().getGrantType()) +
                "&" +
                String.format(BODY_DEVICE_TOKEN_ID, testsProperties.getWso2Proxy().getProxyBody().getDeviceTokenID()) +
                "&" +
                String.format(BODY_CHALLENGE, testsProperties.getWso2Proxy().getProxyBody().getChallenge()) +
                "&" +
                String.format(BODY_SECURE_CODE, testsProperties.getWso2Proxy().getProxyBody().getSecureCode()) +
                "&" +
                String.format(BODY_SCOPE, testsProperties.getWso2Proxy().getProxyBody().getScope());
    }

    private String createBasicAuthHeader() {
        String authParts = testsProperties.getWso2Proxy().getClientId() + ":" + testsProperties.getWso2Proxy().getClientSecret();
        String base64 = new String(Base64.getEncoder().encode(authParts.getBytes()), StandardCharsets.UTF_8);
        return "Basic " + base64;
    }
}
