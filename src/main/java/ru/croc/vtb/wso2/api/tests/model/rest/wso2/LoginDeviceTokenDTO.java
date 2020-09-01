package ru.croc.vtb.wso2.api.tests.model.rest.wso2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.croc.vtb.wso2.api.tests.model.config.LoginGrantType;

@Getter
@Setter
@Builder
@ToString
public class LoginDeviceTokenDTO {
    @JsonProperty("grant_type")
    private LoginGrantType grantType;
    private String deviceTokenID;
    @JsonProperty("X-Device-FingerPrint")
    private String fingerPrint;
    private String secureCode;
    private String challenge;
}
