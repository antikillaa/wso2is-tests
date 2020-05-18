package ru.croc.vtb.wso2.api.tests.service;

import ru.croc.vtb.wso2.api.tests.model.ProxyTestException;
import ru.croc.vtb.wso2.api.tests.model.TokenDto;

/**
 * @author Logvin I. N.
 */
public interface ProxyTestService {

    TokenDto oneFactorPositive() throws ProxyTestException;

    void withSecondFactorPositive();

    void oneFactorNegative();

    void withTwoFactorPositive();
}
