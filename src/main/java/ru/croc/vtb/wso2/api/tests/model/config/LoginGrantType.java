package ru.croc.vtb.wso2.api.tests.model.config;

public enum LoginGrantType {

    LOGIN("login"),
    DEVICE_TOKEN_ID("deviceTokenID"),
    CARD_NUMBER("cardNumber");

    private final String loginType;

    LoginGrantType(String grantType) {
        this.loginType = grantType;
    }

    public String getGrantType() {
        return loginType;
    }
}
