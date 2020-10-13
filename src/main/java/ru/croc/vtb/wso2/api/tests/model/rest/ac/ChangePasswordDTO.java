package ru.croc.vtb.wso2.api.tests.model.rest.ac;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordDTO {
    private String id;
    private String domain;
    private String system;
    private String password;
    private String newPassword;
}
