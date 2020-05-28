package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Дополнительная информация о выходе
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class PayloadDto {

    @JsonProperty("msaSessionId")
    private String msaSessionId = null;

    @JsonProperty("routeName")
    private String routeName = null;

    @JsonProperty("shardName")
    private String shardName = null;

    @JsonProperty("uniqueClientNumber")
    private String uniqueClientNumber = null;

    public PayloadDto msaSessionId(String msaSessionId) {
        this.msaSessionId = msaSessionId;
        return this;
    }

    /**
     * ID сессии в MSA
     *
     * @return msaSessionId
     **/
    @NotNull

    public String getMsaSessionId() {
        return msaSessionId;
    }

    public void setMsaSessionId(String msaSessionId) {
        this.msaSessionId = msaSessionId;
    }

    public PayloadDto routeName(String routeName) {
        this.routeName = routeName;
        return this;
    }

    /**
     * Название маршрута
     *
     * @return routeName
     **/
    @NotNull

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public PayloadDto shardName(String shardName) {
        this.shardName = shardName;
        return this;
    }

    /**
     * Название шарда
     *
     * @return shardName
     **/
    @NotNull

    public String getShardName() {
        return shardName;
    }

    public void setShardName(String shardName) {
        this.shardName = shardName;
    }

    public PayloadDto uniqueClientNumber(String uniqueClientNumber) {
        this.uniqueClientNumber = uniqueClientNumber;
        return this;
    }

    /**
     * УНК (уникальный номер клиента)
     *
     * @return uniqueClientNumber
     **/
    @NotNull

    public String getUniqueClientNumber() {
        return uniqueClientNumber;
    }

    public void setUniqueClientNumber(String uniqueClientNumber) {
        this.uniqueClientNumber = uniqueClientNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PayloadDto payloadDto = (PayloadDto) o;
        return Objects.equals(this.msaSessionId, payloadDto.msaSessionId) &&
                Objects.equals(this.routeName, payloadDto.routeName) &&
                Objects.equals(this.shardName, payloadDto.shardName) &&
                Objects.equals(this.uniqueClientNumber, payloadDto.uniqueClientNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(msaSessionId, routeName, shardName, uniqueClientNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PayloadDto {\n");

        sb.append("    msaSessionId: ").append(toIndentedString(msaSessionId)).append("\n");
        sb.append("    routeName: ").append(toIndentedString(routeName)).append("\n");
        sb.append("    shardName: ").append(toIndentedString(shardName)).append("\n");
        sb.append("    uniqueClientNumber: ").append(toIndentedString(uniqueClientNumber)).append("\n");
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

