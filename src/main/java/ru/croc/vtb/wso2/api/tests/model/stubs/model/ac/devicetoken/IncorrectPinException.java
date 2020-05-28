package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken;

/**
 * <b>Аналог ошибки от АЦ</b>
 *
 * @author Logvin I. N.
 */
public class IncorrectPinException extends Exception {

    private int remainingPinAttempts;

    public IncorrectPinException(int remainingPinAttempts, String msg) {
        super(msg);
        this.remainingPinAttempts = remainingPinAttempts;
    }

    public int getRemainingPinAttempts() {
        return remainingPinAttempts;
    }

    public void setRemainingPinAttempts(int remainingPinAttempts) {
        this.remainingPinAttempts = remainingPinAttempts;
    }
}
