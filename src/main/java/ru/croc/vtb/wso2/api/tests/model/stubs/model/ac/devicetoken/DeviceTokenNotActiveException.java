package ru.croc.vtb.wso2.api.tests.model.stubs.model.ac.devicetoken;

/**
 * <b>Аналог ошибки от АЦ</b>
 *
 * @author Logvin I. N.
 */
public class DeviceTokenNotActiveException extends Exception {

    public DeviceTokenNotActiveException(String msg) {
        super(msg);
    }

}
