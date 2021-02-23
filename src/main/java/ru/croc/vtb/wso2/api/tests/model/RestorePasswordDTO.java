package ru.croc.vtb.wso2.api.tests.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestorePasswordDTO {
    private String id;
    private String systemId;
    private String mobilePhone;
    private String domain;
}
