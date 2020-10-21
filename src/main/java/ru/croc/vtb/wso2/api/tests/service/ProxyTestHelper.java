package ru.croc.vtb.wso2.api.tests.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;
import ru.croc.vtb.wso2.api.tests.config.TestsProperties;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.SpmActionCodes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;

/**
 * <b>Помощник работы с прокси-сервером</b>
 *
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

    /**
     * <b>Ответ СПМ на момент 25.05.2020</b>
     * <p>actionCode и actionName наполняется в тестах</p>
     */
    private static final String SPM_RESPONSE_BODY = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "  <soapenv:Body>" +
            "    <ns1:analyzeResponse xmlns:ns1=\"http://ws.csd.rsa.com\">" +
            "      <ns1:analyzeReturn xsi:type=\"ns1:AnalyzeResponse\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
            "        <ns1:deviceResult>" +
            "          <ns1:authenticationResult>" +
            "            <ns1:authStatusCode>SUCCESS</ns1:authStatusCode>" +
            "            <ns1:risk>0</ns1:risk>" +
            "          </ns1:authenticationResult>" +
            "          <ns1:callStatus>" +
            "            <ns1:statusCode>SUCCESS</ns1:statusCode>" +
            "            <ns1:statusDescription/>" +
            "          </ns1:callStatus>" +
            "          <ns1:deviceData>" +
            "            <ns1:bindingType>HARD_BIND</ns1:bindingType>" +
            "            <ns1:deviceTokenCookie>kCcwcWsfsrcYvzFOBXsQNnFBRICaXANY</ns1:deviceTokenCookie>" +
            "            <ns1:deviceTokenFSO>kCcwcWsfsrcYvzFOBXsQNnFBRICaXANY</ns1:deviceTokenFSO>" +
            "            <ns1:lookupLabel>No Label</ns1:lookupLabel>" +
            "          </ns1:deviceData>" +
            "        </ns1:deviceResult>" +
            "        <ns1:identificationData>" +
            "          <ns1:clientTransactionId>3351197773</ns1:clientTransactionId>" +
            "          <ns1:delegated>false</ns1:delegated>" +
            "          <ns1:orgName>RETAIL</ns1:orgName>" +
            "          <ns1:transactionId>6c1db968136643d3a68a0ed5e609373c</ns1:transactionId>" +
            "          <ns1:userName>PF19635152</ns1:userName>" +
            "          <ns1:userStatus>UNVERIFIED</ns1:userStatus>" +
            "          <ns1:userType>PERSISTENT</ns1:userType>" +
            "        </ns1:identificationData>" +
            "        <ns1:messageHeader>" +
            "          <ns1:apiType>DIRECT_SOAP_API</ns1:apiType>" +
            "          <ns1:requestType>ANALYZE</ns1:requestType>" +
            "          <ns1:timeStamp>2020-03-18 16:08:00.850</ns1:timeStamp>" +
            "          <ns1:version>7.0</ns1:version>" +
            "        </ns1:messageHeader>" +
            "        <ns1:statusHeader>" +
            "          <ns1:reasonCode>0</ns1:reasonCode>" +
            "          <ns1:reasonDescription>Operations were completed successfully</ns1:reasonDescription>" +
            "          <ns1:statusCode>200</ns1:statusCode>" +
            "        </ns1:statusHeader>" +
            "        <ns1:deviceManagementResponse>" +
            "          <ns1:acspAccountId>PF19635152@RETAIL</ns1:acspAccountId>" +
            "          <ns1:callStatus>" +
            "            <ns1:statusCode>SUCCESS</ns1:statusCode>" +
            "            <ns1:statusDescription/>" +
            "          </ns1:callStatus>" +
            "          <ns1:deviceData>" +
            "            <ns1:bindingType>HARD_BIND</ns1:bindingType>" +
            "            <ns1:lookupLabel>No Label</ns1:lookupLabel>" +
            "          </ns1:deviceData>" +
            "        </ns1:deviceManagementResponse>" +
            "        <ns1:riskResult>" +
            "          <ns1:riskScore>0</ns1:riskScore>" +
            "          <ns1:riskScoreBand>SCORE_BAND_0</ns1:riskScoreBand>" +
            "          <ns1:triggeredRule>" +
            "            <ns1:actionCode>%s</ns1:actionCode>" + //наполняется в тестах
            "            <ns1:actionName>%s</ns1:actionName>" + //наполняется в тестах
            "            <ns1:actionType>STRICT</ns1:actionType>" +
            "            <ns1:clientFactList/>" +
            "            <ns1:ruleId>вход в систему DevicePIN</ns1:ruleId>" +
            "            <ns1:ruleName>вход в систему DevicePIN</ns1:ruleName>" +
            "            <ns1:actionSubCode>AntiFraud</ns1:actionSubCode>" +
            "          </ns1:triggeredRule>" +
            "        </ns1:riskResult>" +
            "      </ns1:analyzeReturn>" +
            "    </ns1:analyzeResponse>" +
            "  </soapenv:Body>" +
            "</soapenv:Envelope>";

    private TestsProperties testsProperties;

    public ProxyTestHelper(TestsProperties testsProperties) {
        this.testsProperties = testsProperties;
    }

    /**
     * <b>Обращается к прокси-серверу</b>
     *
     * @param additionalBodyProps дополнительные параметры тела (см. {@link ProxyTestHelper#createOkHttpRequest(java.lang.String)})
     * @return инстанс {@link TokenDto}
     * @throws ProxyTestException
     */
    public TokenDto getTokenDto(String additionalBodyProps) throws ProxyTestException {
        OkHttpClient client = new OkHttpClient();
        Request request = createOkHttpRequest(additionalBodyProps).build();
        try {
            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            TokenDto tokenDto = mapper.readValue(responseBody, TokenDto.class);
            tokenDto.setHttpCode(response.code());
            return tokenDto;
        } catch (IOException e) {
            throw new ProxyTestException("Error occurred. ", e);
        }
    }

    /**
     * <b>Создает билдер OkHttp запроса</b>
     * <p>Наполняет хэдеры, УРЛ, HTTP метод обращения и тело запроса (см. {@link ProxyTestHelper#buildDeviceTokenBodyContent(java.lang.String)})</p>
     *
     * @param additionalBodyProps дополнительные параметры запроса
     * @return билдер OkHttp запроса
     */
    private Request.Builder createOkHttpRequest(String additionalBodyProps) {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, buildDeviceTokenBodyContent(additionalBodyProps));
        return new Request.Builder()
                .url(testsProperties.getUrlToProxyK3() + PROXY_ENDPOINT + "?scope=" + testsProperties.getScope())
                .method(HttpMethod.POST.name(), body)
                .addHeader(CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .addHeader(X_DEVICE_FINGERPRINT, testsProperties.getMobileFingerPrint())
                .addHeader(AUTHORIZATION, createBasicAuthHeader());
    }

    /**
     * <b>Создает тело OkHttp запроса в виде строки</b>
     * <p>Проставляет параметры в УРЛ</p>
     *
     * @param additionalBodyProps дополнительные параметры тела
     * @return строка-тело запроса (УРЛ с pathParams)
     */
    private String buildDeviceTokenBodyContent(String additionalBodyProps) {
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

    private String buildLoginUcnBodyContent(String additionalBodyProps) {
        String result = String.format(BODY_GRANT_TYPE, testsProperties.getGrantType()) +
                "&" +
                String.format(BODY_DEVICE_TOKEN_ID, testsProperties.getDeviceTokenID()) +
                "&" +
                String.format(BODY_CHALLENGE, testsProperties.getChallenge()) +
                "&" +
                String.format(BODY_SECURE_CODE, testsProperties.getSecureCode()) +
                "&" +
                String.format(BODY_SCOPE, testsProperties.getScope());
        return result;
    }


    /**
     * <b>Создает хэдер авторизации из clientId и clientSecret</b>
     *
     * @return хэдер авторизации
     */
    private String createBasicAuthHeader() {
        String authParts = testsProperties.getClientId() + ":" + testsProperties.getClientSecret();
        String base64 = new String(Base64.getEncoder().encode(authParts.getBytes()), StandardCharsets.UTF_8);
        return "Basic " + base64;
    }

    /**
     * <b>Подставляет в СОАП тело ответа СПМ результат проверки</b>
     *
     * @param spmActionCode результат проверки
     * @return СОАП тело ответа
     */
    public String buildSpmResponseMock(SpmActionCodes spmActionCode) {
        return String.format(SPM_RESPONSE_BODY, spmActionCode.name(), spmActionCode.name());
    }

    public Map<String, Object> getLoginByDeviceTokenBody(String deviceId) {
        Map<String, Object> bodyLogin = new HashMap<>();
        bodyLogin.put("deviceTokenID", deviceId);
        bodyLogin.put("secureCode", "123123");
        bodyLogin.put("challenge", "123123");
        bodyLogin.put("grant_type", "device_token");
        bodyLogin.put("scope", "openid");
        return bodyLogin;
    }
}
