package ru.croc.vtb.wso2.api.tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Logvin I. N.
 */
@Getter
@Setter
public class TokenDto implements Serializable {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    private String scope;

    @JsonProperty("id_token")
    private String idToken;

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("additional_properties")
    private Map<String, String> additionalProperties;

}