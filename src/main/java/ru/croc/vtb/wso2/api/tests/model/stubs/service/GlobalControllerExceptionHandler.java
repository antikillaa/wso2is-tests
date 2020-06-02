package ru.croc.vtb.wso2.api.tests.model.stubs.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.croc.vtb.wso2.api.tests.model.ExceptionObject;
import ru.croc.vtb.wso2.api.tests.model.PinAttemptsExceptionObject;
import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;

/**
 * <b>Перехватчик ошибок заглушки</b>
 *
 * @author LogvinIN
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ProxyTestException.class)
    public ResponseEntity<? super ExceptionObject> handleProxyTestException(ProxyTestException e) {

        ExceptionObject exceptionObject = new ExceptionObject();
        if (e.getRemainingPinAttempts() != null) {
            exceptionObject = new PinAttemptsExceptionObject(e.getRemainingPinAttempts());
        }
        exceptionObject.setException(e.getException());
        exceptionObject.setMessage(e.getMessage());

        return new ResponseEntity<>(exceptionObject, mapHttpStatus(e.getHttpCode()));
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ExceptionObject error(Exception e) {
        return new ExceptionObject(e);
    }

    /**
     * <b>Мапит код ошибки из пропертей в статус</b>
     *
     * @param httpCode ошибка АЦ
     * @return {@link HttpStatus}
     */
    private HttpStatus mapHttpStatus(String httpCode) {

        switch (httpCode) {
            case "401":
                return HttpStatus.UNAUTHORIZED;
            case "403":
                return HttpStatus.FORBIDDEN;
            case "404":
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
