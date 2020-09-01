package ru.croc.vtb.wso2.api.tests.model.rest.wso2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginUcnDTO {
    private String grant_type;
    private String scope;
    private String login;
    private String password;
}
