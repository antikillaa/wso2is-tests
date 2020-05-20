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
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author Logvin I. N.
 */
@Getter
@Setter
public class ProxyTestHelper {

    private static final String PROXY_ENDPOINT = "/oauth2/token";

    private static final String X_DEVICE_FINGERPRINT = "X-Device-FingerPrint";

    private static final String BODY_SCOPE = "scope=%s";

    private static final String BODY_GRANT_TYPE = "grant_type=%s";

    private static final String BODY_DEVICE_TOKEN_ID = "deviceTokenID=%s";

    private static final String BODY_SECURE_CODE = "secureCode=%s";

    private static final String BODY_CHALLENGE = "challenge=%s";

    private static final String BODY_OTP = "otp=%s";

    private TestsProperties testsProperties;

    public ProxyTestHelper(TestsProperties testsProperties) {
        this.testsProperties = testsProperties;
    }

    public Request.Builder createOkHttpRequest(String additionalBodyProps) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, buildBodyContent(additionalBodyProps));
        return new Request.Builder()
                .url(testsProperties.getUrlToProxy() + PROXY_ENDPOINT + "?scope=" + testsProperties.getScope())
                .method(HttpMethod.POST.name(), body)
                .addHeader(CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .addHeader(X_DEVICE_FINGERPRINT, testsProperties.getMobileFingerPrint())
                .addHeader(AUTHORIZATION, createBasicAuthHeader());
    }

    private String buildBodyContent(String additionalBodyProps) {
        String otp = testsProperties.getOtp();
        String result = String.format(BODY_GRANT_TYPE, testsProperties.getGrantType()) +
                "&" +
                String.format(BODY_DEVICE_TOKEN_ID, testsProperties.getDeviceTokenID()) +
                "&" +
                String.format(BODY_CHALLENGE, testsProperties.getChallenge()) +
                "&" +
                String.format(BODY_SECURE_CODE, testsProperties.getSecureCode()) +
                "&" +
                String.format(BODY_SCOPE, testsProperties.getScope());
        if (!Objects.equals(otp, "null") && !Objects.isNull(otp) && !Objects.equals(otp, "${test.otp}")) {
            result += "&" + String.format(BODY_OTP, otp);
        }
        if (additionalBodyProps != null && !additionalBodyProps.trim().isEmpty()) {
            result += additionalBodyProps;
        }
        return result;
    }

    private String createBasicAuthHeader() {
        String authParts = testsProperties.getClientId() + ":" + testsProperties.getClientSecret();
        String base64 = new String(Base64.getEncoder().encode(authParts.getBytes()), StandardCharsets.UTF_8);
        return "Basic " + base64;
    }

    public TokenDto executeRequest(String additionalBodyProps) throws ProxyTestException {
        OkHttpClient client = new OkHttpClient();
        Request request = createOkHttpRequest(additionalBodyProps).build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(responseBody, TokenDto.class);
        } catch (IOException e) {
            throw new ProxyTestException("Error occurred. ", e);
        }
    }
}
