package ru.croc.vtb.wso2.api.tests.model.rest.ac;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetUcnDTO {
    private String alias;
    private String phone;
    private String domain;
}
