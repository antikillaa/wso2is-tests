package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Способ идентификации
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class IdentificationDto {

    @JsonProperty("username")
    private String username = null;

    /**
     * Тип username
     */
    public enum UsernameTypeEnum {
        UCN("UCN"),
        ALIAS("ALIAS"),
        PHONE("PHONE"),
        CARD("CARD");

        private String value;

        UsernameTypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static UsernameTypeEnum fromValue(String text) {
            for (UsernameTypeEnum b : UsernameTypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("usernameType")
    private UsernameTypeEnum usernameType = null;

    public IdentificationDto username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Username клиента
     *
     * @return username
     **/
    @NotNull

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public IdentificationDto usernameType(UsernameTypeEnum usernameType) {
        this.usernameType = usernameType;
        return this;
    }

    /**
     * Тип username
     *
     * @return usernameType
     **/
    @NotNull

    public UsernameTypeEnum getUsernameType() {
        return usernameType;
    }

    public void setUsernameType(UsernameTypeEnum usernameType) {
        this.usernameType = usernameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdentificationDto identificationDto = (IdentificationDto) o;
        return Objects.equals(this.username, identificationDto.username) &&
                Objects.equals(this.usernameType, identificationDto.usernameType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, usernameType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IdentificationDto {\n");

        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    usernameType: ").append(toIndentedString(usernameType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

