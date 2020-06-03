package ru.croc.vtb.wso2.api.tests.model;

import lombok.Getter;
import lombok.Setter;

/**
 * <b>Внутренняя ошибка приложения</b>
 *
 * @author Logvin I. N.
 */
@Getter
@Setter
public class ProxyTestException extends Exception {

    private Integer remainingPinAttempts;

    private String httpCode;

    private String exception;

    private String message;

    public ProxyTestException(String httpCode, String exception, String msg, Integer remainingPinAttempts) {
        super(msg);
        this.httpCode = httpCode;
        this.exception = exception;
        this.message = msg;
        this.remainingPinAttempts = remainingPinAttempts;
    }


    public ProxyTestException(String msg) {
        super(msg);
    }

    public ProxyTestException(String msg, Throwable err) {
        super(msg, err);
    }
}
