package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AuthDataResponseDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class AuthDataResponseDto {

    @JsonProperty("branchBicForSPMSharding")
    private String branchBicForSPMSharding = null;

    @JsonProperty("crossReferences")
    @Valid
    private List<CrossRefDTO> crossReferences = new ArrayList<CrossRefDTO>();

    @JsonProperty("payload")
    private PayloadDto payload = null;

    public AuthDataResponseDto branchBicForSPMSharding(String branchBicForSPMSharding) {
        this.branchBicForSPMSharding = branchBicForSPMSharding;
        return this;
    }

    /**
     * БИК
     *
     * @return branchBicForSPMSharding
     **/
    @NotNull

    public String getBranchBicForSPMSharding() {
        return branchBicForSPMSharding;
    }

    public void setBranchBicForSPMSharding(String branchBicForSPMSharding) {
        this.branchBicForSPMSharding = branchBicForSPMSharding;
    }

    public AuthDataResponseDto crossReferences(List<CrossRefDTO> crossReferences) {
        this.crossReferences = crossReferences;
        return this;
    }

    public AuthDataResponseDto addCrossReferencesItem(CrossRefDTO crossReferencesItem) {
        this.crossReferences.add(crossReferencesItem);
        return this;
    }

    /**
     * Кросс ссылки клиента
     *
     * @return crossReferences
     **/
    @NotNull

    @Valid

    public List<CrossRefDTO> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(List<CrossRefDTO> crossReferences) {
        this.crossReferences = crossReferences;
    }

    public AuthDataResponseDto payload(PayloadDto payload) {
        this.payload = payload;
        return this;
    }

    /**
     * Информация о текущей сессии
     *
     * @return payload
     **/
    @NotNull

    @Valid

    public PayloadDto getPayload() {
        return payload;
    }

    public void setPayload(PayloadDto payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AuthDataResponseDto authDataResponseDto = (AuthDataResponseDto) o;
        return Objects.equals(this.branchBicForSPMSharding, authDataResponseDto.branchBicForSPMSharding) &&
                Objects.equals(this.crossReferences, authDataResponseDto.crossReferences) &&
                Objects.equals(this.payload, authDataResponseDto.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchBicForSPMSharding, crossReferences, payload);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AuthDataResponseDto {\n");

        sb.append("    branchBicForSPMSharding: ").append(toIndentedString(branchBicForSPMSharding)).append("\n");
        sb.append("    crossReferences: ").append(toIndentedString(crossReferences)).append("\n");
        sb.append("    payload: ").append(toIndentedString(payload)).append("\n");
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

