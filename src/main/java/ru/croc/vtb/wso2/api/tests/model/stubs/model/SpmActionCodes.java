package ru.croc.vtb.wso2.api.tests.model.stubs.model;

/**
 * <b>Перечисление actionCode от SOAP ответа СПМ</b>
 *
 * @author Logvin I. N.
 */
public enum SpmActionCodes {
    /**
     * <b>СПМ разрешил вход</b>
     */
    ALLOW,

    /**
     * <b>СПМ просит второй фактор</b>
     */
    REVIEW,

    /**
     * <b>СПМ отказал во входе</b>
     */
    DENY
}
