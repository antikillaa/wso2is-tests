package ru.croc.vtb.wso2.api.tests.model;

/**
 * <b>Внутренняя ошибка приложения</b>
 *
 * @author Logvin I. N.
 */
public class ProxyTestException extends Exception {

    public ProxyTestException(String msg, Throwable err) {
        super(msg, err);
    }
}
