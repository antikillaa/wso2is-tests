package ru.croc.vtb.wso2.api.tests.model.stubs.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.croc.vtb.wso2.api.tests.config.Actions;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.DeviceTokenAuthentication;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.User;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.smsotp.GetSmsOtpRequest;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.smsotp.GetSmsOtpResponse;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.AuthDataRequestDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.AuthDataResponseDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.CrossRefDTO;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.PayloadDto;

import java.time.OffsetDateTime;
import java.util.*;

/**
 * <b>Контроллер заглушек</b>
 *
 * @author Logvin I. N.
 */
@RestController
public class MockController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Actions mockActions;

    @Getter
    @Setter
    private String soapResponse;

    @RequestMapping(value = "/authentication/deviceToken",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<User> deviceToken(@RequestBody DeviceTokenAuthentication body) throws ProxyTestException {

        Optional<Actions.DeviceToken> oDeviceToken = mockActions
                .getDeviceTokens()
                .stream()
                .filter(dt -> Objects.equals(dt.getDeviceTokenId(), body.getDeviceTokenId()))
                .findFirst();

        if (oDeviceToken.isPresent()) {
            Actions.DeviceToken dt = oDeviceToken.get();
            String mockHttpStatus = dt.getResponse().getHttpCode();
            Actions.Body mockBody = dt.getResponse().getBody();
            Actions.ExceptionObject mockException = dt.getResponse().getExceptionObject();
            if (mockBody != null) {
                if (mockException != null) {
                    throw new ProxyTestException("Body and exception simultaneously filled!");
                }
                User user = new User();
                user.setId(mockBody.getId());
                user.setDomain(mockBody.getDomain());
                user.setMobile(mockBody.getMobile());
                user.setAccountLock(mockBody.isAccountLock());
                user.setDisableSmsotp(mockBody.isDisableSmsotp());
                user.setUsername(mockBody.getUsername());
                user.setHomePhone(mockBody.getHomePhone());
                user.setEmail(mockBody.getEmail());
                return ResponseEntity.ok(user);
            } else {
                throw new ProxyTestException(mockHttpStatus, mockException.getException(), mockException.getMessage(), mockException.getRemainingPinAttempts());
            }
        }

        throw new ProxyTestException("Mock not found for deviceTokenId = " + body.getDeviceTokenId());
    }

    @RequestMapping(value = "/sessions",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<AuthDataResponseDto> createSessionUsingPOST(String userAgent, AuthDataRequestDto authData, String deviceFingerprint, String mobileSdkData) {
        AuthDataResponseDto dto = new AuthDataResponseDto();
        dto.setBranchBicForSPMSharding("organization bik");
        List<CrossRefDTO> crossRefDTOS = new ArrayList<>();
        crossRefDTOS.add(getCrossRef("cm.SystemInstance.IVR", "fvdsvgwe"));
        crossRefDTOS.add(getCrossRef("cm.SystemInstance.SPM", "TB9004759"));
        dto.setCrossReferences(crossRefDTOS);
        dto.setPayload(new PayloadDto()
                .msaSessionId(UUID.randomUUID().toString())
                .routeName("default")
                .shardName("shard1")
                .uniqueClientNumber("20008901")
        );
        return new ResponseEntity<AuthDataResponseDto>(dto, HttpStatus.OK);
    }

    private CrossRefDTO getCrossRef(String systemNumber, String externalId) {
        return new CrossRefDTO()
                .endDate(OffsetDateTime.now())
                .externalId(externalId)
                .partyUId(UUID.randomUUID().toString())
                .savedAt(OffsetDateTime.now())
                .systemNumber(systemNumber);
    }

    @RequestMapping(value = "/mockAsyncAdaptiveAuthenticationSoapBinding",
            method = RequestMethod.POST)
    public ResponseEntity<String> mockSoap() {
        String soapResponse = this.getSoapResponse();
        return new ResponseEntity<String>(soapResponse, HttpStatus.OK);
    }

    //todo когда потребуется, обработать getSmsOtp
    @RequestMapping(value = "/authentication/smsOtp",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<GetSmsOtpResponse> smsOtp(@RequestBody GetSmsOtpRequest body) throws ProxyTestException {
        Optional<Actions.SmsOtp> oSmsOtp = mockActions
                .getSmsOtps()
                .stream()
                .filter(sotp -> Objects.equals(sotp.getUcn(), body.getId()))
                .findFirst();
        if (oSmsOtp.isPresent()) {
            Actions.Response response = oSmsOtp.get().getResponse();
            return validateSmsOtpBodyAndGet(response.getBody(), response.getExceptionObject(), response.getHttpCode());
        }
        throw new ProxyTestException("Mock not found for ucn = " + body.getId());
    }

    @RequestMapping(value = "/authentication/getSmsOtp",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<GetSmsOtpResponse> getSmsOtp(@RequestBody GetSmsOtpRequest body) throws ProxyTestException {
        Optional<Actions.GetSmsOtp> oSmsOtp = mockActions
                .getGetSmsOtps()
                .stream()
                .filter(sotp -> Objects.equals(sotp.getUcn(), body.getId()))
                .findFirst();
        if (oSmsOtp.isPresent()) {
            Actions.Response response = oSmsOtp.get().getResponse();
            return validateSmsOtpBodyAndGet(response.getBody(), response.getExceptionObject(), response.getHttpCode());
        }
        throw new ProxyTestException("Mock not found for ucn = " + body.getId());
    }

    private ResponseEntity<GetSmsOtpResponse> validateSmsOtpBodyAndGet(Actions.Body mockBody,
                                                                       Actions.ExceptionObject ProxyTestException,
                                                                       String mockHttpStatus) throws ProxyTestException {

        if (mockBody != null) {
            if (ProxyTestException != null) {
                throw new ProxyTestException("Body and exception simultaneously filled!");
            }
            GetSmsOtpResponse response = new GetSmsOtpResponse();
            response.setTransactionId(mockBody.getTransactionId());
            return ResponseEntity.ok(response);
        } else {
            throw new ProxyTestException(mockHttpStatus, ProxyTestException.getException(), ProxyTestException.getMessage(), ProxyTestException.getRemainingPinAttempts());
        }
    }
}
