package ru.croc.vtb.wso2.api.tests.model.stubs.model.msa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Кросс-ссылка на систему
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-05-07T09:52:39.537Z")

public class CrossRefDTO {

    @JsonProperty("endDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime endDate = null;

    @JsonProperty("externalId")
    private String externalId = null;

    @JsonProperty("partyUId")
    private String partyUId = null;

    @JsonProperty("savedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime savedAt = null;

    @JsonProperty("systemNumber")
    private String systemNumber = null;

    public CrossRefDTO endDate(OffsetDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Дата-время окончания кросс-ссылки
     *
     * @return endDate
     **/
    @Valid

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public CrossRefDTO externalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    /**
     * Кросс-ссылка на систему
     *
     * @return externalId
     **/
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public CrossRefDTO partyUId(String partyUId) {
        this.partyUId = partyUId;
        return this;
    }

    /**
     * УИК клиента (или же MDM_ID)
     *
     * @return partyUId
     **/
    public String getPartyUId() {
        return partyUId;
    }

    public void setPartyUId(String partyUId) {
        this.partyUId = partyUId;
    }

    public CrossRefDTO savedAt(OffsetDateTime savedAt) {
        this.savedAt = savedAt;
        return this;
    }

    /**
     * Дата-время записи кросс-ссылки в кеше
     *
     * @return savedAt
     **/
    @Valid

    public OffsetDateTime getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(OffsetDateTime savedAt) {
        this.savedAt = savedAt;
    }

    public CrossRefDTO systemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
        return this;
    }

    /**
     * Код системы (в канонике)
     *
     * @return systemNumber
     **/
    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CrossRefDTO crossRefDTO = (CrossRefDTO) o;
        return Objects.equals(this.endDate, crossRefDTO.endDate) &&
                Objects.equals(this.externalId, crossRefDTO.externalId) &&
                Objects.equals(this.partyUId, crossRefDTO.partyUId) &&
                Objects.equals(this.savedAt, crossRefDTO.savedAt) &&
                Objects.equals(this.systemNumber, crossRefDTO.systemNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, externalId, partyUId, savedAt, systemNumber);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CrossRefDTO {\n");

        sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
        sb.append("    externalId: ").append(toIndentedString(externalId)).append("\n");
        sb.append("    partyUId: ").append(toIndentedString(partyUId)).append("\n");
        sb.append("    savedAt: ").append(toIndentedString(savedAt)).append("\n");
        sb.append("    systemNumber: ").append(toIndentedString(systemNumber)).append("\n");
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

