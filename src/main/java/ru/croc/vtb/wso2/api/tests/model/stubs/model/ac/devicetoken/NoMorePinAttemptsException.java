package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken;

/**
 * <b>Аналог ошибки от АЦ</b>
 *
 * @author Logvin I. N.
 */
public class NoMorePinAttemptsException extends Exception {

    public NoMorePinAttemptsException(String msg) {
        super(msg);
    }

}
