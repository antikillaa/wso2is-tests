package ru.croc.vtb.wso2.api.tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Ошибка IncorrectPinException</b>
 *
 * @author Logvin I. N.
 */
@Getter
@Setter
public class PinAttemptsExceptionObject extends ExceptionObject {

    @JsonProperty("remainingPinAttempts")
    private Integer remainingPinAttempts = null;

    public PinAttemptsExceptionObject(int remainingPinAttempts) {
        this.remainingPinAttempts = remainingPinAttempts;
    }

}
