package ru.croc.vtb.wso2.api.tests.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author Logvin I. N.
 */
@Getter
@Setter
public class ProxyTestServiceImpl implements ProxyTestService {

    public static final String PROXY_ENDPOINT = "/oauth2/token";

    public static final String X_DEVICE_FINGERPRINT = "X-Device-FingerPrint";

    public static final String BODY_SCOPE = "scope=%s";

    public static final String BODY_GRANT_TYPE = "grantType=%s";

    public static final String BODY_DEVICE_TOKEN_ID = "deviceTokenID=%s";

    public static final String BODY_SECURE_CODE = "secureCode=%s";

    public static final String BODY_CHALLENGE = "challenge=%s";

    private String urlToProxy;

    private String mobileFingerPrint;

    private String clientId;

    private String clientSecret;

    private String grantType;

    private String deviceTokenID;

    private String secureCode;

    private String challenge;

    private String scope;

    @Override
    public TokenDto oneFactorPositive() throws ProxyTestException {
        OkHttpClient client = new OkHttpClient();
        Request request = createOkHttpRequest().build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseBody, TokenDto.class);
        } catch (IOException e) {
            throw new ProxyTestException("Error occurred. ", e);
        }
    }

    private String buildBodyContent() {
        return String.format(BODY_GRANT_TYPE, getGrantType()) +
                "&" +
                String.format(BODY_DEVICE_TOKEN_ID, getDeviceTokenID()) +
                "&" +
                String.format(BODY_CHALLENGE, getChallenge()) +
                "&" +
                String.format(BODY_SECURE_CODE, getSecureCode()) +
                "&" +
                String.format(BODY_SCOPE, getScope());
    }

    private String createBasicAuthHeader() {
        String authParts = getClientId() + ":" + getClientSecret();
        String base64 = new String(Base64.getEncoder().encode(authParts.getBytes()), StandardCharsets.UTF_8);
        return "Basic " + base64;
    }

    @Override
    public void withSecondFactorPositive() {

    }

    @Override
    public void oneFactorNegative() {

    }

    @Override
    public void withTwoFactorPositive() {

    }

    private Request.Builder createOkHttpRequest() {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, buildBodyContent());
        return new Request.Builder()
                .url(getUrlToProxy() + PROXY_ENDPOINT + "?scope=" + getScope())
                .method(HttpMethod.POST.name(), body)
                .addHeader(CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .addHeader(X_DEVICE_FINGERPRINT, getMobileFingerPrint())
                .addHeader(AUTHORIZATION, createBasicAuthHeader());
    }
}
