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
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.DeviceTokenAuthentication;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.IncorrectPinException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.GenericBusinessLogicException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.NoMorePinAttemptsException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.EntryLockedException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.DeviceNotFoundException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.UserNotFoundException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.DirectoryAccessException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.DeviceTokenNotActiveException;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken.User;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.smsotp.GetSmsOtpRequest;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.smsotp.GetSmsOtpResponse;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.AuthDataRequestDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.AuthDataResponseDto;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.CrossRefDTO;
import ru.croc.vtb.wso2.api.tests.model.stubs.model.msa.PayloadDto;

import java.util.Arrays;
import java.util.UUID;

/**
 * <b>Контроллер заглушек</b>
 *
 * @author Logvin I. N.
 */
@RestController
public class MockController {

    @Autowired
    RestTemplate restTemplate;

    @Getter
    @Setter
    private String soapResponse;

    @RequestMapping(value = "/authentication/deviceToken",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<User> deviceToken(@RequestBody DeviceTokenAuthentication body) throws IncorrectPinException, GenericBusinessLogicException, NoMorePinAttemptsException, EntryLockedException, DeviceNotFoundException, UserNotFoundException, DirectoryAccessException, DeviceTokenNotActiveException {
        if (body.getDeviceTokenId().equals("401")) {
            throw new IncorrectPinException(2, "AUTHENTICATION_EXCEPTION");
        } else if (body.getDeviceTokenId().equals("403") && body.getChallenge().equals("WRONG STATE")) {
            throw new GenericBusinessLogicException("DEVICE_TOKEN_NOT_ACTIVATED");
        } else if (body.getDeviceTokenId().equals("403") && body.getChallenge().equals("DEVICE LOCKED")) {
            throw new NoMorePinAttemptsException("AUTHENTICATION_EXCEPTION");
        } else if (body.getDeviceTokenId().equals("403") && body.getChallenge().equals("USER_LOCKED")) {
            throw new EntryLockedException("AUTHENTICATION_EXCEPTION");
        } else if (body.getDeviceTokenId().equals("403") && body.getChallenge().equals("DEVICE NOT ACTIVE")) {
            throw new DeviceTokenNotActiveException("Девайс-токен не активен");
        } else if (body.getDeviceTokenId().equals("404") && body.getChallenge().equals("DEVICE NOT FOUND")) {
            throw new DeviceNotFoundException("Устройство двухфакторной аутентификации не найдено");
        } else if (body.getDeviceTokenId().equals("404") && body.getChallenge().equals("USER_NOT_FOUND")) {
            throw new UserNotFoundException("Пользатель не найден");
        } else if (body.getDeviceTokenId().equals("500")) {
            throw new DirectoryAccessException("тестовый текст для 500 ошибки");
        }
        User user = new User();
        user.setId("20002418");
        user.setDomain("master");
        user.setMobile("9163742359");
        user.setAccountLock(false);
        user.setDisableSmsotp(false);
        user.setUsername("&lt;");
        user.setHomePhone("");
        user.setEmail("petrahome@mai.ru");
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/sessions",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<AuthDataResponseDto> createSessionUsingPOST(String userAgent, AuthDataRequestDto authData, String deviceFingerprint, String mobileSdkData) {
        AuthDataResponseDto dto = new AuthDataResponseDto();
        dto.setBranchBicForSPMSharding("organization bik");
        dto.setCrossReferences(Arrays.asList(getCrossRef("123123"), getCrossRef("98000")));
        dto.setPayload(new PayloadDto()
                .msaSessionId(UUID.randomUUID().toString())
                .routeName("default")
                .shardName("shard1")
                .uniqueClientNumber("20008901")
        );
        return new ResponseEntity<AuthDataResponseDto>(dto, HttpStatus.OK);
    }

    private CrossRefDTO getCrossRef(String systemNumber) {
        return new CrossRefDTO()
                .endDate(org.threeten.bp.OffsetDateTime.now())
                .externalId(UUID.randomUUID().toString())
                .partyUId(UUID.randomUUID().toString())
                .savedAt(org.threeten.bp.OffsetDateTime.now())
                .systemNumber(systemNumber);
    }

    @RequestMapping(value = "/mockAsyncAdaptiveAuthenticationSoapBinding",
            method = RequestMethod.POST)
    public ResponseEntity<String> mockSoap() {
        String soapResponse = this.getSoapResponse();
        return new ResponseEntity<String>(soapResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/authentication/smsOtp",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<GetSmsOtpResponse> getSmsOtp(@RequestBody GetSmsOtpRequest body
    ) {
        GetSmsOtpResponse response = new GetSmsOtpResponse();
        response.setTransactionId("1234645454845");
        return ResponseEntity.ok(response);
    }
}
